package com.jmp.nightBatch.jobs;

import org.apache.log4j.Logger;
import org.apache.log4j.spi.LoggerFactory;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import javax.batch.operations.JobOperator;
import javax.batch.runtime.BatchRuntime;
import javax.batch.runtime.context.JobContext;
import javax.inject.Inject;
import java.util.Properties;

public class DataTransferJob implements Job {
    final static Logger logger=Logger.getLogger(DataTransferJob.class);

//    @Inject
//    JobContext jobContext;
    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
//        logger.info("DataTransferJob executed with execution id: "+jobContext.getExecutionId());
        JobOperator jobOperator = BatchRuntime.getJobOperator();
        Long executionId = jobOperator.start("dataTransfer", new Properties());
    }
}
