package com.d108.sduty.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.d108.sduty.dto.InterestHashtag;
import com.d108.sduty.dto.Profile;
import com.d108.sduty.dto.UserInterest;
import com.d108.sduty.repo.InterestHashtagRepo;
import com.d108.sduty.repo.ProfileRepo;
import com.d108.sduty.repo.UserInterestRepo;

@Service
public class ProfileServiceImpl implements ProfileService {
	@Autowired
	private ProfileRepo profileRepo;

	@Autowired
	private UserInterestRepo userInterestRepo;
	
	@Autowired
	private InterestHashtagRepo interestHashtag;
	
	@Override
	public Profile insertProfile(Profile profile) throws Exception {
		Profile p = profileRepo.save(profile);
		if(p.getInterestHashtags() != null) {
			if(!profile.getInterestHashtagSeqs().isEmpty()) {
				for(int i : profile.getInterestHashtagSeqs()) {
					userInterestRepo.save(new UserInterest(profile.getUserSeq(), i));
				}
			}
		}
		return p;
	}

	@Override
	public boolean checkDupNickname(String nickname) throws Exception {
		return profileRepo.existsBynickname(nickname);
	}

	@Override
	public Profile selectProfile(int userSeq) {
		List<UserInterest> listUI = userInterestRepo.findAllByUserSeq(userSeq);
		List<Integer> listInterest = new ArrayList<Integer>();
		for(UserInterest ui : listUI) {
			listInterest.add(ui.getInterestSeq());
		}
		List<InterestHashtag> listIH = new ArrayList<InterestHashtag>();
		for(Integer i : listInterest) {
			if(interestHashtag.findById(i).isPresent())
				listIH.add(interestHashtag.findById(i).get());
		}
		Optional<Profile> OProfile =  profileRepo.findById(userSeq);
		Profile p;
		if(OProfile.isPresent()) {
			p = OProfile.get();
			p.setInterestHashtags(listIH);
			return p;
		}
		return null;
	}

	@Override
	public Profile updateProfile(Profile profile) throws Exception {
		int userSeq = profile.getUserSeq();
		for(Integer ui : profile.getInterestHashtagSeqs()) {
			userInterestRepo.delete(new UserInterest(userSeq, ui));
		}
		Profile p = profileRepo.save(profile);
		if(p != null) {
			if(!profile.getInterestHashtagSeqs().isEmpty()) {
				for(int i : profile.getInterestHashtagSeqs()) {
					userInterestRepo.save(new UserInterest(profile.getUserSeq(), i));
				}
			}
		}
		return p;
	}
}
