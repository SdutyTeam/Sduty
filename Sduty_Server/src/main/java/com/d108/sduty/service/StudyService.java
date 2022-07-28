package com.d108.sduty.service;

import com.d108.sduty.dto.Alarm;
import com.d108.sduty.dto.Study;

public interface StudyService {
	public void registStudy(Study study, Alarm alarm);
}
