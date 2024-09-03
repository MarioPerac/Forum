package org.unibl.etf.sni.forum.models.dto;
import java.io.Serializable;


public class Mail implements Serializable {

    private String to;

    private String subject;
    private String toFullName;
    private String toUsername;

    private String htmlContent;

    public Mail() {

    }

    public Mail(String to, String subject, String toFullName, String htmlContent) {
        this.to = to;
        this.subject = subject;
        this.toFullName = toFullName;
        this.htmlContent = htmlContent;
    }

    public Mail(String to, String subject, String toFullName, String toUsername, String htmlContent) {
        this.to = to;
        this.subject = subject;
        this.toFullName = toFullName;
        this.toUsername = toUsername;
        this.htmlContent = htmlContent;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getHtmlContent() {
        return htmlContent;
    }

    public void setHtmlContent(String htmlContent) {
        this.htmlContent = htmlContent;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getToFullName() {
        return toFullName;
    }

    public void setToFullName(String toFullName) {
        this.toFullName = toFullName;
    }

    public String getToUsername() {
        return toUsername;
    }

    public void setToUsername(String toUsername) {
        this.toUsername = toUsername;
    }
}
