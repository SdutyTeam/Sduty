package com.d108.sduty.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.d108.sduty.dto.InterestHashtag;
import com.d108.sduty.dto.JobHashtag;
import com.d108.sduty.repo.InterestHashtagRepo;
import com.d108.sduty.repo.JobHashtagRepo;

@Service
public class TagServiceImpl implements TagService {

	@Autowired
	private JobHashtagRepo jobRepo;
	
	@Autowired
	private InterestHashtagRepo interestRepo;
	
	
	@Override
	public List<JobHashtag> getAllJobTag() {
		return jobRepo.findAll();
	}
	
	@Override
	public Optional<JobHashtag> getJobTagWithSeq(int seq) {
		return jobRepo.findById(seq);
	}

	@Override
	public List<InterestHashtag> getAllInterestTag() {
		return interestRepo.findAll();
	}

	@Override
	public Optional<InterestHashtag> getInterestTagWithSeq(int seq) {
		return interestRepo.findById(seq);
	}
}
