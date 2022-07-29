package com.d108.sduty.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.d108.sduty.dto.AuthInfo;
import com.d108.sduty.dto.User;
import com.d108.sduty.repo.AuthInfoRepo;
import com.d108.sduty.repo.UserRepo;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	UserRepo userRepo;
	
	@Autowired
	AuthInfoRepo authInfoRepo;

	@Override
	public User insertUser(User user) throws Exception {
		return userRepo.save(user);
	}

	@Override
	public Optional<User> selectUserById(String id) throws Exception {
		return userRepo.findByid(id);
	}

	@Override
	public boolean isUsedId(String id) throws Exception {
		return userRepo.existsByid(id);
	}
	
	@Override
	public User updateUser(User user) throws Exception {
		return userRepo.save(user);
	}
	
	@Override
	public Optional<User> selectUser(int seq) throws Exception {
		return userRepo.findById(seq);
	}
	

	@Override
	public Optional<User> selectByTel(String tel) throws Exception {
		return userRepo.findByTel(tel);
	}

	@Override
	public User updatePassword(User user) throws Exception {
		return userRepo.save(user);
	}
	

	@Override
	public int insertAuthInfo(AuthInfo authInfo) throws Exception {
		return authInfoRepo.insertAuthInfo(authInfo);
	}

	@Override
	public int updateAuthInfo(AuthInfo authInfo) throws Exception {
		return authInfoRepo.updateAuthInfo(authInfo);
	}

	@Override
	public AuthInfo selectAuthInfo(String tel) throws Exception {
		return authInfoRepo.selectAuthInfo(tel);
	}

	@Override
	public void deleteAuthInfo(AuthInfo authInfo) throws Exception {
		authInfoRepo.deleteAuthInfo(authInfo);
	}

}
