package io.iamkrishna73.ereport.utils;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;

@Component
public class EmailUtils {
    @Autowired
    private JavaMailSender mailSender;

    public void sendEmail(String subject, String body, String to, String fileName, ByteArrayInputStream inputStream){
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);

            // Set email details
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(body, true);

            // Add the Excel attachment
            helper.addAttachment(fileName, new ByteArrayResource(inputStream.readAllBytes()));

            // Send the email
            mailSender.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to send email with attachment", e);
        }
    }

}
