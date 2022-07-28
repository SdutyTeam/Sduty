package com.d108.sduty.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.d108.sduty.dto.Study;

public interface StudyRepo extends JpaRepository<Study, String> {

}
