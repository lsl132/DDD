package org.example.software.infrastructure.config;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;

import java.lang.reflect.Method;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 异步线程池配置
 * @author SHK
 */
@Slf4j
@Configuration
@EnableAsync
public class TaskExecutorConfig implements AsyncConfigurer {


    /***
     * 创建异步任务执行线程池
     * @return
     */
    @Override
    public Executor getAsyncExecutor() {
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        return executorService;
    }

    @Override
    public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
        // 执行过程中的异常捕获处理
        return new AsyncUncaughtExceptionHandler() {
            @Override
            public void handleUncaughtException(Throwable throwable, Method method, Object... objects) {
                log.error("async execute error, method:{}, param:{}", method.getName(), JSON.toJSONString(objects), throwable);
            }
        };
    }

}
