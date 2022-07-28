package com.d108.sduty.service;

import java.util.List;

import com.d108.sduty.dto.Alarm;
import com.d108.sduty.dto.Study;

public interface StudyService {
	public void registStudy(Study study, Alarm alarm);
	public List<Study> getAllStudy();
}
