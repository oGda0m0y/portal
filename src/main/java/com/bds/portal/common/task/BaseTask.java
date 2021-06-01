package com.bds.portal.common.task;

import org.apache.log4j.Logger;
import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public abstract class BaseTask<K> implements Job {
	private static Logger logger = Logger.getLogger(BaseTask.class);

	public void execute(JobExecutionContext context) throws JobExecutionException {
		JobDataMap map = context.getJobDetail().getJobDataMap();
		logger.info("###Start [process]..." + this.getClass().getName() + "####");
		try {
			this.process(map);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void init() {
		logger.info("###Start Job [initialization]..." + this.getClass().getName() + "###");
		this.doStart();
	}

	public abstract void doStart();

	public abstract void process(JobDataMap map) throws Exception;
	
	public abstract Thread getThread(K task);
	

}
