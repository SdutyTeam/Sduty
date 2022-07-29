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
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Optional<Profile> selectProfile() {
		// TODO Auto-generated method stub
		return null;
	}
}
