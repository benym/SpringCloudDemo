package cn.itcast.feign.config;

import cn.itcast.feign.clients.fallback.UserClientFallbackFactory;
import feign.Logger;
import org.springframework.context.annotation.Bean;

/**
 * @Time : 2021/8/25 22:45
 * @Author : YuanMing
 * @File : DefaultFeignConfiguration.java
 * @Software: IntelliJ IDEA
 */
public class DefaultFeignConfiguration {
    @Bean
    public Logger.Level logLevel(){
        return Logger.Level.BASIC;
    }

    @Bean
    public UserClientFallbackFactory userClientFallbackFactory(){
        return new UserClientFallbackFactory();
    }
}
