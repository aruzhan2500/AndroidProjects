package com.example.mail;

public class Email {

    private String emailSender;
    private String emailTitle;
    private String emailDetails;
    private String emailDuration;

    public Email(String emailSender, String emailTitle, String emailDetails, String emailDuration) {
        this.emailSender = emailSender;
        this.emailTitle = emailTitle;
        this.emailDetails = emailDetails;
        this.emailDuration = emailDuration;
    }

    public String getEmailSender() {
        return emailSender;
    }

    public void setEmailSender(String emailSender) {
        this.emailSender = emailSender;
    }

    public String getEmailTitle() {
        return emailTitle;
    }

    public void setEmailTitle(String emailTitle) {
        this.emailTitle = emailTitle;
    }

    public String getEmailDetails() {
        return emailDetails;
    }

    public void setEmailDetails(String emailDetails) {
        this.emailDetails = emailDetails;
    }

    public String getEmailDuration() {
        return emailDuration;
    }

    public void setEmailDuration(String emailDuration) {
        this.emailDuration = emailDuration;
    }
}
