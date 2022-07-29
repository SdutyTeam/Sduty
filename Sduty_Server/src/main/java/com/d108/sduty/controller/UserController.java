package com.d108.sduty.controller;

import java.util.Date;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.d108.sduty.dto.AuthInfo;
import com.d108.sduty.dto.User;
import com.d108.sduty.service.KakaoLoginService;
import com.d108.sduty.service.NaverLoginService;
import com.d108.sduty.service.UserService;
import com.d108.sduty.utils.TimeCompare;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value = "User")
@RestController
@RequestMapping("/user")
public class UserController {
	@Autowired
	private UserService userService;
	@Autowired
	private KakaoLoginService kService;
	@Autowired
	private NaverLoginService nService;
	
	@ApiOperation(value = "로그인 > id, pass 확인 > User 리턴", response = User.class)
	@PostMapping("")
	public ResponseEntity<?> selectUser(@RequestBody User user) throws Exception {
		Optional<User> maybeUser = userService.selectUserById(user.getId());
		if(maybeUser.isPresent()) {
			User selectedUser = maybeUser.get();
			if(selectedUser.getPass().equals(user.getPass())) {
				selectedUser.setPass("");
				return new ResponseEntity<User>(selectedUser, HttpStatus.OK);
			}
		}
		return new ResponseEntity<Void>(HttpStatus.UNAUTHORIZED);
	}

	@ApiOperation(value = "회원가입 > id 중복확인 > 200/401 리턴", response = HttpStatus.class)
	@GetMapping("/join/{id}")
	public ResponseEntity<?> isUsedId(@PathVariable String id) throws Exception {
		boolean result = userService.isUsedId(id);
//		System.out.println(id);
//		System.out.println(result);

		if(result) {			
			return new ResponseEntity<Void>(HttpStatus.OK);
		}
		return new ResponseEntity<Void>(HttpStatus.UNAUTHORIZED);
	}
	
	@ApiOperation(value = "회원가입 > User 리턴", response = HttpStatus.class)
	@PostMapping("/join")
	public ResponseEntity<?> insertUser(@RequestBody User user) throws Exception {
		User result = userService.insertUser(user);
		if(result != null) {			
			return new ResponseEntity<User>(result, HttpStatus.OK);
		}
		return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
	}

	@ApiOperation(value = "카카오 로그인 : token > User 리턴", response = HttpStatus.class)
	@PostMapping("/kakao")
	public ResponseEntity<?> kakaoLogin(@RequestBody String token) throws Exception {
		Map<String, Object> userInfo = kService.getUserInfo(token);
		String email = userInfo.get("email").toString();		
		
		Optional<User> maybeUser = userService.selectUserById(email);
		if(maybeUser.isPresent()) {
			User selectedUser = maybeUser.get();
			return new ResponseEntity<User>(selectedUser, HttpStatus.OK);
		} else {
			return new ResponseEntity<Void>(HttpStatus.UNAUTHORIZED);
		}
	}

	@ApiOperation(value = "네이버 로그인 : token > User 리턴", response = HttpStatus.class)
	@PostMapping("/naver")
	public ResponseEntity<?> naverLogin(@RequestBody String token) throws Exception {
		Map<String, Object> userInfo = nService.getUserInfo(token);
		String email = userInfo.get("email").toString();	
		
		Optional<User> maybeUser = userService.selectUserById(email);
		if(maybeUser.isPresent()) {
			User selectedUser = maybeUser.get();
			return new ResponseEntity<User>(selectedUser, HttpStatus.OK);
		}else {
			return new ResponseEntity<Void>(HttpStatus.UNAUTHORIZED);
		}		
	}

	@ApiOperation(value = "카카오 회원가입 : token > User 리턴", response = HttpStatus.class)
	@PostMapping("/kakao/join")
	public ResponseEntity<?> kakaoJoin(@RequestBody String token) throws Exception {
		Map<String, Object> userInfo = kService.getUserInfo(token);
		String email = userInfo.get("email").toString();
		String name = userInfo.get("name").toString();
		User user = new User();
		user.setId(email);
		user.setPass("");
		user.setName(name);
		user.setEmail(email);
		//User result = userService.insertUser(user);  // User 정보만 보내주고 /join으로 카카오, 네이버 둘 다 가입
		if(user != null) {
			return new ResponseEntity<User>(user, HttpStatus.OK);
		}
		return new ResponseEntity<Void>(HttpStatus.UNAUTHORIZED);	
	}

