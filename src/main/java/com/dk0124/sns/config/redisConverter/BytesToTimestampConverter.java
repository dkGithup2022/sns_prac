package com.dk0124.sns.config.redisConverter;

import java.sql.Timestamp;

import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.ReadingConverter;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.RequiredArgsConstructor;

@ReadingConverter
@Component
@RequiredArgsConstructor
public class BytesToTimestampConverter implements Converter<byte[], Timestamp> {

	private final Jackson2JsonRedisSerializer<Timestamp> serializer;

	public BytesToTimestampConverter() {

		serializer = new Jackson2JsonRedisSerializer<Timestamp>(Timestamp.class);
		serializer.setObjectMapper(new ObjectMapper());
	}

	@Override
	public Timestamp convert(byte[] value) {
		return serializer.deserialize(value);
	}
}
