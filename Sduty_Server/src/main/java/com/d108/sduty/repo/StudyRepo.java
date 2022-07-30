package com.d108.sduty.repo;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.d108.sduty.dto.Study;

public interface StudyRepo extends JpaRepository<Study, Integer>, JpaSpecificationExecutor<Study> {
	public Optional<Study> findByNameEquals(String name);
	//@EntityGraph(attributePaths= {"participation"})
	public Study findBySeq(int studySeq);
	@Transactional
	public int deleteBySeq(int studySeq);
}
