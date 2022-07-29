package com.d108.sduty.service;

import java.util.List;

import org.springframework.data.jpa.domain.Specification;

import com.d108.sduty.dto.Alarm;
import com.d108.sduty.dto.Study;

public interface StudyService {
	public List<Study> getAllStudy();
	public boolean checkStudyName(String name);
	public void registStudy(Study study, Alarm alarm);
	public Study getStudyDetail(int studySeq);
	public boolean deleteStudy(int userSeq, int studySeq);
	public List<Study> searchStudy(String category, boolean emptyfilter, boolean camfilter, boolean publicfilter, String keyword);
	public Specification<Study> findCamStudy(boolean isCamStudy);
}
