package com.d108.sduty.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.d108.sduty.dto.Story;
import com.d108.sduty.repo.StoryRepo;

@Service
public class StoryServiceImpl implements StoryService {

	@Autowired
	private StoryRepo storyRepo;
	
	@Override
	public Story insertStory(Story story) {
		return storyRepo.save(story);
	}

	@Override
	public List<Optional<Story>> findBywriterSeq(int userSeq) {
		return storyRepo.findBywriterSeqOrderByRegtimeDesc(userSeq);
	}

}
