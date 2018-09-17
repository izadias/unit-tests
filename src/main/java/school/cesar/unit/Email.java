package school.cesar.unit;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Collection;

public class Email {

    private Instant creationDate;
    private String from = "";
    private Collection<String> to = new ArrayList<>();
    private Collection<String> cc = new ArrayList<>();
    private Collection<String> bcc = new ArrayList<>();
    private String subject = "";
    private String message = "";

    public Email(Instant creationDate, String from, Collection<String> to,
                 Collection<String> cc, Collection<String> bcc, String subject, String message) {
        this.setCreationDate(creationDate);
        this.setFrom(from);
        this.setTo(to);
        this.setCc(cc);
        this.setBcc(bcc);
        this.setSubject(subject);
        this.setMessage(message);
    }

    public Instant getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Instant creationDate) {
        this.creationDate = creationDate;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public Collection<String> getTo() {
        return to;
    }

    public void setTo(Collection<String> to) {
        this.to = to;
    }

    public Collection<String> getCc() {
        return cc;
    }

    public void setCc(Collection<String> cc) {
        this.cc = cc;
    }

    public Collection<String> getBcc() {
        return bcc;
    }

    public void setBcc(Collection<String> bcc) {
        this.bcc = bcc;
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
