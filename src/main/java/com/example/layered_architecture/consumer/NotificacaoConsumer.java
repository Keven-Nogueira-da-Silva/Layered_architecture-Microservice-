package com.example.layered_architecture.consumer;

import com.example.layered_architecture.config.RabbitMQConfig;
import com.example.layered_architecture.dto.NotificacaoRecord;
import com.example.layered_architecture.service.EmailService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class NotificacaoConsumer {

    private final EmailService emailService;

    public NotificacaoConsumer(EmailService emailService) {
        this.emailService = emailService;
    }

    @RabbitListener(queues = RabbitMQConfig.QUEUE_NAME)
    public void listen(@Payload NotificacaoRecord record) {

        emailService.sendEmail(record);
        System.out.println("E-mail enviado com sucesso para: " + record.emailTo());
    }

}
