package com.d108.sduty.service;

import java.util.List;

import com.d108.sduty.dto.Timeline;

public interface TimelineService {
	public List<Timeline> selectAllByUserSeqsOrderByRegtime(int userSeq, List<Integer> writerSeq);
	public List<Timeline> selectAllByUserSeqsWithTag(int userSeq, List<Integer> writerSeq, int jobSeq);
	public Timeline selectDetailTimeline(int storySeq);
	List<Timeline> selectAllTimelines(int userSeq);
//	public Timeline selectRecommandTimeline(int userSeq);
}
