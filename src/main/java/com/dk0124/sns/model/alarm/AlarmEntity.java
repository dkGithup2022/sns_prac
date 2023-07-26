package com.dk0124.sns.model.alarm;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.Getter;


/* 로그성 데이터이기 때문에 base 정보를 저장 안함. soft delete 도 안함 */

@Entity
@Table(name = "\"alarm\"")
@Getter
@EntityListeners(AuditingEntityListener.class)
public class AlarmEntity {

	@Id
	@GeneratedValue(generator = "uuid2")
	@GenericGenerator(name = "uuid2", strategy = "uuid2")
	@Column(columnDefinition = "BINARY(16)")
	private UUID id;

	private Long toId; // 여기선 외래키를 안걸고 싶어..... 외래키를 잘 모르기 때문 .

	private AlarmType alarmType;

	@Type(type = "json")
	@Column(name = "histories", columnDefinition = "longtext")
	private HashMap<String, String> alarmContent;

	@CreatedDate
	private Timestamp createdAt;

}
