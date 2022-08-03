package com.d108.sduty.service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.stereotype.Service;

import com.d108.sduty.dto.Alarm;
import com.d108.sduty.dto.Study;
import com.d108.sduty.dto.User;
import com.d108.sduty.repo.AlarmRepo;
import com.d108.sduty.repo.StudyRepo;
import com.d108.sduty.repo.UserRepo;

@Service
public class StudyServiceImpl implements StudyService {

	@Autowired
	private StudyRepo studyRepo;
	@Autowired
	private AlarmRepo alarmRepo;
	@Autowired
	private UserRepo userRepo;

	@Override
	public List<Study> getAllStudy() {
		return studyRepo.findAll();
	}

	@Override
	public boolean checkStudyName(String name) {
		return studyRepo.existsByName(name);
	}

	@Override
	public void registStudy(Study study, Alarm alarm) {
		//1. 방장 참여
		study.getParticipants().add(userRepo.findBySeq(study.getMasterSeq()).get());
		//study.setJoinNumber(1);// 방장만 참여 => trigger로 바꿀예정
		if(alarm!=null) {
			alarm = alarmRepo.save(alarm);
		}
		study.setAlarm(alarm);
		studyRepo.save(study);
	}

	@Override
	public Study getStudyDetail(int studySeq) {
		return studyRepo.findBySeq(studySeq).get();
	}
	

	@Override
	public Set<Study> getMyStudies(int userSeq) {
		Optional<User> user = userRepo.findBySeq(userSeq);
		if(user.isPresent()) {
			return user.get().getStudies();
		}
		return null;
	}
	

	@Override
	public Study updateStudy(Study study) {
		return studyRepo.save(study);
	}

	@Override
	public boolean deleteStudy(int userSeq, int studySeq) {
		//1. user_seq가 방장인지 확인
		if(studyRepo.findBySeq(studySeq).get().getMasterSeq() != userSeq) {
			return false;
		}
		//2. 삭제
		if(studyRepo.deleteBySeq(studySeq)==0) {
			return false;
		}
		return true;
	}

	@Override
	public List<Study> filterStudy(String category, boolean emptyfilter, boolean camfilter, boolean publicfilter) {
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
		return studyRepo.findAll(spec);
	}
	
	@Override
	public List<Study> searchStudy(String keyword){
		Specification<Study> spec = (root, query, criteriaBuilder)->criteriaBuilder.like(root.get("name"), "%"+keyword+"%");
		return studyRepo.findAll(spec);
	}
	
	public Specification<Study> findCategory(String category){
		return (root, query, criteriaBuilder)->criteriaBuilder.equal(root.get("category"), category);
	}
	
	public Specification<Study> findEmpty(){
		return (root, query, criteriaBuilder)->criteriaBuilder.gt(root.get("limitNumber"), root.get("joinNumber"));
	}
	
	public Specification<Study> findCamStudy(boolean isCamStudy){
		return (root, query, criteriaBuilder)->criteriaBuilder.isNotNull(root.get("roomId"));
	}
	
	public Specification<Study> findPublic(boolean isPublic){
		if(isPublic) {
			return (root, query, criteriaBuilder)->criteriaBuilder.isNull(root.get("password"));
		}
		else {
			return (root, query, criteriaBuilder)->criteriaBuilder.isNotNull(root.get("password"));
		}
		
	}

	@Override
	public boolean joinStudy(int studySeq, int userSeq) {
		Study study = studyRepo.findBySeq(studySeq).get();
		User user = userRepo.findBySeq(userSeq).get();
//		System.out.println(study.getParticipants());
//		System.out.println(user.getStudies());
		//이미 참여 중이면
		if(study.getParticipants().contains(user)) {
			System.out.println("이미 참여중");
			return false;
		}
		//참여 study, user 한 쪽만 해주면, N:M관계에 따라 양방향 자동 적용됨.
		study.getParticipants().add(user);
		studyRepo.save(study);
		return true;
	}

	@Override
	public boolean disjoinStudy(int studySeq, int userSeq) {
		System.out.println(studySeq+", "+userSeq);
		Study study = studyRepo.findBySeq(studySeq).get();
		User user = userRepo.findBySeq(userSeq).get();
//		System.out.println(study.getParticipants());
//		System.out.println(user.getStudies());
		//1. 참여 중인가? & 방장은 탈퇴X 삭제만 가능
		if(!study.getParticipants().contains(user)) {
			System.out.println("스터디 회원이 아니므로 탈퇴할 수 없습니다.");
			return false;
		}
		//2. 탈퇴
		study.getParticipants().remove(user);
		studyRepo.save(study);
		return true;
	}

}
