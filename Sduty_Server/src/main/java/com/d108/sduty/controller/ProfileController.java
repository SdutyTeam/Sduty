package com.d108.sduty.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.d108.sduty.dto.Profile;
import com.d108.sduty.dto.User;
import com.d108.sduty.service.ProfileService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;


@Api(value = "Profile")
@RestController
@RequestMapping("/profile")
public class ProfileController {

	@Autowired
	ProfileService profileService;
	
	@ApiOperation(value = "프로필 저장 > Profile > Profile 리턴", response = Profile.class)
	@PostMapping()
	public Profile insertProfile(@RequestBody Profile profile) {
		return null;
	}
	
	@ApiOperation(value = "닉네임 중복 확인 > Nickname > Profile 리턴", response = HttpStatus.class)
	@GetMapping("/check/{nickname}")
	public Profile checkDupNickname(@PathVariable String nickname) {
		return null;
	}
	
	@ApiOperation(value = "프로필 조회 > UserSeq > Profile 리턴", response = Profile.class)
	@GetMapping("/{seq}")
	public Profile selectProfile(@PathVariable int seq) {
		return null;
	}
	
	@ApiOperation(value = "프로필 수정 > UserSeq > Profile 리턴", response = Profile.class)
	@PutMapping("/{seq}")
	public Profile updateProfile(@PathVariable int seq, @RequestBody Profile profile) {
		return null;
	}
	
	@ApiOperation(value = "팔로워 조회 > UserSeq > 팔로워 리턴", response = Profile.class)
	@GetMapping("/follower/{seq}")
	public Profile selectFollower(@PathVariable int seq) {
		return null;
	}

	@ApiOperation(value = "팔로이 조회 > UserSeq > 팔로워 리턴", response = Profile.class)
	@GetMapping("/followee/{seq}")
	public Profile selectFollowee(@PathVariable int seq) {
		return null;
	}
	
	@ApiOperation(value = "유저 신고 > UserSeq > 팔로워 리턴", response = HttpStatus.class)
	@GetMapping("/warning/{seq}")
	public Profile warnUser(@PathVariable int seq) {
		return null;
	}
	
	@ApiOperation(value = "업적 달성 > Achievement > ", response = Profile.class)
	@PostMapping("/achievement/{seq}")
	public Profile achieveAchievement(@PathVariable int seq) {
		return null;
	}
	
	@ApiOperation(value = "업적 조회 > Achievement > ", response = Profile.class)
	@GetMapping("/achievement/{seq}")
	public Profile selectAchievement(@PathVariable int seq) {
		return null;
	}
	
	@ApiOperation(value = "대표 업적 설정 > Achievement > ", response = Profile.class)
	@PutMapping("/achievement/{seq}")
	public Profile selectRepAcheive(@PathVariable int seq) {
		return null;
	}
	
	@ApiOperation(value = "프로필 공개 범위 > User Seq > ", response = Profile.class)
	@PostMapping("/public/{seq}")
	public Profile updatePublicity(@PathVariable int seq) {
		return null;
	}

	@ApiOperation(value = "팔로우/취소 > User Seq > ", response = Profile.class)
	@PostMapping("/follow/{seq}")
	public Profile doFollow(@PathVariable int seq) {
		return null;
	}
}
