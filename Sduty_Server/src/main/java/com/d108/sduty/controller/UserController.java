package com.d108.sduty.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.dbcp2.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.d108.sduty.dto.AuthInfo;
import com.d108.sduty.dto.User;
import com.d108.sduty.service.KakaoLoginService;
import com.d108.sduty.service.NaverLoginService;
import com.d108.sduty.service.TestService;
import com.d108.sduty.utils.TimeCompare;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value = "User")
@RestController
@RequestMapping("/user")
public class UserController {
	@Autowired
	private TestService tService;
	@Autowired
	private KakaoLoginService kService;
	@Autowired
	private NaverLoginService nService;
	
	@ApiOperation(value = "로그인 > id, pass 확인 > User 리턴", response = User.class)
	@PostMapping("")
	public ResponseEntity<?> selectUser(@RequestBody User user){
		User selectedUser = tService.selectUser(user.getId());
		System.out.println(selectedUser);
		if(selectedUser.getPass().equals(user.getPass())) {
			selectedUser.setPass("");
			return new ResponseEntity<User>(selectedUser, HttpStatus.OK);
		}
		return new ResponseEntity<Void>(HttpStatus.UNAUTHORIZED);
	}

	@ApiOperation(value = "회원가입 > id 중복확인 > 200/401 리턴", response = HttpStatus.class)
	@GetMapping("/join/{id}")
	public ResponseEntity<?> isUsedId(@PathVariable String id){
		int result = tService.isUsedId(id);
		if(result > 0) {			
			return new ResponseEntity<Void>(HttpStatus.OK);
		}
		return new ResponseEntity<Void>(HttpStatus.UNAUTHORIZED);
	}
	
	@ApiOperation(value = "회원가입 > User 리턴", response = HttpStatus.class)
	@PostMapping("/join")
	public ResponseEntity<?> insertUser(@RequestBody User user){
		int result = tService.insertUser(user);
		if(result > 0) {			
			return new ResponseEntity<User>(HttpStatus.OK);
		}
		return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
	}
		
	@PostMapping("/kakao")
	public ResponseEntity<?> kakaoLogin(@RequestBody String token){
		Map<String, Object> userInfo = kService.getUserInfo(token);
		String email = userInfo.get("email").toString();		
		User user= tService.selectUser(email);
		if(user != null) {
			return new ResponseEntity<User>(user, HttpStatus.OK);
		}else {
			return new ResponseEntity<Void>(HttpStatus.UNAUTHORIZED);
		}
	}
	
	@PostMapping("/naver")
	public ResponseEntity<?> naverLogin(@RequestBody String token){
		Map<String, Object> userInfo = nService.getUserInfo(token);
		String email = userInfo.get("email").toString();		
		User user= tService.selectUser(email);				
		if(user != null) {
			return new ResponseEntity<User>(user, HttpStatus.OK);
		}else {
			return new ResponseEntity<Void>(HttpStatus.UNAUTHORIZED);
		}		
	}
	
	@PostMapping("/kakao/join")
	public ResponseEntity<?> kakaoJoin(@RequestBody String token){
		Map<String, Object> userInfo = kService.getUserInfo(token);
		String email = userInfo.get("email").toString();
		String name = userInfo.get("name").toString();
		User user = new User(email, "", name, email);
//		int result = tService.insertUser(user);  // User 정보만 보내주고 /join으로 카카오, 네이버 둘 다 가입
		if(user != null) {
			return new ResponseEntity<User>(user, HttpStatus.OK);
		}
		return new ResponseEntity<Void>(HttpStatus.UNAUTHORIZED);		
	}
	
	@PostMapping("/naver/join")
	public ResponseEntity<?> naverJoin(@RequestBody String token){
		Map<String, Object> userInfo = nService.getUserInfo(token);
		String email = userInfo.get("email").toString();
		String name = userInfo.get("name").toString();
		String mobile = userInfo.get("mobile").toString().replace("-", "");		
		User user = new User(email, "", name, mobile, email);
//		int result = tService.insertUser(user);// User 정보만 보내주고 /join으로 카카오, 네이버 둘 다 가입
		if(user != null) {
			return new ResponseEntity<User>(user, HttpStatus.OK);
		}
		return new ResponseEntity<Void>(HttpStatus.UNAUTHORIZED);		
	}
	
	@PostMapping("/auth")
	public ResponseEntity<?> authTest(@RequestBody AuthInfo authInfo){
		tService.deleteAuthInfo(authInfo);
		int result = tService.insertAuthInfo(authInfo);
		if(result > 0) {
			return new ResponseEntity<Void>(HttpStatus.OK);
		}
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT); 
	}
	
	@PostMapping("/auth/check")
	public ResponseEntity<?> getAuthCode(@RequestBody AuthInfo authInfo){
		AuthInfo selectedCode = tService.selectAuthInfo(authInfo.getPhone());
		System.out.println(selectedCode);
		System.out.println(new Date(System.currentTimeMillis()));
		if(selectedCode != null) {
			if(selectedCode.getAuthcode().equals(authInfo.getAuthcode())) { // 인증코드 비교
				if(TimeCompare.compare(selectedCode.getExpire_time())) { // 인증 만료시간 확인
					tService.deleteAuthInfo(authInfo);					
					return new ResponseEntity<Void>(HttpStatus.OK); // 인증완료
				}
				else {
					return new ResponseEntity<Void>(HttpStatus.GONE); // 인증시간 만료
				}
			}
		}		
		return new ResponseEntity<Void>(HttpStatus.UNAUTHORIZED); // 인증번호 불일치
	}
	
}

