package com.d108.sduty.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.PageRequest;

import com.d108.sduty.dto.Reply;
import com.d108.sduty.dto.Story;

public interface StoryService {
	Story insertStory(Story story);
	Story updateStory(Story story);
	List<Story> findBywriterSeq(int userSeq);
	Story findById(int storySeq);
	List<Story> findAll();
	List<Story> findAllByWriterSeqInOrderByRegtimeDesc(List<Integer> writerSeqs);
	List<Story> selectStoryInSeq(List<Integer> storySeqs);
	void deleteStory(int storySeq);
	
	List<Reply> selectReplyByStorySeq(int storySeq);
	Reply insertReply(Reply reply);
	Reply updateReply(Reply reply);
	void deleteReply(int replySeq);
}