	@ApiOperation(value = "네이버 회원가입 : token > User 리턴", response = HttpStatus.class)
	@PostMapping("/naver/join")
	public ResponseEntity<?> naverJoin(@RequestBody String token) throws Exception {
		Map<String, Object> userInfo = nService.getUserInfo(token);
		String email = userInfo.get("email").toString();
		String name = userInfo.get("name").toString();
		User user = new User();
		user.setId(email);
		user.setPass("");
		user.setName(name);
		user.setEmail(email);
		//User result = userService.insertUser(user);
		if(user != null) {
			return new ResponseEntity<User>(user, HttpStatus.OK);
		}
		return new ResponseEntity<Void>(HttpStatus.UNAUTHORIZED);		
	}
	
	@ApiOperation(value = "회원정보 수정 > User/401 리턴", response = HttpStatus.class)
	@PutMapping("/{seq}")
	public ResponseEntity<?> updateUserInfo(@PathVariable int seq, @RequestBody User user) throws Exception {
		user.setSeq(seq);
		User selectUser = userService.selectUser(seq).get();
		if(user.getPass() != null) selectUser.setPass(user.getPass());
		user.setTel(selectUser.getTel());
		user.setEmail(selectUser.getEmail());
		user.setFcmToken(selectUser.getFcmToken());
		if(userService.updateUser(user) != null)
			return new ResponseEntity<Void>(HttpStatus.OK);
		else
			return new ResponseEntity<Void>(HttpStatus.UNAUTHORIZED);
	}
	
	@ApiOperation(value = "회원정보 조회 > User/401 리턴", response = HttpStatus.class)
	@GetMapping("/{seq}")
	public ResponseEntity<?> getUserInfo(@PathVariable int seq) throws Exception {
		Optional<User> maybeUser = userService.selectUser(seq);
		if(maybeUser.isPresent()) {
			User user = maybeUser.get();
			user.setFcmToken("");
			user.setPass("");
			return new ResponseEntity<User>(user, HttpStatus.OK);
		} else 
			return new ResponseEntity<Void>(HttpStatus.UNAUTHORIZED);
	}

	@ApiOperation(value = "아이디 찾기 > String/401 리턴", response = HttpStatus.class)
	@GetMapping("/id/{tel}")
	public ResponseEntity<?> findIdByTel(@PathVariable String tel) throws Exception {
		Optional<User> user = userService.selectByTel(tel);
		if(user.isPresent())
			return new ResponseEntity<String>(user.get().getId(), HttpStatus.OK);
		else
			return new ResponseEntity<Void>(HttpStatus.UNAUTHORIZED);
	}
	

	@ApiOperation(value = "비밀번호 변경 > 200/401 리턴", response = HttpStatus.class)
	@PutMapping("/pwd/{id}")
	public ResponseEntity<?> setPwdById(@PathVariable String id, @RequestBody String password) throws Exception {
		//인증이 안되면 수정이 안되므로 거의 not null 확실
		User user = userService.selectUserById(id).get();
		user.setPass(password);
		User result = userService.updatePassword(user);
		if(result != null) {
			return new ResponseEntity<User>(result, HttpStatus.OK);
		}
		return new ResponseEntity<Void>(HttpStatus.UNAUTHORIZED); 
	}

	
	@ApiOperation(value = "인증정보 저장 > 200/401 리턴", response = HttpStatus.class)
	@PostMapping("/auth")
	public ResponseEntity<?> authTest(@RequestBody AuthInfo authInfo) throws Exception {
		userService.deleteAuthInfo(authInfo);
		int result = userService.insertAuthInfo(authInfo);
		if(result > 0) {
			return new ResponseEntity<Void>(HttpStatus.OK);
		}
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT); 
	}

	@ApiOperation(value = "인증정보 확인 > 200/401 리턴", response = HttpStatus.class)
	@PostMapping("/auth/check")
	public ResponseEntity<?> getAuthCode(@RequestBody AuthInfo authInfo) throws Exception {
		AuthInfo selectedCode = userService.selectAuthInfo(authInfo.getTel());
		System.out.println("들어옴");
		System.out.println(selectedCode);
		System.out.println(new Date(System.currentTimeMillis()));
		if(selectedCode != null) {
			if(selectedCode.getCode().equals(authInfo.getCode())) { // 인증코드 비교
				if(TimeCompare.compare(selectedCode.getExpire())) { // 인증 만료시간 확인
					userService.deleteAuthInfo(authInfo);					
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


