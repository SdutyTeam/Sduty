package com.d108.sduty.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.d108.sduty.dto.Study;

public interface StudyRepo extends JpaRepository<Study, String> {
	public Optional<Study> findByNameEquals(String name);
	public Study findBySeq(int studySeq);
}
