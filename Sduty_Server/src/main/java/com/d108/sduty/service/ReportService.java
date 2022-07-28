package com.d108.sduty.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.d108.sduty.dto.Report;
import com.d108.sduty.dto.Task;

@Service
public interface ReportService {
	public void startTask(int userSeq);
	public void registTask(int userSeq, String date, Task task);
	public Report getReport(int userSeq, String date);
	
}