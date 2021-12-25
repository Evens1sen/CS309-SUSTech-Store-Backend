package com.project.store.config;

import com.project.store.entity.Product;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;

@Configuration
public class MyRedisConfig {
    @Bean
    public RedisTemplate<Object, Product> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate<Object, Product> template = new RedisTemplate<>();
        template.setConnectionFactory(redisConnectionFactory);

        Jackson2JsonRedisSerializer<Product> ser = new Jackson2JsonRedisSerializer<>(Product.class);
        template.setDefaultSerializer(ser);
        return template;
    }
}

