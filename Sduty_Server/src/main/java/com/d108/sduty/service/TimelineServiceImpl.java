package com.d108.sduty.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.d108.sduty.dto.InterestHashtag;
import com.d108.sduty.dto.JobHashtag;
import com.d108.sduty.dto.PagingResult;
import com.d108.sduty.dto.Profile;
import com.d108.sduty.dto.Reply;
import com.d108.sduty.dto.Story;
import com.d108.sduty.dto.StoryInterest;
import com.d108.sduty.dto.Timeline;
import com.d108.sduty.repo.DislikeRepo;
import com.d108.sduty.repo.InterestHashtagRepo;
import com.d108.sduty.repo.JobHashtagRepo;
import com.d108.sduty.repo.LikesRepo;
import com.d108.sduty.repo.ProfileRepo;
import com.d108.sduty.repo.ReplyRepo;
import com.d108.sduty.repo.ScrapRepo;
import com.d108.sduty.repo.StoryInterestHashtagRepo;
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
	
	@Autowired
	private JobHashtagRepo jobHashtagRepo;
	
	@Autowired
	private InterestHashtagRepo interestHashtagRepo;
	
	@Autowired
	private StoryInterestHashtagRepo storyInterestRepo;
	
	@Autowired
	private DislikeRepo dislikeRepo;
	
	@Override
	public List<Timeline> selectAllTimelines(int userSeq){
		List<Story> storyList = storyRepo.findAllByOrderByRegtimeDesc();
		List<Timeline> timelineList = new ArrayList<Timeline>();
		List<Integer> dislikes = dislikeRepo.findAllDislikes(userSeq);
		for(Story s : storyList) {
			Timeline t = new Timeline();
			t.setProfile(getProfile(s.getWriterSeq()));
			t.setStory(s);
			t.setCntReply(replyRepo.countAllByStorySeq(s.getSeq()));
			t.setLikes(likesRepo.existsByUserSeqAndStorySeq(userSeq, s.getSeq()));
			t.setScrap(scrapRepo.existsByUserSeqAndStorySeq(userSeq, s.getSeq()));
			t.setNumLikes(likesRepo.countBystorySeq(s.getSeq()).intValue());
			setInterestList(t, s);
			timelineList.add(t);
		}
		return timelineList;
	}

	@Override
	public List<Timeline> selectAllByUserSeqsOrderByRegtime(int userSeq, List<Integer> writerSeq) {
		List<Story> selectedOStory =  storyRepo.findAllByWriterSeqInOrderByRegtimeDesc(writerSeq);
		List<Timeline> tList = new ArrayList<>();
		List<Integer> dislikes = dislikeRepo.findAllDislikes(userSeq);
		for(Story s : selectedOStory) {
			if(dislikes.contains(s.getSeq()))
				continue;
			Timeline t = new Timeline();
			t.setProfile(getProfile(s.getWriterSeq()));
			t.setStory(s);
			t.setCntReply(replyRepo.countAllByStorySeq(s.getSeq()));
			t.setLikes(likesRepo.existsByUserSeqAndStorySeq(userSeq, s.getSeq()));
			t.setScrap(scrapRepo.existsByUserSeqAndStorySeq(userSeq, s.getSeq()));
			t.setNumLikes(likesRepo.countBystorySeq(s.getSeq()).intValue());
			setInterestList(t, s);
			tList.add(t);
		}
		return tList;
	}
	
	@Override
	public List<Timeline> selectAllByUserSeqsWithTag(int userSeq, List<Integer> writerSeq, String jobName) {
		List<Story> selectedOStory =  storyRepo.findAllByWriterSeqInAndJobHashtagOrderByRegtimeDesc(writerSeq, getJobHashtag(jobName).getSeq());
		List<Timeline> tList = new ArrayList<>();
		List<Integer> dislikes = dislikeRepo.findAllDislikes(userSeq);
		for(Story s : selectedOStory) {
			if(dislikes.contains(s.getSeq()))
				continue;
			Timeline t = new Timeline();
			t.setProfile(getProfile(s.getWriterSeq()));
			t.setStory(s);
			t.setCntReply(replyRepo.countAllByStorySeq(s.getSeq()));
			t.setLikes(likesRepo.existsByUserSeqAndStorySeq(userSeq, s.getSeq()));
			t.setScrap(scrapRepo.existsByUserSeqAndStorySeq(userSeq, s.getSeq()));
			t.setNumLikes(likesRepo.countBystorySeq(s.getSeq()).intValue());
			setInterestList(t, s);
			tList.add(t);
		}
		return tList;
	}
	
	@Override
	public List<Timeline> selectAllByJobName(int userSeq, String jobName){
		JobHashtag JH = getJobHashtag(jobName);
		List<Story> selectedStory = storyRepo.findAllByjobHashtagOrderByRegtimeDesc(JH.getSeq());
		List<Timeline> tList = new ArrayList<>();
		for(Story s : selectedStory) {
			Timeline t = new Timeline();
			t.setProfile(getProfile(s.getWriterSeq()));
			t.setStory(s);
			t.setCntReply(replyRepo.countAllByStorySeq(s.getSeq()));
			t.setLikes(likesRepo.existsByUserSeqAndStorySeq(userSeq, s.getSeq()));
			t.setScrap(scrapRepo.existsByUserSeqAndStorySeq(userSeq, s.getSeq()));
			t.setNumLikes(likesRepo.countBystorySeq(s.getSeq()).intValue());
			setInterestList(t, s);
			tList.add(t);
		}
		return tList;
	}
	
	@Override
	public List<Timeline> selectAllByInterestName(int userSeq, String interestName){
		InterestHashtag IH = getInterestHashtag(interestName);
		List<StoryInterest> SI = storyInterestRepo.findAllByinterestSeq(IH.getSeq());
		List<Integer> siSeqs = new ArrayList<Integer>();
		for(StoryInterest si : SI) {
			siSeqs.add(si.getSeq());
		}
		List<Story> selectedStory = storyRepo.findAllBySeqInOrderByRegtimeDesc(siSeqs);
		List<Timeline> tList = new ArrayList<>();
		for(Story s : selectedStory) {
			Timeline t = new Timeline();
			t.setProfile(getProfile(s.getWriterSeq()));
			t.setStory(s);
			t.setCntReply(replyRepo.countAllByStorySeq(s.getSeq()));
			t.setLikes(likesRepo.existsByUserSeqAndStorySeq(userSeq, s.getSeq()));
			t.setScrap(scrapRepo.existsByUserSeqAndStorySeq(userSeq, s.getSeq()));
			t.setNumLikes(likesRepo.countBystorySeq(s.getSeq()).intValue());
			setInterestList(t, s);
			tList.add(t);
		}
		return tList;
	}
	
	
	@Override
	public Timeline selectRecommandTimeline(int userSeq) {
		Story s = storyRepo.findRecommanded(getProfile(userSeq).getJob());
		Timeline t = new Timeline();
		t.setProfile(getProfile(s.getWriterSeq()));
		t.setStory(s);
		t.setCntReply(replyRepo.countAllByStorySeq(s.getSeq()));
		t.setLikes(likesRepo.existsByUserSeqAndStorySeq(userSeq, s.getSeq()));
		t.setScrap(scrapRepo.existsByUserSeqAndStorySeq(userSeq, s.getSeq()));
		t.setNumLikes(likesRepo.countBystorySeq(s.getSeq()).intValue());
		setInterestList(t, s);

		return t;
	}
	
	@Override
	public Timeline selectDetailTimeline(int storySeq, int userSeq) {
		Optional<Story> selectedOStory = storyRepo.findById(storySeq);
		if(selectedOStory.isPresent()) {
			Timeline t =  new Timeline();
			Story s = selectedOStory.get();				
			t.setProfile(getProfile(s.getWriterSeq()));
			t.setLikes(likesRepo.existsByUserSeqAndStorySeq(userSeq, storySeq));
			t.setScrap(scrapRepo.existsByUserSeqAndStorySeq(userSeq, storySeq));
			t.setNumLikes(likesRepo.countBystorySeq(s.getSeq()).intValue());
			List<Reply> listReply = replyRepo.findAllByStorySeqOrderByRegtimeDesc(storySeq);
			for(Reply r : listReply) {
				r.setProfile(profileRepo.findById((r.getUserSeq())).get());
			}
			t.setReplies(listReply);
			t.setStory(s);			
			setInterestList(t, s);
			return t;
		}
		
		
		return null;
	}
	
	@Override
	public Profile getProfile(int userSeq) {
		Optional<Profile> OProfile = profileRepo.findById(userSeq);
		if(OProfile.isPresent()) {
			return OProfile.get();
		}
		return null;
	}

	@Override
	public void setInterestList(Timeline t, Story s) {
		List<StoryInterest> listSI = storyInterestRepo.findAllBySeq(s.getSeq());
		List<InterestHashtag> listIH = new ArrayList<InterestHashtag>();
		for(StoryInterest i : listSI) {
			if(interestHashtagRepo.findById(i.getInterestSeq()).isPresent())
				listIH.add(interestHashtagRepo.findById(i.getInterestSeq()).get());
		}
		t.setInterestHashtags(listIH);
	}
	
	@Override
	public List<Reply> findAllReplyByStorySeqOrderByRegtimeDesc(int storySeq){
		return replyRepo.findAllByStorySeqOrderByRegtimeDesc(storySeq);
	}

	@Override
	public JobHashtag getJobHashtag(String jobName) {
		return jobHashtagRepo.findByName(jobName);
	}
	
	@Override
	public InterestHashtag getInterestHashtag(String interestName) {
		return interestHashtagRepo.findByName(interestName);
	}

	@Override
	public PagingResult<Timeline> selectAllPagingTimelines(Pageable pageable, int userSeq) {
		Page<Story> storyPage = storyRepo.findAllByOrderByRegtimeDesc(pageable);
		List<Timeline> timelineList = new ArrayList<Timeline>();
		List<Integer> dislikes = dislikeRepo.findAllDislikes(userSeq);
		for(Story s : storyPage) {
			if(dislikes.contains(s.getSeq()))
				continue;
			Timeline t = new Timeline();
			t.setProfile(getProfile(s.getWriterSeq()));
			t.setStory(s);
			t.setCntReply(replyRepo.countAllByStorySeq(s.getSeq()));
			t.setLikes(likesRepo.existsByUserSeqAndStorySeq(userSeq, s.getSeq()));
			t.setScrap(scrapRepo.existsByUserSeqAndStorySeq(userSeq, s.getSeq()));
			t.setNumLikes(likesRepo.countBystorySeq(s.getSeq()).intValue());
			setInterestList(t, s);
			timelineList.add(t);
		}
		PagingResult result = new PagingResult<Timeline>(pageable.getPageNumber(), storyPage.getTotalPages() -1, timelineList);
		return result;
	}
}
