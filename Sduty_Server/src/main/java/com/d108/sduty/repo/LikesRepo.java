package com.d108.sduty.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.d108.sduty.dto.Likes;

public interface LikesRepo extends JpaRepository<Likes, Integer>{
	boolean existsByUserSeqAndStorySeq(int userSeq, int storySeq);
}
