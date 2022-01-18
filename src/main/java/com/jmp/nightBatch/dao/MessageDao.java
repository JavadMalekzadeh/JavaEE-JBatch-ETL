package com.jmp.nightBatch.dao;

import com.jmp.nightBatch.domain.Message;
import com.jmp.nightBatch.domain.RetryMessage;

import javax.ejb.Local;
import java.util.Date;
import java.util.List;
@Local
public interface MessageDao {
    List<Message> getAllNotSentMessages();
    List<Message> getNotSentMessagesInDateRange(Date fromDate, Date toDate);
    void saveRetryMessage(RetryMessage message);

}
