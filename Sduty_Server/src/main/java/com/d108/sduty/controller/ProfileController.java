package com.d108.sduty.controller;

import java.util.List;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.d108.sduty.dto.UserAchieve;
import com.d108.sduty.dto.Achievement;
import com.d108.sduty.dto.Follow;
import com.d108.sduty.dto.Profile;
import com.d108.sduty.service.FollowService;
import com.d108.sduty.service.ImageService;
import com.d108.sduty.service.ProfileService;
import com.d108.sduty.service.UserAchieveService;
import com.google.gson.Gson;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;


@Api(value = "Profile")
@RestController
@RequestMapping("/profile")
public class ProfileController {

	@Autowired
	private ProfileService profileService;
	
	@Autowired
	private ImageService imageService;
	
	@Autowired
	private FollowService followService;
	
	@Autowired
	private UserAchieveService userAchieveService;
	
	@ApiOperation(value = "프로필 저장 > Profile > Profile 리턴", response = Profile.class)
	@PostMapping("")
	public ResponseEntity<?> insertProfile(@RequestParam MultipartFile imageFile,  @RequestParam("profile") String json) throws Exception {
		Gson gson = new Gson();
		Profile profile = gson.fromJson(json, Profile.class);
		profile.setImage(imageFile.getOriginalFilename());
		imageService.fileUpload(imageFile);
		Profile result = profileService.insertProfile(profile);
		if(result != null) {
			return new ResponseEntity<Profile>(profile, HttpStatus.OK);
		}
		return new ResponseEntity<Void>(HttpStatus.UNAUTHORIZED);
	}
	
	@ApiOperation(value = "닉네임 중복 확인 > Nickname > Profile 리턴", response = HttpStatus.class)
	@GetMapping("/check/{nickname}")
	public ResponseEntity<?> checkDupNickname(@PathVariable String nickname) throws Exception {
		boolean result = profileService.checkDupNickname(nickname);
		if(result) {
			return new ResponseEntity<Void>(HttpStatus.OK);
		}
		return new ResponseEntity<Void>(HttpStatus.UNAUTHORIZED);
	}
	
	@ApiOperation(value = "프로필 조회 > UserSeq > Profile 리턴", response = Profile.class)
	@GetMapping("/{userSeq}")
	public ResponseEntity<?> selectProfile(@PathVariable int userSeq) {
		Optional<Profile> selectedProfile = profileService.selectProfile(userSeq);
		if(selectedProfile.isPresent()) {
			return new ResponseEntity<Profile>(selectedProfile.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<Void>(HttpStatus.UNAUTHORIZED);
		}
	}
	
	@ApiOperation(value = "프로필 수정 > UserSeq > Profile 리턴", response = Profile.class)
	@PutMapping()
	public ResponseEntity<?> updateProfile(@RequestParam MultipartFile imageFile,  @RequestParam("profile") String json) throws Exception {
		Gson gson = new Gson();
		Profile profile = gson.fromJson(json, Profile.class);
		imageService.deleteFile(profile.getImage());
		profile.setImage(imageFile.getOriginalFilename());
		imageService.fileUpload(imageFile);
		Profile result = profileService.updateProfile(profile);
		if(result != null) {
			return new ResponseEntity<Profile>(profile, HttpStatus.OK);
		}
		return new ResponseEntity<Void>(HttpStatus.UNAUTHORIZED);
	}
	
	@ApiOperation(value = "팔로워 조회 > UserSeq > 팔로워 리턴", response = Follow.class)
	@GetMapping("/follower/{userSeq}")
	public ResponseEntity<?> selectFollower(@PathVariable int userSeq) {
		List<Follow> followers = followService.selectFollower(userSeq);
		if(followers!=null) {
			return new ResponseEntity<List<Follow>>(followers, HttpStatus.OK);
		}
		return new ResponseEntity<Void>(HttpStatus.UNAUTHORIZED);
	}

	@ApiOperation(value = "팔로이 조회 > UserSeq > 팔로이 리턴", response = Follow.class)
	@GetMapping("/followee/{userSeq}")
	public ResponseEntity<?> selectFollowee(@PathVariable int userSeq) {
		List<Follow> followees = followService.selectFollowee(userSeq);
		if(followees!=null) {
			return new ResponseEntity<List<Follow>>(followees, HttpStatus.OK);
		}
		return new ResponseEntity<Void>(HttpStatus.UNAUTHORIZED);
	}
	
	@ApiOperation(value = "유저 신고 > UserSeq > 팔로워 리턴", response = HttpStatus.class)
	@PutMapping("/warning/{userSeq}")
	public ResponseEntity<?> warnUser(@PathVariable int userSeq) throws Exception {
		Optional<Profile> selectedProfile = profileService.selectProfile(userSeq);
		if(selectedProfile.isPresent()) {
			Profile updatingProfile = selectedProfile.get();
			updatingProfile.setWarning(updatingProfile.getWarning() + 1);
			profileService.updateProfile(updatingProfile);
			return new ResponseEntity<Void>(HttpStatus.OK);
		}
		return new ResponseEntity<Void>(HttpStatus.UNAUTHORIZED);
	}
	
	@ApiOperation(value = "업적 달성 > Achievement > ", response = HttpStatus.class)
	@PostMapping("/achievement")
	public ResponseEntity<?> achieveAchievement(@RequestBody UserAchieve userAchieve) {
		int result = userAchieveService.insertUserAchieve(userAchieve.getUserSeq(), userAchieve.getAchievementSeq());
		if(result > 0) {
			return new ResponseEntity<Void>(HttpStatus.OK);
		}
		return new ResponseEntity<Void>(HttpStatus.UNAUTHORIZED);
	}
	
	@ApiOperation(value = "유저 달성 업적 조회 > Achievement > ", response = Achievement.class)
	@GetMapping("/achievement/{userSeq}")
	public ResponseEntity<?> selectUserAchievement(@PathVariable int userSeq) {
		List<Achievement> selectedAchievement = userAchieveService.selectUserAchieve(userSeq); 
		if(selectedAchievement != null) {
			return new ResponseEntity<List<Achievement>>(selectedAchievement, HttpStatus.OK);
		}
		return new ResponseEntity<Void>(HttpStatus.UNAUTHORIZED);
	}
	
	@ApiOperation(value = "업적 상세 정보 조회 > Achievement > ", response = Achievement.class)
	@GetMapping("/achievement/info/{achieveSeq}")
	public ResponseEntity<?> selectAchievementInfo(@PathVariable int achieveSeq) {
		Achievement selectedAchievement = userAchieveService.selectAchievement(achieveSeq); 
		if(selectedAchievement != null) {
			return new ResponseEntity<Achievement>(selectedAchievement, HttpStatus.OK);
		}
		return new ResponseEntity<Void>(HttpStatus.UNAUTHORIZED);
	}
	
	@ApiOperation(value = "팔로우/취소 > Follow > HttpStatus", response = HttpStatus.class)
	@PostMapping("/follow")
	public ResponseEntity<?> doFollow(@RequestBody Follow follow) throws Exception {
		int followerSeq = follow.getFollowerSeq();
		int followeeSeq = follow.getFolloweeSeq();
		boolean alreadyFollowing = followService.findFollowing(followerSeq, followeeSeq);
		int result = 0;
		if(alreadyFollowing) {
			result = followService.deleteFollow(followerSeq, followeeSeq);
		} else {
			result = followService.insertFollow(followerSeq, followeeSeq);
		}
		if(result > 0) {			
			return new ResponseEntity<Void>(HttpStatus.OK);
		}
		return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
	}
}
