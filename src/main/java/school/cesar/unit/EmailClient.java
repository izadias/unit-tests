package school.cesar.unit;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EmailClient implements EmailService {

    Collection<EmailAccount> accounts = new ArrayList<>();
    EmailService emailService;

    private Pattern regexPattern;
    private Matcher regMatcher;

    public EmailClient(){


    }

    public void setEmailService(EmailService emailService){

        this.emailService = emailService;
    }

    public boolean isValidAddress(String emailAddress){
        //validar a conta de email seguindo as regras esperadas
        regexPattern = Pattern.compile("^[(a-zA-Z-0-9-\\_\\+\\.)]+@[(a-z-A-z)]+\\.[(a-zA-z)]{2,3}$");
        regMatcher   = regexPattern.matcher(emailAddress);
        if(regMatcher.matches()) {
            return true;
        } else {
            return false;
        }
    }

    public boolean isValidEmail(Email email){

        Collection<String> toList = email.getTo();
        String from = email.getFrom();
        if(email.getCreationDate().equals(null)){
            return false;
        } else {
            if (!isValidAddress(from)){
                return false;
            } else {
                Iterator<String> it = toList.iterator();
                while (it.hasNext()){
                    String to = it.next();
                    if(isValidAddress(to)){
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public boolean sendEmail(Email email){

        if (isValidEmail(email)){
            this.setEmailService(emailService);
            this.emailService.sendEmail(email);
            return true;
        } else {
            throw new RuntimeException("Email is invalid");
        }
    }

    public boolean isValidPassword (EmailAccount account){
        //verificar senha segundo regra especificada
        if (account.getPassword().length() > 6 && !account.verifyPasswordExpiration()){
            return true;
        }
        return false;
    }

    public Collection<Email> emailList(EmailAccount account) throws RuntimeException {

        if (isValidPassword(account)) {
            Collection<Email> emails = account.getEmails();
            return emails;
        } else {
            throw new RuntimeException();
        }
    }

    public boolean createAccount(EmailAccount account){

        if (account.getPassword().length() <= 6){
            return false;
        } else if (!isValidAddress(account.getUser()+account.getDomain())){
            return false;
        } else {
            account.setLastPasswordUpdate(Instant.now());
            accounts.add(account);
            return true;
        }
    }

}
