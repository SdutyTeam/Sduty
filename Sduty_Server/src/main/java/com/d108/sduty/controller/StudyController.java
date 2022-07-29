package com.d108.sduty.controller;

import java.util.ArrayList;
import java.util.HashMap;
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

import com.d108.sduty.dto.Alarm;
import com.d108.sduty.dto.Study;
import com.d108.sduty.dto.Task;
import com.d108.sduty.dto.User;
import com.d108.sduty.service.StudyService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/study")
public class StudyController {
	
	@Autowired
	private StudyService studyService;
	
	@ApiOperation(value="스터디 전체 조회")
	@GetMapping("")
	public ResponseEntity<?> getAllStudy(){
		return new ResponseEntity<List<Study>>(studyService.getAllStudy(), HttpStatus.OK);
	}
	
	@ApiOperation(value = "스터디명 중복검사")
	@GetMapping("/check/{study_name}")
	public ResponseEntity<?> checkStudyName(@PathVariable String study_name){
		boolean result = studyService.checkStudyName(study_name);
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("result", result);
		return new ResponseEntity<Map<String, Object>>(resultMap, HttpStatus.OK);
	}
	
	@ApiOperation(value = "스터디 등록")
	@PostMapping("")
	public ResponseEntity<?> registStudy(@RequestBody ObjectNode reqObject){
		ObjectMapper mapper = new ObjectMapper();
		Study study = null;
		Alarm alarm = null;
		try {
			study = mapper.treeToValue(reqObject.get("study"), Study.class);
			JsonNode node = reqObject.get("alarm");
			if(node != null) {
				alarm = mapper.treeToValue(node, Alarm.class);
			}
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		}
		
		studyService.registStudy(study, alarm);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}

	@ApiOperation(value = "내 스터디 목록")
	@GetMapping("/{user_seq}")
	public ResponseEntity<?> myStudyList(@PathVariable int user_seq){
		System.out.println("내 스터디 목록"+ user_seq);
		List<Study> myStudyList = new ArrayList<>();
//		myStudyList.add(new Study(5, 71, "하루 10시간ㄱ", "하루 10시간하실 분만 오세요!", "대학생", 12, 5, "a123", false, "2022-07-27 08:24:31", "공지사항!!", null));
//		myStudyList.add(new Study(1, 23, "서울가자", "열심히 해봐요", "중학생", 10, 2, null, true, "2022-07-26 22:00:12", null, null));
//		myStudyList.add(new Study(1, 23, "서울가자", "열심히 해봐요", "중학생", 10, 2, null, true, "2022-07-26 22:00:12", null, null));
//		myStudyList.add(new Study(1, 23, "서울가자", "열심히 해봐요", "중학생", 10, 2, null, true, "2022-07-26 22:00:12", null, null));
//		myStudyList.add(new Study(1, 23, "서울가자", "열심히 해봐요", "중학생", 10, 2, null, true, "2022-07-26 22:00:12", null, null));
//		myStudyList.add(new Study(1, 23, "서울가자", "열심히 해봐요", "중학생", 10, 2, null, true, "2022-07-26 22:00:12", null, null));
//		myStudyList.add(new Study(1, 23, "서울가자", "열심히 해봐요", "중학생", 10, 2, null, true, "2022-07-26 22:00:12", null, null));
//		myStudyList.add(new Study(1, 23, "서울가자", "열심히 해봐요", "중학생", 10, 2, null, true, "2022-07-26 22:00:12", null, null));
//		myStudyList.add(new Study(1, 23, "서울가자", "열심히 해봐요", "중학생", 10, 2, null, true, "2022-07-26 22:00:12", null, null));
//		myStudyList.add(new Study(1, 23, "서울가자", "열심히 해봐요", "중학생", 10, 2, null, true, "2022-07-26 22:00:12", null, null));
//		myStudyList.add(new Study(1, 23, "서울가자", "열심히 해봐요", "중학생", 10, 2, null, true, "2022-07-26 22:00:12", null, null));
		Map<String, Object> resultMap = new HashMap();
		resultMap.put("my_study_list", myStudyList);
		return new ResponseEntity<Map<String, Object>>(resultMap, HttpStatus.OK);
	}
}
