package com.d108.sduty.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.d108.sduty.dto.Report;
import com.d108.sduty.dto.Task;
import com.d108.sduty.dto.User;
import com.d108.sduty.repo.ReportRepo;
import com.d108.sduty.repo.TaskRepo;

@Service
public class ReportServiceImpl implements ReportService {
	@Autowired
	private TaskRepo taskRepo;
	@Autowired
	private ReportRepo reportRepo;

	@Override
	public void startTask(int userSeq) {
		//profile 수정 후, 구현예정
		
	}
	
	@Override
	public void registTask(int userSeq, String date, Task task) {
		//1. 오늘 날짜 report 가져오기(없으면 만들어서 반환)
		Report report = reportRepo.findByDateAndOwnerSeq(date, userSeq);
		
		if(report==null) {
			report = new Report();
			report.setOwnerSeq(userSeq);
			report.setDate(date);
			report = reportRepo.save(report);
		}
		System.out.println(report);
		
		//2. task에 report번호 등록
		task.setReportSeq(report.getSeq());
		taskRepo.save(task);
	}

	@Override
	public Report getReport(int userSeq, String date) {
		Report report = reportRepo.findByDateAndOwnerSeq(date, userSeq);
		System.out.println(report);
		return report;
	}

	
}
