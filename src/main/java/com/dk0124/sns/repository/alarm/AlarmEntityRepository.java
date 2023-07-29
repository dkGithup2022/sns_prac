package com.dk0124.sns.repository.alarm;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dk0124.sns.model.alarm.AlarmEntity;

public interface AlarmEntityRepository extends JpaRepository<AlarmEntity, Long > {
}
