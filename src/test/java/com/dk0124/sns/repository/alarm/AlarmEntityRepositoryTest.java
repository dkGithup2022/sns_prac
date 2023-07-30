package com.dk0124.sns.repository.alarm;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.dk0124.sns.model.alarm.AlarmEntity;
import com.dk0124.sns.model.alarm.AlarmType;

@SpringBootTest
class AlarmEntityRepositoryTest {

	@Autowired
	private AlarmEntityRepository alarmRepository;

	@Test
	public void test() {
		AlarmEntity entity = AlarmEntity.builder().toId(1L).alarmType(AlarmType.NEW_POST).build();

		AlarmEntity saved = alarmRepository.save(entity);

		System.out.println(saved.toString());

		assertNotNull(saved);
	}
}