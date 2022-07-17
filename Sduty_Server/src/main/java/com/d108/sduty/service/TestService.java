package com.d108.sduty.service;

import com.d108.sduty.dto.User;

public interface TestService {
	
	public int insertUser(User user);
	
	public User selectUser(String id);
	
	public int isUsedId(String id);
}
