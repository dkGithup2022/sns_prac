package com.dk0124.sns.config.redisConverter;

import java.sql.Timestamp;

import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.WritingConverter;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.RequiredArgsConstructor;

@Component
@WritingConverter
@RequiredArgsConstructor
public class TimestampToBytesConverter implements Converter<Timestamp, byte[]> {

	private final Jackson2JsonRedisSerializer<Timestamp> serializer;

	public TimestampToBytesConverter() {
		serializer = new Jackson2JsonRedisSerializer<Timestamp>(Timestamp.class);
		serializer.setObjectMapper(new ObjectMapper());
	}

	@Override
	public byte[] convert(Timestamp source) {
		return serializer.serialize(source);
	}
}