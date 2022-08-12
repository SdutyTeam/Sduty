package com.d108.sduty.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

	@Transactional
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
	
	@Transactional
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

	@Transactional
	@Override
	public User updatePassword(User user) throws Exception {
		return userRepo.save(user);
	}
	
	@Transactional
	@Override
	public void deleteUser(int seq) throws Exception {
		userRepo.deleteById(seq);
	}

	@Override
	public int insertAuthInfo(AuthInfo authInfo) throws Exception {
		return authInfoRepo.insertAuthInfo(authInfo);
	}

	@Transactional
	@Override
	public int updateAuthInfo(AuthInfo authInfo) throws Exception {
		return authInfoRepo.updateAuthInfo(authInfo);
	}

	@Override
	public AuthInfo selectAuthInfo(String tel) throws Exception {
		return authInfoRepo.selectAuthInfo(tel);
	}

	@Transactional
	@Override
	public void deleteAuthInfo(AuthInfo authInfo) throws Exception {
		authInfoRepo.deleteAuthInfo(authInfo);
	}



}
