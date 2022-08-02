package com.d108.sduty.service;

import java.util.List;
import java.util.Optional;

import com.d108.sduty.dto.Story;

public interface StoryService {
	Story insertStory(Story story);
	List<Optional<Story>> findBywriterSeq(int userSeq);
}
