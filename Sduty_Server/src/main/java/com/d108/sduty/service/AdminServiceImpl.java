package com.d108.sduty.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.d108.sduty.dto.Admin;
import com.d108.sduty.dto.DailyQuestion;
import com.d108.sduty.dto.Notice;
import com.d108.sduty.dto.Qna;
import com.d108.sduty.dto.Story;
import com.d108.sduty.dto.User;
import com.d108.sduty.repo.AdminRepo;
import com.d108.sduty.repo.DailyQuestionRepo;
import com.d108.sduty.repo.NoticeRepo;

@Service
public class AdminServiceImpl implements AdminService {

	@Autowired
	private AdminRepo adminRepo;
	@Autowired
	private NoticeRepo noticeRepo;
	@Autowired
	private DailyQuestionRepo dailyqRepo;
	
	@Override
	public Optional<Admin> getAdmin(String id) {
		return adminRepo.findByid(id);
	}

	@Override
	public boolean registNotice(Notice notice) {
		Notice newNotice = noticeRepo.save(notice);
		return (newNotice!=null);
	}

	@Override
	public Notice updateNotice(Notice notice) {
		Optional<Notice> noticeOp = noticeRepo.findById(notice.getSeq());
		if(noticeOp.isPresent()) {
			Notice originNotice = noticeOp.get();
			originNotice.setContent(notice.getContent());
			return noticeRepo.save(originNotice);
		}
		return null;
	}

	@Override
	public void deleteNotice(int noticeSeq) {
		noticeRepo.deleteById(noticeSeq);
	}

	@Override
	public List<Story> getBadStories() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<User> getBadUsers() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void limitUser() {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean registDailyQuestion(DailyQuestion dailyq) {
		DailyQuestion result = dailyqRepo.save(dailyq);
		return (result!=null);
	}

	@Override
	public List<DailyQuestion> getDailyQuestions() {
		return dailyqRepo.findAll();
	}

	@Override
	public DailyQuestion getDailyQuestionDetail(int questionSeq) {
		Optional<DailyQuestion> dq = dailyqRepo.findById(questionSeq);
		if(dq.isPresent()) {
			return dq.get();
		}
		return null;
	}

	@Override
	public DailyQuestion updateDailyQuestion(DailyQuestion dailyq) {
		Optional<DailyQuestion> dqOp = dailyqRepo.findById(dailyq.getSeq());
		if(dqOp.isPresent()) {
			DailyQuestion origindq = dqOp.get();
			origindq.setContent(dailyq.getContent());
			return dailyqRepo.save(origindq);
		}
		return null;
	}

	@Override
	public void deleteDailyQuestion(int dailyQuestionSeq) {
		dailyqRepo.deleteById(dailyQuestionSeq);
	}

	@Override
	public List<Qna> getQnas() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Qna getQnaDetail() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Qna registAnswer(Qna qna) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Qna updateAnswer(Qna qna) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Qna deleteAnswer(int qnaSeq) {
		// TODO Auto-generated method stub
		return null;
	}

}
