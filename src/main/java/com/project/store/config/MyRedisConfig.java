package com.project.store.config;

import com.project.store.vo.ProductVO;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;

@Configuration
public class MyRedisConfig {
    @Bean
    public RedisTemplate<Object, ProductVO> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate<Object, ProductVO> template = new RedisTemplate<>();
        template.setConnectionFactory(redisConnectionFactory);

        Jackson2JsonRedisSerializer<ProductVO> ser = new Jackson2JsonRedisSerializer<>(ProductVO.class);
        template.setDefaultSerializer(ser);
        return template;
    }
}

