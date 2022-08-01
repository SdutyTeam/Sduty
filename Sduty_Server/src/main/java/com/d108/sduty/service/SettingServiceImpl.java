package com.d108.sduty.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.d108.sduty.dto.Qna;
import com.d108.sduty.repo.QnaRepo;

@Service
public class SettingServiceImpl implements SettingService {
	
	@Autowired
	private QnaRepo qnaRepo;
	
	@Override
	public int registQna(Qna qna) {
		Qna newQna = qnaRepo.save(qna);
		if(newQna!=null) {return 1;}
		return 0;
	}

	@Override
	public Qna getQnaDetail(int qnaSeq) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Qna updateQna(Qna qna) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Qna deleteQna(int qnaSeq) {
		// TODO Auto-generated method stub
		return null;
	}

}
