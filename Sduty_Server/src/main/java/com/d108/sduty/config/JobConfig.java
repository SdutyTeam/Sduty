package com.d108.sduty.config;
import static org.quartz.JobBuilder.newJob;

import javax.annotation.PostConstruct;

import org.quartz.CronScheduleBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import com.d108.sduty.dto.Job;

@Configuration
public class JobConfig {
	
	@Autowired
	private Scheduler scheduler;
	
	@PostConstruct//프로그램 실행 시, 실행
	public void start() {
		//JobDetail jobDetail = buildJobDetail(Job.class, new HashMap());
		//JobDataMap map1 = new JobDataMap(Collections.singletonMap("num", 1));
		JobDetail jobDetail = buildJobDetail("alarm1", "Study", 1);
		JobDetail jobDetail2 = buildJobDetail("alarm2", "Study", 2);
		try {
			scheduler.scheduleJob(jobDetail, buildJobTrigger("0/10 * * * * ?"));
			scheduler.scheduleJob(jobDetail2, buildJobTrigger("0/10 * * * * ?"));
		} catch(SchedulerException e) {
			e.printStackTrace();
		}
	}
	
	 public Trigger buildJobTrigger(String scheduleExp){
	        return TriggerBuilder.newTrigger()
	                .withSchedule(CronScheduleBuilder.cronSchedule(scheduleExp)).build();
	    }
	
	public JobDetail buildJobDetail(String name, String group, int n) {
		JobDetail jobDetail = JobBuilder.newJob(Job.class)
				.withIdentity(name, group)
				.usingJobData("studySeq", n)
				.withDescription("Study Scheduling")
				.build();
		return jobDetail;
//		JobDataMap jobDataMap = new JobDataMap();
//		jobDataMap.putAll(params);
//		return newJob(job).usingJobData(jobDataMap).build();
	}
}
