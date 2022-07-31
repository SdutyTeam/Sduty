package com.d108.sduty.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
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
	
	@ApiOperation(value = "스터디 등록")
	@PostMapping("")
	public ResponseEntity<?> registStudy(@RequestBody ObjectNode reqObject){
		ObjectMapper mapper = new ObjectMapper();
		Study study = null;
		Alarm alarm = null;
		try {
			study = mapper.treeToValue(reqObject.get("study"), Study.class);
			JsonNode node = reqObject.get("alarm");
			if((study.isCamstudy() && node==null)||(!study.isCamstudy()&& node!=null)) {
				//캠스터디인데 알람이 없는경우 || 캠스터디 아닌데 알람이 있는 경우
				return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
			}
			if(node != null) {
				alarm = mapper.treeToValue(node, Alarm.class);
			}
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		}
		
		//1. form 제출 후, 이름 중복 검사
		boolean result = studyService.checkStudyName(study.getName());
		if(result==true) {//중복
			return new ResponseEntity<Void>(HttpStatus.UNAUTHORIZED);
		}
		//2. 등록
		studyService.registStudy(study, alarm);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	@ApiOperation(value = "스터디명 중복검사")
	@GetMapping("/check/{study_name}")
	public ResponseEntity<?> checkStudyName(@PathVariable String study_name){
		boolean result = studyService.checkStudyName(study_name);
		if(result==true) {//중복
			return new ResponseEntity<Void>(HttpStatus.UNAUTHORIZED);
		}
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	@ApiOperation(value="스터디 전체 조회")
	@GetMapping("")
	public ResponseEntity<?> getAllStudy(){
		return new ResponseEntity<List<Study>>(studyService.getAllStudy(), HttpStatus.OK);
	}
	
	@ApiOperation(value="스터디 상세 조회")
	@GetMapping("/detail/{study_seq}")
	public ResponseEntity<?> getStudyDetail(@PathVariable int study_seq){
		return new ResponseEntity<Study>(studyService.getStudyDetail(study_seq), HttpStatus.OK);
	}
	
	@ApiOperation(value="스터디 참여")
	@PostMapping("/participation/{study_seq}/{user_seq}")
	public ResponseEntity<?> joinStudy(@PathVariable int study_seq, @PathVariable int user_seq){
		if(!studyService.joinStudy(study_seq, user_seq)) {
			return new ResponseEntity<Void>(HttpStatus.FORBIDDEN);
		}
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	@ApiOperation(value="스터디 탈퇴")
	@DeleteMapping("/participation/{study_seq}/{user_seq}")
	public ResponseEntity<?> disjoinStudy(@PathVariable int study_seq, @PathVariable int user_seq){
		if(!studyService.disjoinStudy(study_seq, user_seq)) {
			return new ResponseEntity<Void>(HttpStatus.FORBIDDEN);
		}
		return new ResponseEntity<Void>(HttpStatus.OK);
	}

	@ApiOperation(value = "내 스터디 목록")
	@GetMapping("/{user_seq}")
	public ResponseEntity<?> getMyStudies(@PathVariable int user_seq){
		Map<String, Object> resultMap = new HashMap();
		resultMap.put("my_study_list", studyService.getMyStudies(user_seq));
		return new ResponseEntity<Map<String, Object>>(resultMap, HttpStatus.OK);
	}
	
	@ApiOperation(value = "스터디 삭제")
	@DeleteMapping("/{user_seq}/{study_seq}")
	public ResponseEntity<?> updateStudy(@PathVariable int user_seq, int study_seq){
		Map<String, Object> map = new HashMap<String, Object>();
		if(studyService.deleteStudy(user_seq, study_seq)) {
			map.put("result", "삭제되었습니다.");
		}
		else {
			map.put("result", "삭제할 수 없습니다.");
		}
		return new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
	}
	
	@ApiOperation(value = "스터디 검색")
	@GetMapping("/{category}/{emptyfilter}/{camfilter}/{publicfilter}/{keyword}")
	public ResponseEntity<?> searchStudy(String category, @PathVariable boolean emptyfilter, @PathVariable boolean camfilter, @PathVariable boolean publicfilter, String keyword){
		List<Study> resultList = studyService.searchStudy(category, emptyfilter, camfilter, publicfilter, keyword);
		
		return new ResponseEntity<List<Study>>(resultList, HttpStatus.OK);
	}
}
