package com.d108.sduty.repo;

import com.d108.sduty.dto.User;

public interface TestRepo {
	
	public int insertUser(User user);
	
	public User selectUser(String id);
	
	public int idUsedId(String id);
}
