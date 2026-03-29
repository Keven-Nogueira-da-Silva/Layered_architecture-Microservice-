package com.example.layered_architecture.model;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Entity
@Table(name = "tb_emails")
public class EmailModel implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private UUID id;

    private String emailTo;
    private String subject;

    @Column(columnDefinition = "TEXT")
    private String body;

    private LocalDateTime sendDateTime;

    private String statusEmail;
}
