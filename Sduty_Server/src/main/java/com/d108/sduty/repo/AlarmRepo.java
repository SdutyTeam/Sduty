package com.d108.sduty.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.d108.sduty.dto.Alarm;

public interface AlarmRepo extends JpaRepository<Alarm, String> {

}
