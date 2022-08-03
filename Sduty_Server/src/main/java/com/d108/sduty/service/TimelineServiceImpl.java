package com.d108.sduty.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.d108.sduty.dto.Profile;
import com.d108.sduty.dto.Story;
import com.d108.sduty.dto.Timeline;
import com.d108.sduty.repo.LikesRepo;
import com.d108.sduty.repo.ProfileRepo;
import com.d108.sduty.repo.ReplyRepo;
import com.d108.sduty.repo.ScrapRepo;
import com.d108.sduty.repo.StoryRepo;

@Service
public class TimelineServiceImpl implements TimelineService {

	@Autowired
	private ProfileRepo profileRepo;
	
	@Autowired
	private StoryRepo storyRepo;
	
	@Autowired
	private ReplyRepo replyRepo;
	
	@Autowired
	private LikesRepo likesRepo;
	
	@Autowired
	private ScrapRepo scrapRepo;

	@Override
	public List<Timeline> selectAllByUserSeqsOrderByRegtime(int userSeq, List<Integer> writerSeq) {
		List<Story> selectedOStory =  storyRepo.findAllByWriterSeqInOrderByRegtimeDesc(writerSeq);
		List<Timeline> tList = new ArrayList<>();
		for(Story s : selectedOStory) {
			Timeline t = new Timeline();
			Optional<Profile> OProfile = profileRepo.findById(s.getWriterSeq());
			if(OProfile.isPresent()) {
				t.setProfile(OProfile.get());
			}
			t.setStory(s);
			t.setReply(replyRepo.findAllByStorySeqOrderByRegtimeDesc(s.getSeq()));
			t.setLikes(likesRepo.existsByUserSeqAndStorySeq(userSeq, s.getSeq()));
			t.setScrap(scrapRepo.existsByUserSeqAndStorySeq(userSeq, s.getSeq()));
			tList.add(t);
		}
		return tList;
	}

	@Override
	public List<Timeline> selectAllByUserSeqsWithTagOrderByRegtime() {
		// TODO Auto-generated method stub
		return null;
	}

}
