package school.cesar.unit;

import java.time.Instant;
import java.time.Period;
import java.util.ArrayList;
import java.util.Collection;

public class EmailAccount {

    public String user; //Apenas Letras, números e os seguintes caracteres: ponto (.), linha (_) e traço (-)
    public String domain; //Letras, números e o caractere ponto (.), não podendo ele estar no início, final ou seguido de outro ponto
    public String password;
    public Instant lastPasswordUpdate;
    Collection<Email> emails = new ArrayList<>();


    public EmailAccount(String user, String domain, String password,
                        Instant lastPasswordUpdate, Collection<Email> emails) {
        this.user = user;
        this.domain = domain;
        this.password = password;
        this.lastPasswordUpdate = lastPasswordUpdate;
        this.emails = emails;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Instant getLastPasswordUpdate() {
        return lastPasswordUpdate;
    }

    public void setLastPasswordUpdate(Instant lastPasswordUpdate) {
        this.lastPasswordUpdate = lastPasswordUpdate;
    }

    public Collection<Email> getEmails() {
        return emails;
    }

    public void setEmails(Collection<Email> emails) {
        this.emails = emails;
    }

    public boolean verifyPasswordExpiration() {

        Instant expiration = Instant.now().plus(Period.ofDays(90));
        if (this.lastPasswordUpdate.isAfter(expiration)) {
            return true;
        }

        return false;
    }


}
