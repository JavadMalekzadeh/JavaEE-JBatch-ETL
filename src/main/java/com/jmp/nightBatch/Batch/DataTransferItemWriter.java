package com.jmp.nightBatch.Batch;

import com.jmp.nightBatch.dao.MessageDao;
import com.jmp.nightBatch.domain.Message;
import com.jmp.nightBatch.domain.RetryMessage;
import org.apache.log4j.Logger;

import javax.batch.api.chunk.AbstractItemWriter;
import javax.batch.runtime.context.JobContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.naming.InitialContext;
import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

@Named
public class DataTransferItemWriter extends AbstractItemWriter {
    private Logger logger=Logger.getLogger(DataTransferItemWriter.class);
    @Inject
    JobContext jobContext;
    private MessageDao messageDao;

    @Override
    public void open(Serializable checkpoint) throws Exception {
        getLookupInjection();
        super.open(checkpoint);
    }

    @Override
    public void writeItems(List<Object> messageObjs) throws Exception {
        logger.info("Going to Send messages - executionId "+jobContext.getExecutionId());
        messageObjs
                .stream()
                .map(x->new RetryMessage(((Message)x).getId(),((Message)x).getInsertDate(),(Message)x))
                .forEach(x-> messageDao.saveRetryMessage(x));
        logger.info("All "+messageObjs.size()+ " Sent successfully.");

    }
    private void getLookupInjection(){
        try {
            InitialContext ctx = new InitialContext();
            messageDao = (MessageDao) ctx.lookup("java:global/demo-batch-1.0-SNAPSHOT/MessageDaoImpl!com.jmp.nightBatch.dao.MessageDao");

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

}
