package com.d108.sduty.repo;

import com.d108.sduty.dto.AuthInfo;
import com.d108.sduty.dto.User;

public interface TestRepo {
	
	public int insertUser(User user);
	
	public User selectUser(String id);
	
	public int isUsedId(String id);
	
	public int insertAuthInfo(AuthInfo authInfo);
	
	public int updateAuthInfo(AuthInfo authInfo);
	
	public AuthInfo selectAuthInfo(String phone);
	
	public int deleteAuthInfo(AuthInfo authInfo);
}
