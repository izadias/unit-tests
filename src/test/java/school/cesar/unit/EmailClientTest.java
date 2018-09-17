package school.cesar.unit;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import school.cesar.unit.mocking.MockitoExtension;

import java.time.Instant;
import java.time.Period;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

@ExtendWith(MockitoExtension.class)
public class EmailClientTest {

    private Email email;
    private Email badEmail1;
    private Email badEmail2;
    private EmailAccount emailAccount1;
    private EmailAccount emailAccount2;
    private EmailAccount emailAccount3;
    private EmailAccount emailAccount4;
    @InjectMocks
    EmailClient emailClient;
    @Mock
    EmailService service;
    private ArrayList<String> toList;
    private ArrayList<String> badToList;
    private ArrayList<String> ccList;
    private ArrayList<String> bccList;
    private Collection<Email> emails;

    public void setService(EmailService svc) {
        this.service = svc;
    }


    @BeforeEach
    public void setUp() {

        toList = new ArrayList<>(Arrays.asList("xyz@gmail.com", "abc@gmail.com"));
        badToList = new ArrayList<>(Arrays.asList("xyz_gmail.com"));
        ccList = new ArrayList<>(Arrays.asList("xyz@gmail.com", "abc@gmail.com"));
        bccList = new ArrayList<>(Arrays.asList("xyz@gmail.com", "abc@gmail.com"));
        emails = new ArrayList<>();

        email = new EmailBuilder()
                .setCreationDate(Instant.now())
                .setFrom("izaura@gmail.com")
                .setTo(toList)
                .setCc(ccList)
                .setBcc(bccList)
                .setSubject("test")
                .setMessage("test")
                .build();

        emails.add(email);

        badEmail1 = new EmailBuilder()
                .setCreationDate(Instant.now())
                .setFrom("izauça_gmail.com")
                .setTo(toList)
                .setCc(ccList)
                .setBcc(bccList)
                .setSubject("test")
                .setMessage("test")
                .build();

        badEmail2 = new EmailBuilder()
                .setCreationDate(Instant.now())
                .setFrom("izauça@mail.com")
                .setTo(badToList)
                .setCc(ccList)
                .setBcc(bccList)
                .setSubject("test")
                .setMessage("test")
                .build();

        emailAccount1 = new EmailAccountBuilder()
                .setUser("izaura")
                .setDomain("@gmail.com")
                .setPassword("1234567")
                .setLastPasswordUpdate(Instant.now())
                .setEmailsList(emails)
                .build();

        emailAccount2 = new EmailAccountBuilder()
                .setUser("izaura")
                .setDomain("@gmail.com")
                .setPassword("1234567")
                .setLastPasswordUpdate(Instant.now().plus(Period.ofDays(100)))
                .build();

        emailAccount3 = new EmailAccountBuilder()
                .setUser("izaura")
                .setDomain("@gmail.com")
                .setPassword("123")
                .setLastPasswordUpdate(Instant.now().plus(Period.ofDays(8)))
                .build();

        emailAccount4 = new EmailAccountBuilder()
                .setUser("izaura$$$")
                .setDomain("@gmail.com")
                .setPassword("1234567")
                .setLastPasswordUpdate(Instant.now().plus(Period.ofDays(8)))
                .build();

        // emailClient = new EmailClient();
    }

    @Test
    public void verifyPasswordExpired() {
        Assertions.assertTrue(emailAccount2.verifyPasswordExpiration());
    }

    @Test
    public void isValidEmailAddress() {
        Assertions.assertTrue(emailClient.isValidAddress(email.getFrom()));
    }

    @Test
    public void isNotValidEmailAddress() {
        Assertions.assertFalse(emailClient.isValidAddress(badEmail1.getFrom()));
    }

    @Test
    public void isValidEmail() {
        Assertions.assertTrue(emailClient.isValidEmail(email));
    }

    @Test
    public void isNotValidEmail_address() {
        Assertions.assertFalse(emailClient.isValidEmail(badEmail1));
    }

    @Test
    public void isNotValidEmail_toList() {
        Assertions.assertFalse(emailClient.isValidEmail(badEmail2));
    }

    @Test
    public void isValidPassword() {
        Assertions.assertTrue(emailClient.isValidPassword(emailAccount1));
    }

    @Test
    public void isNotValidPassword() {
        Assertions.assertFalse(emailClient.isValidPassword(emailAccount3));
    }

    @Test
    public void canCreateAccount_true() {
        Assertions.assertTrue(emailClient.createAccount(emailAccount1));
    }

    @Test
    public void canCreateAccount_false_badPassword() {
        Assertions.assertFalse(emailClient.createAccount(emailAccount3));
    }

    @Test
    public void canCreateAccount_false_badAddress() {
        Assertions.assertFalse(emailClient.createAccount(emailAccount4));
    }

    @Test
    public void canSendEmail_true() {
        emailClient.setEmailService(service);
        Assertions.assertTrue(emailClient.sendEmail(email));
    }

    @Test
    public void canSendEmail_false() {
        emailClient.setEmailService(service);
        Assertions.assertThrows(RuntimeException.class, () -> {
            emailClient.sendEmail(badEmail1);
        });
    }

    @Test
    public void returnsEmailsList() {
        Assertions.assertNotNull(emailClient.emailList(emailAccount1));
    }

    @Test
    public void exceptionWhenReturningEmailsList() throws RuntimeException {

        Assertions.assertThrows(RuntimeException.class, () -> {
            emailClient.emailList(emailAccount2);
        });
    }

}
