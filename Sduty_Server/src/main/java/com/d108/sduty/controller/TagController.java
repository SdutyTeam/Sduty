package com.d108.sduty.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.d108.sduty.dto.InterestHashtag;
import com.d108.sduty.dto.JobHashtag;
import com.d108.sduty.dto.User;
import com.d108.sduty.service.TagService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value = "Tag")
@RestController
@RequestMapping("/tag")
public class TagController {

	@Autowired
	private TagService tagService;
	
	@ApiOperation(value = "모든 직업 태그 조회 > JobHashtag", response = JobHashtag.class)
	@GetMapping("/job")
	public ResponseEntity<?> getAllJobTags(){
		List<JobHashtag> list = tagService.getAllJobTag();
		
		if(list != null) {
			return new ResponseEntity<List<JobHashtag>>(list, HttpStatus.OK);
		}
		return new ResponseEntity<Void>(HttpStatus.UNAUTHORIZED);
	}
	
	@ApiOperation(value = "시퀀스로 직업 태그 조회 > JobHashtag", response = JobHashtag.class)
	@GetMapping("/job/{tagSeq}")
	public ResponseEntity<?> getJobTagWithSeq(@PathVariable int tagSeq){
		Optional<JobHashtag> tag = tagService.getJobTagWithSeq(tagSeq);
		if(tag.isPresent()) {
			return new ResponseEntity<JobHashtag>(tag.get(), HttpStatus.OK);
		}
		return new ResponseEntity<Void>(HttpStatus.UNAUTHORIZED);
	}
	
	@ApiOperation(value = "모든 관심사 태그 조회 > InterestHashtag 리턴", response = InterestHashtag.class)
	@GetMapping("/interest")
	public ResponseEntity<?> getAllInterestTags(){
		List<InterestHashtag> list = tagService.getAllInterestTag();
		
		if(list != null) {
			return new ResponseEntity<List<InterestHashtag>>(list, HttpStatus.OK);
		}
		return new ResponseEntity<Void>(HttpStatus.UNAUTHORIZED);
	}
	
	@ApiOperation(value = "시퀀스로 관심사 태그 조회 > InterestHashtag 리턴", response = InterestHashtag.class)
	@GetMapping("/interest/{tagSeq}")
	public ResponseEntity<?> getInterestTagWtihSeq(@PathVariable int tagSeq){
		Optional<InterestHashtag> tag = tagService.getInterestTagWithSeq(tagSeq);
		if(tag.isPresent()) {
			return new ResponseEntity<InterestHashtag>(tag.get(), HttpStatus.OK);
		}
		return new ResponseEntity<Void>(HttpStatus.UNAUTHORIZED);
	}
}
