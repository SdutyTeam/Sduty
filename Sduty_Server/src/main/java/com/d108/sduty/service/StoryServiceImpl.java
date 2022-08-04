package com.d108.sduty.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
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
	public List<Story> findBywriterSeq(int userSeq) {
		return optConverter(storyRepo.findBywriterSeqOrderByRegtimeDesc(userSeq));
	}

	@Override
	public Optional<Story> findById(int storySeq) {
		return storyRepo.findById(storySeq);
	}

	@Override
	public List<Story> findAll() {
		return storyRepo.findAll();
	}
	
	@Override
	public List<Story> findAllByWriterSeqInOrderByRegtimeDesc(List<Integer> writerSeqs) {
		return storyRepo.findAllByWriterSeqInOrderByRegtimeDesc(writerSeqs);
	}

	@Override
	public void deleteStory(int storySeq) {
		storyRepo.deleteById(storySeq);
	}

	private List<Story> optConverter(List<Optional<Story>> list){
		List<Story> sList = new ArrayList<>();
		for(Optional<Story> l : list) {
			if(l.isPresent())
				sList.add(l.get());
		}
		return sList;
	}

	@Override
	public List<Story> selectStoryInSeq(List<Integer> storySeqs) {
		return storyRepo.findAllBySeqInOrderByRegtimeDesc(storySeqs);
	}

}
