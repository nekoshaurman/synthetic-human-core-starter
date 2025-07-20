package com.weylandyutani.starter.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.TimeUnit;

@Configuration
public class ThreadPoolConfig {

    @Bean
    public ThreadPoolExecutor commandExecutorThreadPool() {
        return new ThreadPoolExecutor(
                5, // core pool size
                10, // max pool size
                60L, TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(50), // capacity
                new ThreadPoolExecutor.AbortPolicy() // отбрасывать задачи при переполнении очереди
        );
    }
}
