package org.example.common.redis;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.lettuce.core.cluster.ClusterClientOptions;
import io.lettuce.core.cluster.ClusterTopologyRefreshOptions;
import io.lettuce.core.resource.ClientResources;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisClusterConfiguration;
import org.springframework.data.redis.connection.RedisPassword;
import org.springframework.data.redis.connection.lettuce.LettuceClientConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettucePoolingClientConfiguration;
import org.springframework.data.redis.core.*;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import javax.annotation.Resource;
import java.time.Duration;

/**
 * @Describe: Redis配置类
 * @Author: lsl
 * @Date: 2021-11-29
 */
@Configuration
@EnableCaching
public class RedisConfig extends CachingConfigurerSupport {


    @Resource
    private RedisProperties redisProperties;

    /**
     * @Describe: RedisTemplate配置
     * @Param: redis集群连接工厂
     * @Return RedisTemplate
     * @Author: lsl
     * @Date: 2021-11-29
     */
    @Bean
    public RedisTemplate<String, Object> redisTemplate(
            LettuceConnectionFactory redisCF) {
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<String, Object>();
        redisTemplate.setConnectionFactory(redisCF);
        initDomainRedisTemplate(redisTemplate);
        redisTemplate.afterPropertiesSet();

        return redisTemplate;
    }


    /**
     * 连接Redis
     *
     * @return
     */
    @Bean
    public LettuceConnectionFactory redisConnectionFactory(RedisClusterConfiguration cluster, LettuceClientConfiguration lettuceClientConfiguration) {
        LettuceConnectionFactory lettuceConnectionFactory = new LettuceConnectionFactory(cluster, lettuceClientConfiguration);
        return lettuceConnectionFactory;
    }


    @Bean
    public RedisClusterConfiguration getClusterConfig() {
        //添加redis 集群节点信息
        RedisClusterConfiguration rcc = new RedisClusterConfiguration(redisProperties.getCluster().getNodes());
        rcc.setPassword(RedisPassword.of(redisProperties.getPassword()));
        rcc.setMaxRedirects(100);
        return rcc;
    }

    @Bean
    public LettuceClientConfiguration lettuceClientConfiguration(ClientResources clientResources) {

        ClusterTopologyRefreshOptions clusterTopologyRefreshOptions =  ClusterTopologyRefreshOptions.builder()
                //.enablePeriodicRefresh(Duration.ofSeconds(5))
                // 开启全部自适应刷新 自适应刷新不开启,Redis集群变更时将会导致连接异常
                .enableAllAdaptiveRefreshTriggers()
                .adaptiveRefreshTriggersTimeout(Duration.ofSeconds(10))
                // 开周期刷新
                //.enablePeriodicRefresh(Duration.ofSeconds(10))
                .build();

        ClusterClientOptions clusterClientOptions = ClusterClientOptions.builder()
                //.autoReconnect(false)  是否自动重连
                //.pingBeforeActivateConnection(Boolean.TRUE)
                //.cancelCommandsOnReconnectFailure(Boolean.TRUE)
                //.disconnectedBehavior(ClientOptions.DisconnectedBehavior.REJECT_COMMANDS)
                .topologyRefreshOptions(clusterTopologyRefreshOptions).build();

        return LettucePoolingClientConfiguration.builder().commandTimeout(Duration.ofMillis(1000))
                .poolConfig(getPoolConfig(redisProperties.getLettuce().getPool()))
                .clientResources(clientResources)
                .clientOptions(clusterClientOptions)
                .build();
    }

    private GenericObjectPoolConfig getPoolConfig(RedisProperties.Pool properties) {
        GenericObjectPoolConfig config = new GenericObjectPoolConfig();
        config.setMaxTotal(properties.getMaxActive());
        config.setMaxIdle(properties.getMaxIdle());
        config.setMinIdle(properties.getMinIdle());
        if (properties.getMaxWait() != null) {
            config.setMaxWaitMillis(properties.getMaxWait().toMillis());
        }

        return config;
    }

    @Bean
    public StringRedisTemplate stringRedisTemplate(LettuceConnectionFactory redisCF) {
        StringRedisTemplate stringRedisTemplate = new StringRedisTemplate();
        stringRedisTemplate.setConnectionFactory(redisCF);
        return stringRedisTemplate;
    }

    /**
     * 设置数据存入 redis 的序列化方式
     * </br>redisTemplate 序列化默认使用的jdkSerializeable, 存储二进制字节码, 导致key会出现乱码，所以自定义
     * 序列化类
     *
     * @param redisTemplate
     * @param
     */
    private void initDomainRedisTemplate(RedisTemplate<String, Object> redisTemplate) {
        // 使用Jackson2JsonRedisSerialize 替换默认序列化
        Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);
        ObjectMapper om = new ObjectMapper();
        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
        jackson2JsonRedisSerializer.setObjectMapper(om);
        //// string结构的数据，设置value的序列化规则和 key的序列化规则
        //StringRedisSerializer解决key中乱码问题。//Long类型不可以会出现异常信息;
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        //value乱码问题：Jackson2JsonRedisSerializer
        redisTemplate.setValueSerializer(jackson2JsonRedisSerializer);

        //设置Hash结构的key和value的序列化方式
        //redisTemplate.setHashKeySerializer(jackson2JsonRedisSerializer);
        //redisTemplate.setHashValueSerializer(jackson2JsonRedisSerializer);
    }

    /**
     * 实例化 CacheManager 对象，指定使用RedisCacheManager作缓存管理
     *
     * @return CacheManager
     */
    @Bean
    public CacheManager cacheManager(RedisTemplate redisTemplate) {
        RedisCacheManager rcm = RedisCacheManager.create(redisTemplate.getConnectionFactory());
        return rcm;
    }

    @Bean
    public HashOperations<String, String, Object> hashOperations(RedisTemplate<String, String> redisTemplate) {
        return redisTemplate.opsForHash();
    }

    @Bean
    public ValueOperations<String, String> valueOperations(RedisTemplate<String, String> redisTemplate) {
        return redisTemplate.opsForValue();
    }

    @Bean
    public ListOperations<String, String> listOperations(RedisTemplate<String, String> redisTemplate) {
        return redisTemplate.opsForList();
    }

    @Bean
    public SetOperations<String, String> setOperations(RedisTemplate<String, String> redisTemplate) {
        return redisTemplate.opsForSet();
    }

    @Bean
    public ZSetOperations<String, String> zSetOperations(RedisTemplate<String, String> redisTemplate) {
        return redisTemplate.opsForZSet();
    }
}
