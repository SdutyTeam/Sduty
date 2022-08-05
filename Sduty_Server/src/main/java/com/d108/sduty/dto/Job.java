package com.d108.sduty.dto;

import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.stereotype.Component;

import com.d108.sduty.service.StudyService;

@Component
public class Job extends QuartzJobBean{
	//private static final Logger log = LoggerFactory.getLogger(Job.class);

	@Autowired
	private SchedulerFactoryBean schedulerFactoryBean;
	@Autowired
	private StudyService studyService;
	
    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        System.out.println("여기 push알림");
        int studySeq = (int) jobExecutionContext.getJobDetail().getJobDataMap().get("studySeq");
        System.out.println(studyService.getStudyDetail(studySeq).getParticipants());;
    	//log.info("매 시간 실행 될 작업 작성 공간");
//    	Scheduler scheduler = schedulerFactoryBean.getScheduler();
//    	JobKey jobKey = buildJobDetail("alarm1", "Study", 1).getKey();
//    	try {
//    		System.out.println(" scheduler 정지");
//			//scheduler.pauseJob(jobKey);
//    		scheduler.deleteJob(jobKey);
//		} catch (SchedulerException e) {
//			e.printStackTrace();
//		}
    }
    
    public JobDetail buildJobDetail(String name, String group, int n) {
		JobDetail job = JobBuilder.newJob(Job.class)
				.withIdentity(name, group)
				.usingJobData("studySeq", n)
				.withDescription("Study Scheduling")
				.build();
		return job;
	}

}
