package com.example.layered_architecture.service;

import com.example.layered_architecture.dto.NotificacaoRecord;
import com.example.layered_architecture.model.EmailModel;
import com.example.layered_architecture.repository.EmailRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
public class EmailService {

    private final EmailRepository emailRepository;
    private final JavaMailSender mailSender;

    public EmailService(EmailRepository emailRepository, JavaMailSender mailSender) {
        this.emailRepository = emailRepository;
        this.mailSender = mailSender;
    }

    @Value("${spring.mail.username}")
    private String emailFrom;

    @Transactional
    public void sendEmail(NotificacaoRecord record) {
        EmailModel emailModel = new EmailModel();

        BeanUtils.copyProperties(record, emailModel);
        emailModel.setSendDateTime(LocalDateTime.now());
        emailModel.setBody(record.text());

        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom(emailFrom);
            message.setTo(emailModel.getEmailTo());
            message.setSubject(emailModel.getSubject());
            message.setText(emailModel.getBody());

            mailSender.send(message);

            emailModel.setStatusEmail("SENT");
        } catch (MailException e) {
            emailModel.setStatusEmail("ERROR");
        } finally {

            emailRepository.save(emailModel);
        }
    }

}

