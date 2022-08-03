package com.d108.sduty.service;

import java.util.List;

import com.d108.sduty.dto.Timeline;

public interface TimelineService {
	public List<Timeline> selectAllByUserSeqsOrderByRegtime(int userSeq, List<Integer> writerSeq);
	public List<Timeline> selectAllByUserSeqsWithTagOrderByRegtime();
}
