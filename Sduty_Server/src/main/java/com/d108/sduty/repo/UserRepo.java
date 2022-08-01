package com.d108.sduty.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.d108.sduty.dto.User;

public interface UserRepo extends JpaRepository<User, Integer> {
	boolean existsByid(String id);
	Optional<User> findByid(String id);
	Optional<User> findByTel(String tel);
	Optional<User> findBySeq(int seq);
}
