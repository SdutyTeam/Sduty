package com.d108.sduty.utils;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.d108.sduty.dto.Profile;
import com.d108.sduty.dto.User;
import com.d108.sduty.repo.ProfileRepo;
import com.d108.sduty.repo.UserRepo;

@Component
public class BirthdayScheduler {
	
	@Autowired
	private ProfileRepo profileRepo;
	
	@Autowired
	private UserRepo userRepo;
	
	@Scheduled(cron="0 * * * * *")
	public void sendMsg() {
		List<User> listU = userRepo.findAll();
		for(User u : listU) {
			if(u.getFcmToken() != null && !u.getFcmToken().equals("")) {
				Profile p = profileRepo.findById(u.getSeq()).get();
				System.out.println(p.getBirthday());
			}
		}
	}
}
	
	
