package com.FleetX.model;

import java.sql.Timestamp;

public class MessageModel {
    private int messageId;
    private int userId;
    private String email;     
    private String subject;
    private String content;
    private Timestamp sentAt;

    // Constructors
    public MessageModel() {}

    public MessageModel(int messageId, int userId, String email, String subject, String content, Timestamp sentAt) {
        this.messageId = messageId;
        this.userId = userId;
        this.email = email;
        this.subject = subject;
        this.content = content;
        this.sentAt = sentAt;
    }
    
    public MessageModel(String content, Timestamp sentAt) {
        this.content = content;
        this.sentAt = sentAt;
    }

    // Getters and Setters
    public int getMessageId() {
        return messageId;
    }

    public void setMessageId(int messageId) {
        this.messageId = messageId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Timestamp getSentAt() {
        return sentAt;
    }

    public void setSentAt(Timestamp sentAt) {
        this.sentAt = sentAt;
    }
}
