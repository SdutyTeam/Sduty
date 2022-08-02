package com.d108.sduty.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.d108.sduty.dto.Qna;

public interface QnaRepo extends JpaRepository<Qna, Integer> {
}
