package com.shoplify.shoplify.service;


import com.shoplify.shoplify.exception.EmailFailureException;
import com.shoplify.shoplify.interfaces.EmailInterface;
import com.shoplify.shoplify.models.LocalUser;
import com.shoplify.shoplify.models.VerificationToken;
import org.apache.naming.factory.SendMailFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cglib.core.Local;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService implements EmailInterface {

    @Value("${email.from}")
    private String fromAddress;

    @Value("${app.frontend.url}")
    private String url;

    JavaMailSender javaMailSender;
    public EmailService(JavaMailSender javaMailSender){
        this.javaMailSender = javaMailSender;
    }

    private SimpleMailMessage makeMailMessage(){
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom(fromAddress);

        return simpleMailMessage;
    }

    @Override
    public void sendVerificationEmail(VerificationToken verificationToken) throws  EmailFailureException{
        SimpleMailMessage message = makeMailMessage();
        message.setTo(verificationToken.getUser().getEmail());
        message.setSubject("Verify you email to activate you account!");
        message.setText("Please follow the link below to verify you email to activate you acoount." + url + "/auth/verify?token="+
                verificationToken.getToken());

        try{
            javaMailSender.send(message);
        }catch (MailException ex){
            throw new EmailFailureException();
        }

    }

    @Override
    public void sendPasswrodResetEmail(LocalUser user, String token) throws EmailFailureException {
        SimpleMailMessage message = makeMailMessage();
        message.setTo(user.getEmail());
        message.setSubject("Reset your password");
        message.setText("This link is your password reset token.\n" + url + "/auth/reset?token="+token);

        try{
            javaMailSender.send(message);
        }
        catch (MailException ex){
            throw new EmailFailureException();
        }
    }
}
