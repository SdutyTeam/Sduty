package com.d108.sduty.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.d108.sduty.dto.Reply;
import com.d108.sduty.dto.Story;
import com.d108.sduty.dto.StoryInterest;
import com.d108.sduty.repo.ReplyRepo;
import com.d108.sduty.repo.StoryInterestHashtagRepo;
import com.d108.sduty.repo.StoryRepo;

@Service
public class StoryServiceImpl implements StoryService {

	@Autowired
	private StoryRepo storyRepo;
	
	@Autowired
	private ReplyRepo replyRepo;
	
	@Autowired
	private StoryInterestHashtagRepo storyInterestHashtagRepo;
	
	@Override
	public Story insertStory(Story story) {
		Story s = storyRepo.save(story);
		int storySeq = storyRepo.findTopByWriterSeqOrderByRegtimeDesc(s.getWriterSeq()).getSeq();
		s.setSeq(storySeq);
		if(!story.getInterestHashtag().isEmpty()) {
			for(int i : story.getInterestHashtag())
				storyInterestHashtagRepo.save(new StoryInterest(storySeq, i));
		}
		return s;
	}	
	
	@Override
	public Story updateStory(Story story) {
		int storySeq = story.getSeq();
		List<StoryInterest> listSI = storyInterestHashtagRepo.findAllBySeq(storySeq);
		if(!listSI.isEmpty()) {
			for(StoryInterest si : listSI) {
				storyInterestHashtagRepo.delete(si);
			}
		}
		if(!story.getInterestHashtag().isEmpty()) {
			for(int i : story.getInterestHashtag())
				storyInterestHashtagRepo.save(new StoryInterest(storySeq, i));
		}
		return storyRepo.save(story);
	}

	@Override
	public List<Story> findBywriterSeq(int userSeq) {
		return optConverter(storyRepo.findBywriterSeqOrderByRegtimeDesc(userSeq));
	}

	@Override
	public Story findById(int storySeq) {
		Story story = null;
		if(storyRepo.findById(storySeq).isPresent()) {
			 story = storyRepo.findById(storySeq).get();
		}
		List<StoryInterest> listSI = storyInterestHashtagRepo.findAllBySeq(storySeq);
		List<Integer> ilistSI = new ArrayList<Integer>();
		for(StoryInterest si : listSI) {
			ilistSI.add(si.getInterestSeq());
		}
		story.setInterestHashtag(ilistSI);
		return story;
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

	@Override
	public Reply insertReply(Reply reply) {
		return replyRepo.save(reply);
	}

	@Override
	public Reply updateReply(Reply reply) {
		return replyRepo.save(reply);
	}

	@Override
	public void deleteReply(int replySeq) {
		replyRepo.deleteById(replySeq);
	}
}
