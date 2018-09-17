package school.cesar.unit;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Collection;

public class EmailAccountBuilder {

    public String user; //Apenas Letras, números e os seguintes caracteres: ponto (.), linha (_) e traço (-)
    public String domain; //Letras, números e o caractere ponto (.), não podendo ele estar no início, final ou seguido de outro ponto
    public String password;
    public Instant lastPasswordUpdate;
    Collection<Email> emails = new ArrayList<>();

    public EmailAccountBuilder() {

    }

    public EmailAccountBuilder setEmailsList(Collection<Email> emails) {
        this.emails = emails;
        return this;
    }

    public EmailAccountBuilder setUser(String user) {
        this.user = user;
        return this;
    }

    public EmailAccountBuilder setDomain(String domain) {
        this.domain = domain;
        return this;
    }

    public EmailAccountBuilder setPassword(String password) {
        this.password = password;
        return this;
    }

    public EmailAccountBuilder setLastPasswordUpdate(Instant lastPasswordUpdate) {
        this.lastPasswordUpdate = lastPasswordUpdate;
        return this;
    }

    public EmailAccount build() {
        return new EmailAccount(user, domain, password, lastPasswordUpdate, emails);
    }
}
