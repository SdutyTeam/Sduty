package com.d108.sduty.repo;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.d108.sduty.dto.Story;

public interface StoryRepo extends JpaRepository<Story, Integer>{
	List<Optional<Story>> findRegtimeBywriterSeq(String userSeqs);//개인 타임라인 조회
	List<Optional<Story>> findRegtimeBywriterSeqAndHashtag(String userSeqs, int hashtag);
	List<Optional<Story>> findBywriterSeqOrderByRegtimeDesc(int userSeq);
	List<Optional<Date>> findRegtimeBywriterSeq(int writerSeq);
	List<Optional<Story>> findAllByWriterSeqInOrderByRegtimeDesc(List<Integer> followerSeqs, Pageable pageable);
	List<Story> findAllByOrderByRegtimeDesc();
	List<Story> findAllByWriterSeqInOrderByRegtimeDesc(List<Integer> writerSeqs);
}
