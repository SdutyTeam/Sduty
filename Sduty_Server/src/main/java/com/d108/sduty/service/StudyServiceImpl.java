package com.d108.sduty.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.d108.sduty.dto.Alarm;
import com.d108.sduty.dto.Study;
import com.d108.sduty.repo.AlarmRepo;
import com.d108.sduty.repo.StudyRepo;

@Service
public class StudyServiceImpl implements StudyService {

	@Autowired
	private StudyRepo studyRepo;
	@Autowired
	private AlarmRepo alarmRepo;

	@Override
	public List<Study> getAllStudy() {
		return studyRepo.findAll();
	}

	@Override
	public boolean checkStudyName(String name) {
		return studyRepo.findByNameEquals(name).isPresent();
	}

	@Override
	public void registStudy(Study study, Alarm alarm) {
		study.setJoinNumber(1);// 방장만 참여
		Study newStudy = studyRepo.save(study);
		if (alarm != null) {
			alarm.setStudy(newStudy);
			alarmRepo.save(alarm);
		}
	}

	@Override
	public Study getStudyDetail(int studySeq) {
		return studyRepo.findBySeq(studySeq);
	}

}
