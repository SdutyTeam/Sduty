package com.d108.sduty.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.domain.Specification;

import com.d108.sduty.dto.Alarm;
import com.d108.sduty.dto.Study;
import com.d108.sduty.dto.User;

public interface StudyService {
	public List<Study> getAllStudy();
	public boolean checkStudyName(String name);
	public void registStudy(Study study, Alarm alarm);
	public Study getStudyDetail(int studySeq);
	public Alarm getAlarm(int studySeq);
	public Set<Study> getMyStudies(int userSeq);
	public boolean deleteStudy(int userSeq, int studySeq);
	public List<Study> searchStudy(String category, boolean emptyfilter, boolean camfilter, boolean publicfilter, String keyword);
	public boolean joinStudy(int studySeq, int userSeq);
	public boolean disjoinStudy(int studySeq, int userSeq);
}
