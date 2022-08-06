package com.d108.sduty.repo;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.d108.sduty.dto.Story;

public interface StoryRepo extends JpaRepository<Story, Integer>{
	List<Optional<Story>> findRegtimeBywriterSeq(String userSeqs);//개인 타임라인 조회
	List<Optional<Story>> findBywriterSeqOrderByRegtimeDesc(int userSeq);//작성자로 조회
	List<Story> findAllByOrderByRegtimeDesc();//전체 조회
	List<Story> findAllByWriterSeqInOrderByRegtimeDesc(List<Integer> writerSeqs);//팔로우한 작성자들로 조회
	List<Story> findAllByWriterSeqInAndJobHashtagOrderByRegtimeDesc(List<Integer> writerSeqs, int jobSeq);//팔로우한 작성자들로 조회
	List<Story> findAllBySeqInOrderByRegtimeDesc(List<Integer> storySeqs);//게시글 목록들로 조회
	Story findTopByWriterSeqOrderByRegtimeDesc(int writerSeq);
	
	@Query(value="SELECT distinct dayofyear(substr(story_regtime, 1,10)) as dayofyear FROM sduty.story where story_writer_seq = ?1", nativeQuery=true)
	List<Integer> findAllRegtime(int userSeq);
}
