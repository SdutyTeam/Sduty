package com.d108.sduty.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.d108.sduty.dto.Notice;
import com.d108.sduty.dto.Qna;
import com.d108.sduty.repo.NoticeRepo;
import com.d108.sduty.repo.QnaRepo;

@Service
public class SettingServiceImpl implements SettingService {
	
	@Autowired
	private QnaRepo qnaRepo;
	@Autowired
	private NoticeRepo noticeRepo;
	
	@Override
	public int registQna(Qna qna) {
		Qna newQna = qnaRepo.save(qna);
		if(newQna!=null) {return 1;}
		return 0;
	}

	@Override
	public Qna getQnaDetail(int qnaSeq) {
		Optional<Qna> qna = qnaRepo.findById(qnaSeq);
		if(qna.isPresent()) {
			return qna.get();
		}
		return null;
	}

	@Override
	public Qna updateQna(int qnaSeq, Qna qna) {
		Optional<Qna> qnaOp = qnaRepo.findById(qnaSeq);
		if(qnaOp.isPresent()) {
			Qna originQna = qnaOp.get();
			originQna.setCategory(qna.getCategory());
			originQna.setTitle(qna.getTitle());
			originQna.setContent(qna.getContent());
			return qnaRepo.save(originQna);
		}
		return null;
	}

	@Override
	public void deleteQna(int qnaSeq) {
		qnaRepo.deleteById(qnaSeq);
	}

	@Override
	public List<Notice> getNoticeList() {
		return noticeRepo.findAll();
	}

}
