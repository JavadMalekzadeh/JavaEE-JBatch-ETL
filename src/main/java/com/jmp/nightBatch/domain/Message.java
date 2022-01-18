package com.jmp.nightBatch.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "message")
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "body")
    private String body;

    @Column(name = "insertDate")
    private Date insertDate;

    @OneToMany(mappedBy = "message")
    private List<RetryMessage> retryMessages=new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Date getInsertDate() {
        return insertDate;
    }

    public void setInsertDate(Date insertDate) {
        this.insertDate = insertDate;
    }

    public List<RetryMessage> getRetryMessages() {
        return retryMessages;
    }

    public void setRetryMessages(List<RetryMessage> retryMessages) {
        this.retryMessages = retryMessages;
    }
}
