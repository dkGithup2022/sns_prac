package com.dk0124.sns.config;

import java.util.Arrays;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.convert.RedisCustomConversions;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import com.dk0124.sns.config.redisConverter.BytesToTimestampConverter;
import com.dk0124.sns.config.redisConverter.TimestampToBytesConverter;

@Configuration
@EnableRedisRepositories
public class RedisConfig {

	@Bean
	public LettuceConnectionFactory redisConnectionFactory() {
		// 로컬에선 노드 한대라 single node redis 설정 하였음 . 이건 나중에 변경 포인트가 맞다
		return new LettuceConnectionFactory(new RedisStandaloneConfiguration());
	}

	@Bean
	public RedisCustomConversions redisCustomConversions(BytesToTimestampConverter bytesToTimestamp,
		TimestampToBytesConverter timestampToBytes) {
		return new RedisCustomConversions(Arrays.asList(bytesToTimestamp, timestampToBytes));
	}

	@Bean
	RedisTemplate<?, ?> redisTemplate(RedisConnectionFactory redisConnectionFactory) {

		RedisTemplate<?, ?> template = new RedisTemplate<>();
		template.setConnectionFactory(redisConnectionFactory);
		template.setKeySerializer(new StringRedisSerializer());
		template.setValueSerializer(new StringRedisSerializer());
		return template;
	}

}
