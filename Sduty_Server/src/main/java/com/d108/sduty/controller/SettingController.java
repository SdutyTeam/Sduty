package com.d108.sduty.controller;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.d108.sduty.dto.Qna;
import com.d108.sduty.dto.User;
import com.d108.sduty.service.SettingService;
import com.d108.sduty.service.UserService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/setting")
public class SettingController {
	
	@Autowired
	private UserService userService;
	@Autowired
	private SettingService settingService;
	
	@ApiOperation(value = "내 문의사항 목록 조회")
	@GetMapping("/qna/{user_seq}")
	public ResponseEntity<?> getQnaList(@RequestParam int user_seq) throws Exception{
		Optional<User> userOp = userService.selectUser(user_seq);
		if(userOp.isPresent()) {
			Set<Qna> qnas = userOp.get().getQnas();
			return new ResponseEntity<Set<Qna>>(qnas, HttpStatus.OK);
		}
		return new ResponseEntity<Void>(HttpStatus.UNAUTHORIZED);
	}
	
	@ApiOperation(value = "문의사항 작성하기")
	@PostMapping("/qna")
	public ResponseEntity<?> registQna(@RequestBody Qna qna){
		if(settingService.registQna(qna)==1) {
			return new ResponseEntity<Void>(HttpStatus.OK);
		}
		return new ResponseEntity<Void>(HttpStatus.UNAUTHORIZED);
	}
}
