package com.jmp.nightBatch.domain;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "retrymessage")
public class RetryMessage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "insertDate")
    private Date insertDate;

    @ManyToOne
    private Message message;

    public RetryMessage() {
    }

    public RetryMessage(Long id, Date insertDate, Message message) {
        this.id = id;
        this.insertDate = insertDate;
        this.message = message;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getInsertDate() {
        return insertDate;
    }

    public void setInsertDate(Date insertDate) {
        this.insertDate = insertDate;
    }

    public Message getMessage() {
        return message;
    }

    public void setMessage(Message message) {
        this.message = message;
    }
}
