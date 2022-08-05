package com.d108.sduty.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.d108.sduty.dto.Profile;
import com.d108.sduty.dto.UserInterest;
import com.d108.sduty.repo.ProfileRepo;
import com.d108.sduty.repo.UserInterestRepo;

@Service
public class ProfileServiceImpl implements ProfileService {
	@Autowired
	private ProfileRepo profileRepo;

	@Autowired
	private UserInterestRepo userInterestRepo;
	
	@Override
	public Profile insertProfile(Profile profile) throws Exception {
		Profile p = profileRepo.save(profile);
		if(p != null) {
			if(!profile.getInterestHashtag().isEmpty()) {
				for(UserInterest ui : profile.getInterestHashtag()) {
					userInterestRepo.save(ui);
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
		Optional<Profile> OProfile =  profileRepo.findById(userSeq);
		Profile p;
		if(OProfile.isPresent()) {
			p = OProfile.get();
			p.setInterestHashtag(listUI);
			return p;
		}
		return null;
	}

	@Override
	public Profile updateProfile(Profile profile) throws Exception {
		int userSeq = profile.getUserSeq();
		for(UserInterest ui : userInterestRepo.findAllByUserSeq(userSeq)) {
			userInterestRepo.delete(ui);
		}
		Profile p = profileRepo.save(profile);
		if(p != null) {
			if(!profile.getInterestHashtag().isEmpty()) {
				for(UserInterest ui : profile.getInterestHashtag()) {
					userInterestRepo.save(ui);
				}
			}
		}
		return p;
	}
}
