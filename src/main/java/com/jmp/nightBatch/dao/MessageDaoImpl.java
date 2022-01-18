package com.jmp.nightBatch.dao;

import com.jmp.nightBatch.HibernateUtils;
import com.jmp.nightBatch.domain.Message;
import com.jmp.nightBatch.domain.RetryMessage;
import org.hibernate.Session;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.*;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Stateless
public class MessageDaoImpl implements MessageDao{
    @PersistenceContext
    EntityManager entityManager;


    @Override
    public List<Message> getAllNotSentMessages() {
        Query query=
                entityManager
                .createNativeQuery("select msg.* from message msg left join retrymessage rtrymsg " +
                        "on msg.id=rtrymsg.message_id where rtrymsg.message_id is null",Message.class);
        List<Message> messages=query.getResultList();
        return messages.stream().map(x->(Message)x).collect(Collectors.toList());
    }

    @Override
    public List<Message> getNotSentMessagesInDateRange(Date fromDate, Date toDate) {
        Query query=
                entityManager
                        .createNativeQuery("select msg.* from message msg left join retrymessage rtrymsg " +
                                "on msg.id=rtrymsg.message_id where rtrymsg.message_id is null " +
                                "and msg.insertDate> ?1 and msg.insertDate<= ?2");
        query.setParameter(1,fromDate);
        query.setParameter(2,toDate);
        List<Message> messages=query.getResultList();
        return messages;
    }

    @Override
    public void saveRetryMessage(RetryMessage message) {

        try {
            entityManager.refresh(entityManager.merge(message));
        }catch (Exception ex){
            ex.printStackTrace();
        }
//        entityManager.joinTransaction();
//        Query query=
//                entityManager
//                        .createNativeQuery("insert into retrymessage(insertDate,message_id) values(?1,?2)");
//        query.setParameter(1,message.getInsertDate());
//        query.setParameter(2,message.getMessage().getId());
//        int result=query.executeUpdate();
//        entityManager.getTransaction().commit();
//        System.out.println("-->>"+result);
    }

}
