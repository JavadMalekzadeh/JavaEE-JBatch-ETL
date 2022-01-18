package com.jmp.nightBatch.services;

import javax.batch.operations.JobOperator;
import javax.batch.runtime.BatchRuntime;
import javax.ejb.Stateless;
import java.util.Properties;

@Stateless
public class DataTransferServiceImpl implements DataTransferService {
    @Override
    public void runDataTransferBatch() {
        JobOperator jobOperator = BatchRuntime.getJobOperator();
        Long executionId = jobOperator.start("dataTransfer", new Properties());
    }
}
