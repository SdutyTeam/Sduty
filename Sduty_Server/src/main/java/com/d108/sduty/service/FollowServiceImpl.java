package com.d108.sduty.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.d108.sduty.dto.Follow;
import com.d108.sduty.dto.Profile;
import com.d108.sduty.repo.FollowRepo;
import com.d108.sduty.repo.ProfileRepo;

@Service
public class FollowServiceImpl implements FollowService {

	@Autowired
	private FollowRepo followRepo;
	
	@Autowired ProfileRepo profileRepo;
	
	@Override
	public List<Follow> selectFollower(int seq) {
		List<Optional<Follow>> followers = followRepo.findByFollowerSeq(seq);
		List<Follow> followerList = new ArrayList<>();
		for(Optional<Follow> f : followers) {
			if(f.isPresent())
				followerList.add(f.get());
		}
		return followerList;
	}

	@Override
	public List<Follow> selectFollowee(int seq) {
		List<Optional<Follow>> followees = followRepo.findByFolloweeSeq(seq);
		List<Follow> followeeList = new ArrayList<>();
		for(Optional<Follow> f : followees) {
			if(f.isPresent()) {
				followeeList.add(f.get());
				Profile profile = profileRepo.findById(f.get().getFollowerSeq()).get(); 
				followeeList.get(followeeList.size() - 1).setProfile(profile);
			}
		}
		return followeeList;
	}

	@Override
	public Follow insertFollow(Follow follow) {
		return followRepo.save(follow);
	}

	@Override
	public boolean findFollowing(int followerSeq, int followeeSeq) {
		return followRepo.existsByFollowerSeqAndFolloweeSeq(followerSeq, followeeSeq);
	}

	@Override
	public void deleteFollow(Follow follow) {
		followRepo.delete(follow);
	}
}
