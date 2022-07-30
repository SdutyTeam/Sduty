package com.d108.sduty.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.EntityGraph;
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

	@Override
	public boolean deleteStudy(int userSeq, int studySeq) {
		//1. user_seq가 방장인지 확인
		if(studyRepo.findBySeq(studySeq).getMasterSeq() != userSeq) {
			return false;
		}
		//2. 삭제
		if(studyRepo.deleteBySeq(studySeq)==0) {
			return false;
		}
		return true;
	}

	@Override
	public List<Study> searchStudy(String category, boolean emptyfilter, boolean camfilter, boolean publicfilter, String keyword) {
		Specification<Study> spec = (root, query, criteriaBuilder)->null;
		if(category!=null) {
			spec = spec.and(findCategory(category));
		}
		if(emptyfilter==true) {
			spec = spec.and(findEmpty());
		}
		if(camfilter==true) {
			spec = spec.and(findCamStudy(camfilter));
		}
		if(publicfilter==true) {
			spec = spec.and(findPublic(publicfilter));
		}
		if(keyword!=null) {
			spec = spec.and(findKeyword(keyword));
		}
		System.out.println(studyRepo.findAll(spec));
		return null;
	}
	
	public Specification<Study> findCategory(String category){
		return (root, query, criteriaBuilder)->criteriaBuilder.equal(root.get("category"), category);
	}
	
	public Specification<Study> findEmpty(){
		return (root, query, criteriaBuilder)->criteriaBuilder.gt(root.get("limitNumber"), root.get("joinNumber"));
	}
	
	public Specification<Study> findCamStudy(boolean isCamStudy){
		return (root, query, criteriaBuilder)->criteriaBuilder.equal(root.get("isCamstudy"), isCamStudy);
	}
	
	public Specification<Study> findPublic(boolean isPublic){
		if(isPublic) {
			return (root, query, criteriaBuilder)->criteriaBuilder.isNull(root.get("password"));
		}
		else {
			return (root, query, criteriaBuilder)->criteriaBuilder.isNotNull(root.get("password"));
		}
		
	}
	
	public Specification<Study> findKeyword(String keyword){
		return (root, query, criteriaBuilder)->criteriaBuilder.like(root.get("name"), "%"+keyword+"%");
	}

}
