package com.d108.sduty.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.d108.sduty.dto.Follow;
import com.d108.sduty.repo.FollowRepo;

@Service
public class FollowServiceImpl implements FollowService {

	@Autowired
	private FollowRepo followRepo;
	
	@Override
	public List<Follow> selectFollower(int seq) {
		return followRepo.selectFollower(seq);
	}

	@Override
	public List<Follow> selectFollowee(int seq) {
		return followRepo.selectFollowee(seq);
	}

	@Override
	public int insertFollow(int followerSeq, int followeeSeq) {
		return followRepo.insertFollow(followerSeq, followeeSeq);
	}

	@Override
	public boolean findFollowing(int followerSeq, int followeeSeq) {
		if(followRepo.findFollowing(followerSeq, followeeSeq) != null) {
			return true;
		}
		return false;
	}

	@Override
	public int deleteFollow(int followerSeq, int followeeSeq) {
		return followRepo.deleteFollowing(followerSeq, followeeSeq);
	}
}
