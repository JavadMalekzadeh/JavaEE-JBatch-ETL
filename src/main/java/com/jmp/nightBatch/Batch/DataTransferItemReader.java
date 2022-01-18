package com.jmp.nightBatch.Batch;

import com.jmp.nightBatch.dao.MessageDao;
import com.jmp.nightBatch.domain.Message;
import org.apache.log4j.Logger;

import javax.batch.api.chunk.AbstractItemReader;
import javax.batch.runtime.context.JobContext;
import javax.ejb.EJB;
import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Named;
import javax.naming.InitialContext;
import java.io.Serializable;
import java.util.List;

@Named
@Dependent
public class DataTransferItemReader extends AbstractItemReader {
    private Logger logger=Logger.getLogger(DataTransferItemReader.class);


    private MessageDao messageDao;

    @Inject
    JobContext jobContext;

    private List<Message> messages;
    int index=0;

    @Override
    public void open(Serializable serializable) throws Exception {
        logger.info("DataTransfer Batch was executed with Execution ID: "+jobContext.getExecutionId());
        getLookupInjection();
        messages= messageDao.getAllNotSentMessages();
        logger.info("There are "+messages.size()+" messages not sent yet.");
        index=-1;
    }

    @Override
    public Object readItem() throws Exception {
        index++;
        if(index<messages.size()) {
            Message msg =(Message) messages.get(index);
            logger.info("sending message with ID to processor" + msg.getId());
            return msg;
        }
        return null;
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
