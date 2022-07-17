package com.d108.sduty.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.d108.sduty.dto.User;
import com.d108.sduty.service.KakaoLoginService;
import com.d108.sduty.service.NaverLoginService;
import com.d108.sduty.service.TestService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value = "User")
@RestController
@RequestMapping("/user")
public class TestController {
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
	@GetMapping("/{id}")
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
	
	@ApiOperation(value = "test> List<String>리턴", response = User.class)
	@GetMapping("/test")
	public ResponseEntity<?> test(){
		List<String> list = new ArrayList<String>();
		list.add("권용준");
		list.add("김남희");
		list.add("김정윤");
		list.add("정봉진");
		list.add("최영희");
		list.add("편예린");
		
		return new ResponseEntity<List<String>>(list, HttpStatus.OK);
	}
	
	@PostMapping("/kakao")
	public ResponseEntity<?> kakaoLogin(@RequestBody String token){
		Map<String, Object> userInfo = kService.getUserInfo(token);
		String email = userInfo.get("email").toString();
		String nickname = userInfo.get("nickname").toString();
		User user= tService.selectUser(email);
		if(user != null) {
			return new ResponseEntity<User>(user, HttpStatus.OK);
		}else {
//			회원가입
//			User newUser = new User(email, "", nickname, email);
//			tService.insertUser(newUser);
			return new ResponseEntity<Void>(HttpStatus.UNAUTHORIZED);
		}
	}
	
	@PostMapping("/naver")
	public ResponseEntity<?> naverLogin(@RequestBody String token){
		Map<String, Object> userInfo = nService.getUserInfo(token);
		String email = userInfo.get("email").toString();
		String nickname = userInfo.get("nickname").toString();
		User user= tService.selectUser(email);
		if(user != null) {
			return new ResponseEntity<User>(user, HttpStatus.OK);
		}else {
//			회원가입
//			User newUser = new User(email, "", nickname, email);
//			tService.insertUser(newUser);
			return new ResponseEntity<Void>(HttpStatus.UNAUTHORIZED);
		}		
	}
	
	@PostMapping("/kakao/join")
	public ResponseEntity<?> kakaoJoin(@RequestBody String token){
		Map<String, Object> userInfo = kService.getUserInfo(token);
		String email = userInfo.get("email").toString();
		String nickname = userInfo.get("nickname").toString();
		User user = new User(email, "", nickname, email);
		int result = tService.insertUser(user);
		if(result > 0) {
			return new ResponseEntity<User>(user, HttpStatus.OK);
		}
		return new ResponseEntity<Void>(HttpStatus.UNAUTHORIZED);		
	}
	
	@PostMapping("/naver/join")
	public ResponseEntity<?> naverJoin(@RequestBody String token){
		Map<String, Object> userInfo = nService.getUserInfo(token);
		String email = userInfo.get("email").toString();
		String nickname = userInfo.get("nickname").toString();
		User user = new User(email, "", nickname, email);
		int result = tService.insertUser(user);
		if(result > 0) {
			return new ResponseEntity<User>(user, HttpStatus.OK);
		}
		return new ResponseEntity<Void>(HttpStatus.UNAUTHORIZED);		
	}

	
}
