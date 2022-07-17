package com.d108.sduty.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.d108.sduty.dto.User;
import com.d108.sduty.repo.TestRepo;


@Service
public class TestServiceImpl implements TestService {

	@Autowired
	TestRepo testRepo;

	@Override
	public int insertUser(User user) {
		return testRepo.insertUser(user);
	}

	@Override
	public User selectUser(String id) {
		return testRepo.selectUser(id);
	}

	@Override
	public int isUsedId(String id) {
		return testRepo.idUsedId(id);
	}


}
