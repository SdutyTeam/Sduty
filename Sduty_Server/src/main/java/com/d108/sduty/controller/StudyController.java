package com.d108.sduty.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.d108.sduty.dto.Study;

@RestController
@RequestMapping("/study")
public class StudyController {

	@GetMapping("/{user_seq}")
	public ResponseEntity<?> myStudyList(@PathVariable int user_seq){
		System.out.println("내 스터디 목록"+ user_seq);
		List<Study> myStudyList = new ArrayList<>();
		myStudyList.add(new Study(5, 71, "하루 10시간ㄱ", "하루 10시간하실 분만 오세요!", "대학생", 12, 5, "a123", false, "2022-07-27 08:24:31"));
		myStudyList.add(new Study(1, 23, "서울가자", "열심히 해봐요", "중학생", 10, 2, null, true, "2022-07-26 22:00:12"));
		myStudyList.add(new Study(1, 23, "서울가자", "열심히 해봐요", "중학생", 10, 2, null, true, "2022-07-26 22:00:12"));
		myStudyList.add(new Study(1, 23, "서울가자", "열심히 해봐요", "중학생", 10, 2, null, true, "2022-07-26 22:00:12"));
		myStudyList.add(new Study(1, 23, "서울가자", "열심히 해봐요", "중학생", 10, 2, null, true, "2022-07-26 22:00:12"));
		myStudyList.add(new Study(1, 23, "서울가자", "열심히 해봐요", "중학생", 10, 2, null, true, "2022-07-26 22:00:12"));
		myStudyList.add(new Study(1, 23, "서울가자", "열심히 해봐요", "중학생", 10, 2, null, true, "2022-07-26 22:00:12"));
		myStudyList.add(new Study(1, 23, "서울가자", "열심히 해봐요", "중학생", 10, 2, null, true, "2022-07-26 22:00:12"));
		myStudyList.add(new Study(1, 23, "서울가자", "열심히 해봐요", "중학생", 10, 2, null, true, "2022-07-26 22:00:12"));
		myStudyList.add(new Study(1, 23, "서울가자", "열심히 해봐요", "중학생", 10, 2, null, true, "2022-07-26 22:00:12"));
		Map<String, Object> resultMap = new HashMap();
		resultMap.put("my_study_list", myStudyList);
		return new ResponseEntity<Map<String, Object>>(resultMap, HttpStatus.OK);
	}
}
