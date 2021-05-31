package com.example.mail;

public class UserHelper {
    String  receiver, subject,message;
    public UserHelper(){}
    public UserHelper(String receiver, String subject, String message) {
        this.receiver = receiver;
        this.subject = subject;
        this.message = message;
    }
    public UserHelper( String receiver, String message) {
        this.receiver = receiver;
        this.message = message;
    }
    public String getReceiver() {
        return receiver;
    }
    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }
    public String getSubject() {
        return subject;
    }
    public void setSubject(String subject) {
        this.subject = subject;
    }
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
}
