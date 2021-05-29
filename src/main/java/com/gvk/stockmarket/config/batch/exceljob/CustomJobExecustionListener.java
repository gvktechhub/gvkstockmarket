package com.gvk.stockmarket.config.batch.exceljob;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.stereotype.Component;

@Component
public class CustomJobExecustionListener implements JobExecutionListener {
	
	private static final Logger log = LoggerFactory.getLogger(CustomJobExecustionListener.class);

	@Override
	public void beforeJob(JobExecution jobExecution) {
		log.debug("Calling beforeJob in CustomJobExecustionListener");
		log.info("Batch started with status "+jobExecution.getStatus());
	}

	@Override
	public void afterJob(JobExecution jobExecution) {
		log.debug("Calling afterJob in CustomJobExecustionListener");
		log.info("Batch ended with status"+jobExecution.getStatus());
		log.info("Started at "+jobExecution.getStartTime()+", Ended at "+jobExecution.getEndTime());
	}

}
