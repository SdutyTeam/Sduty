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
	
//	//8시 정각에 알림
//	@Scheduled(cron="0 8 * * * *")
//	public void sendMsg() {
//		List<User> listU = userRepo.findAll();
//		LocalDate today = LocalDate.now();
//		System.out.println(today);
//		for(User u : listU) {
//			if(u.getFcmToken() != null && !u.getFcmToken().equals("")) {
//				Profile p = profileRepo.findById(u.getSeq()).get();
//				Format formatter = new SimpleDateFormat("yyyy-MM-dd");
//			    String d = formatter.format(p.getBirthday());
//			    String title = "생일 축하합니다.";
//			    String content =  "축하합니다";
//			    if(d.equals(today.toString())){
//			    	fcmUtil.send_FCM(u.getFcmToken(), title, content);
//			    }
//				
//			}
//		}
//	}
}
	
	
