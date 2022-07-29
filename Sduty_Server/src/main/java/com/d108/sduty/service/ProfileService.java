package com.d108.sduty.service;

import java.util.Optional;

import com.d108.sduty.dto.Profile;

public interface ProfileService {
	Profile insertProfile(Profile profile) throws Exception;
	boolean checkDupNickname(String nickname) throws Exception;
	Optional<Profile> selectProfile();
}
