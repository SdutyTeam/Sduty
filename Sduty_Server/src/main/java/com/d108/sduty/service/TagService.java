package com.d108.sduty.service;

import java.util.List;
import java.util.Optional;

import com.d108.sduty.dto.InterestHashtag;
import com.d108.sduty.dto.JobHashtag;

public interface TagService {
	List<JobHashtag> getAllJobTag();
	Optional<JobHashtag> getJobTagWithSeq(int seq);
	List<InterestHashtag> getAllInterestTag();
	Optional<InterestHashtag> getInterestTagWithSeq(int seq);
}
