package com.d108.sduty.service;

import java.util.List;

import com.d108.sduty.dto.Alarm;
import com.d108.sduty.dto.Study;

public interface StudyService {
	public List<Study> getAllStudy();
	public boolean checkStudyName(String name);
	public void registStudy(Study study, Alarm alarm);
}
