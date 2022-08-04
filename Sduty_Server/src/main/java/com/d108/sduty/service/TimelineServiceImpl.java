package com.d108.sduty.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.d108.sduty.dto.Profile;
import com.d108.sduty.dto.Reply;
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
	public List<Timeline> selectAllTimelines(int userSeq){
		List<Story> storyList = storyRepo.findAllByOrderByRegtimeDesc();
		List<Timeline> timelineList = new ArrayList<Timeline>();
		for(Story s : storyList) {
			Timeline t = new Timeline();
			t.setProfile(getProfile(s.getWriterSeq()));
			t.setStory(s);
			t.setCntReply(replyRepo.countAllByStorySeq(s.getSeq()));
			t.setLikes(likesRepo.existsByUserSeqAndStorySeq(userSeq, s.getSeq()));
			t.setScrap(scrapRepo.existsByUserSeqAndStorySeq(userSeq, s.getSeq()));
			timelineList.add(t);
		}
		return timelineList;
	}

	@Override
	public List<Timeline> selectAllByUserSeqsOrderByRegtime(int userSeq, List<Integer> writerSeq) {
		List<Story> selectedOStory =  storyRepo.findAllByWriterSeqInOrderByRegtimeDesc(writerSeq);
		List<Timeline> tList = new ArrayList<>();
		for(Story s : selectedOStory) {
			Timeline t = new Timeline();
			t.setProfile(getProfile(s.getWriterSeq()));
			t.setStory(s);
			t.setCntReply(replyRepo.countAllByStorySeq(s.getSeq()));
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
	
	public Timeline selectDetailTimeline(int storySeq) {
		Optional<Story> selectedOStory = storyRepo.findById(storySeq);
		if(selectedOStory.isPresent()) {
			Timeline t =  new Timeline();
			Story s = selectedOStory.get();
			int userSeq = s.getWriterSeq();
			t.setProfile(getProfile(userSeq));
			t.setStory(s);
			t.setLikes(likesRepo.existsByUserSeqAndStorySeq(userSeq, storySeq));
			t.setScrap(scrapRepo.existsByUserSeqAndStorySeq(userSeq, storySeq));
			List<Reply> listReply = replyRepo.findAllByStorySeqOrderByRegtimeDesc(storySeq);
			for(Reply r : listReply) {
				r.setProfile(profileRepo.findById((r.getUserSeq())).get());
			}
			t.setReplies(listReply);
			return t;
		}
		
		
		return null;
	}
	
	public Profile getProfile(int userSeq) {
		Optional<Profile> OProfile = profileRepo.findById(userSeq);
		if(OProfile.isPresent()) {
			return OProfile.get();
		}
		return null;
	}

	
}
