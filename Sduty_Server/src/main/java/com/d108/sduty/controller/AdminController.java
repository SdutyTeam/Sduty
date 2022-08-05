package com.d108.sduty.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.d108.sduty.dto.Admin;
import com.d108.sduty.dto.DailyQuestion;
import com.d108.sduty.dto.Notice;
import com.d108.sduty.dto.Qna;
import com.d108.sduty.service.AdminService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/admin")
public class AdminController {
	
	@Autowired
	private AdminService adminService;
	
	@ApiOperation(value = "로그인")
	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody ObjectNode objectNode){
		JsonNode idNode = objectNode.get("id");
		JsonNode passwordNode = objectNode.get("password");
		if(idNode==null || passwordNode==null) {
			return new ResponseEntity<Void>(HttpStatus.UNAUTHORIZED);
		}
		String id = idNode.asText();
		String password = passwordNode.asText();
		Optional<Admin> adminOp = adminService.getAdmin(id);
		if(adminOp.isPresent()) {
			Admin adminObject = adminOp.get();
			if(password.equals(adminObject.getPassword())) {
				return new ResponseEntity<Admin>(adminObject, HttpStatus.OK);
			}
		}
		return new ResponseEntity<Void>(HttpStatus.UNAUTHORIZED);
	}
	
	@ApiOperation(value = "공지사항 등록")
	@PostMapping("/notice")
	public ResponseEntity<?> registNotice(@RequestBody Notice newNotice){
		if(newNotice.getWriterSeq()!=null && newNotice.getContent()!=null) {
			newNotice.setSeq(null);
			if(adminService.registNotice(newNotice)) {
				return new ResponseEntity<Void>(HttpStatus.OK);
			}
		}
		return new ResponseEntity<Void>(HttpStatus.UNAUTHORIZED);
	}
	
	@ApiOperation(value = "공지사항 수정")
	@PutMapping("/notice/{notice_seq}")
	public ResponseEntity<?> updateNotice(@RequestParam int notice_seq, @RequestBody Notice notice){
		if(notice_seq == notice.getSeq()) {
			Notice result = adminService.updateNotice(notice);
			if(result!=null) {
				return new ResponseEntity<Notice>(result, HttpStatus.OK);
			}
		}
		return new ResponseEntity<Void>(HttpStatus.UNAUTHORIZED);
	}
	
	@ApiOperation(value = "공지사항 삭제")
	@DeleteMapping("/notice/{notice_seq}")
	public ResponseEntity<?> deleteNotice(@RequestParam int notice_seq){
		adminService.deleteNotice(notice_seq);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	//신고 관리
	
	//데일리 질문
	@ApiOperation(value = "데일리질문 등록")
	@PostMapping("/question")
	public ResponseEntity<?> registDailyQuestion(@RequestBody DailyQuestion dailyq){
		if(adminService.registDailyQuestion(dailyq)) {
			return new ResponseEntity<Void>(HttpStatus.OK);
		}
		return new ResponseEntity<Void>(HttpStatus.UNAUTHORIZED);
	}
	
	@ApiOperation(value = "데일리질문 목록")
	@GetMapping("/question")
	public ResponseEntity<?> getDailyQuestions(){
		return new ResponseEntity<List<DailyQuestion>>(adminService.getDailyQuestions(), HttpStatus.OK);
	}
	
	@ApiOperation(value = "데일리질문 상세 조회")
	@GetMapping("/question/{question_seq}")
	public ResponseEntity<?> getDailyQuestionDetail(@RequestParam int question_seq){
		DailyQuestion dq = adminService.getDailyQuestionDetail(question_seq);
		if(dq!=null) {
			return new ResponseEntity<DailyQuestion>(dq, HttpStatus.OK);
		}
		return new ResponseEntity<Void>(HttpStatus.UNAUTHORIZED);
	}
	
	@ApiOperation(value = "데일리질문 수정")
	@PutMapping("/question/{question_seq}")
	public ResponseEntity<?> updateDailyQuestion(@RequestParam int question_seq, @RequestBody DailyQuestion dailyq){
		if(question_seq==dailyq.getSeq()) {
			DailyQuestion dq = adminService.updateDailyQuestion(dailyq);
			return new ResponseEntity<DailyQuestion>(dq, HttpStatus.OK);
		}
		return new ResponseEntity<Void>(HttpStatus.UNAUTHORIZED);
	}
	
	@ApiOperation(value = "데일리질문 삭제")
	@DeleteMapping("/question/{question_seq}")
	public ResponseEntity<?> deleteDailyQustion(@RequestParam int question_seq){
		adminService.deleteDailyQuestion(question_seq);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	//1:1 문의
	@ApiOperation(value = "1:1문의 목록 조회")
	@GetMapping("/qna")
	public ResponseEntity<?> getQnas(){
		return new ResponseEntity<List<Qna>>(adminService.getQnas(), HttpStatus.OK);
	}
	
	@ApiOperation(value = "1:1문의 상세 조회")
	@GetMapping("/qna/{qna_seq}")
	public ResponseEntity<?> getQnaDetail(@RequestParam int qna_seq){
		Qna qna = adminService.getQnaDetail(qna_seq);
		if(qna!=null) {
			return new ResponseEntity<Qna>(qna, HttpStatus.OK);
		}
		return new ResponseEntity<Void>(HttpStatus.UNAUTHORIZED);
	}
	
	@ApiOperation(value = "1:1문의 답변 등록 및 수정")
	@PostMapping("/qna/{qna_seq}")
	public ResponseEntity<?> registAnswer(@RequestParam int qna_seq, @RequestBody Qna qna){
		if(qna_seq==qna.getSeq()) {
			Qna result = adminService.registAnswer(qna);
			if(result!=null) {
				return new ResponseEntity<Qna>(result, HttpStatus.OK);
			}
		}
		return new ResponseEntity<Void>(HttpStatus.UNAUTHORIZED);
	}
	
	@ApiOperation(value = "1:1문의 답변 삭제")
	@PutMapping("/qna/{qna_seq}")
	public ResponseEntity<?> updateAnswer(@RequestParam int qna_seq){
		Qna result = adminService.deleteAnswer(qna_seq);
		if(result!=null) {
			return new ResponseEntity<Qna>(result, HttpStatus.OK);
		}
		return new ResponseEntity<Void>(HttpStatus.UNAUTHORIZED);
	}
}
