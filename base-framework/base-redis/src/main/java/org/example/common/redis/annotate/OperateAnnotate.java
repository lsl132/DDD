package org.example.common.redis.annotate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

import java.lang.annotation.*;

/**
 * @Describe : TODO
 * @Author : SHK
 * @Date : 2021/11/30 10:55
 */
@Documented
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface OperateAnnotate {



}
