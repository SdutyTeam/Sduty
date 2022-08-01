package com.d108.sduty.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.d108.sduty.dto.Profile;
import com.d108.sduty.repo.ProfileRepo;

@Service
public class ProfileServiceImpl implements ProfileService {
	@Autowired
	ProfileRepo profileRepo;

	@Override
	public Profile insertProfile(Profile profile) throws Exception {
		return profileRepo.save(profile);
	}

	@Override
	public boolean checkDupNickname(String nickname) throws Exception {
		return profileRepo.existsBynickname(nickname);
	}

	@Override
	public Optional<Profile> selectProfile(int seq) {
		return profileRepo.findById(seq);
	}

	@Override
	public Profile updateProfile(Profile profile) throws Exception {
		return profileRepo.save(profile);
	}
}
