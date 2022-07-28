package com.d108.sduty.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.d108.sduty.dto.Task;
import com.d108.sduty.repo.TaskRepo;

@Service
public class TaskServiceImpl implements TaskService {
	
	@Autowired
	TaskRepo taskRepo;
	
	@Override
	public Task getTask(int taskSeq) {
		return taskRepo.findBySeq(taskSeq);
	}

}
