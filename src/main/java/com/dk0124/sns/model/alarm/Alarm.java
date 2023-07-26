package com.dk0124.sns.model.alarm;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Alarm {
	private UUID id;
	private Long toId;
	private AlarmType alarmType;
	private HashMap<String, String> alarmContent;
	private Timestamp createdAt;

	public static Alarm fromEntity(AlarmEntity alarmEntity){
		return  new Alarm(
			alarmEntity.getId(),
			alarmEntity.getToId(),
			alarmEntity.getAlarmType(),
			alarmEntity.getAlarmContent(),
			alarmEntity.getCreatedAt()
		);
	}
}
