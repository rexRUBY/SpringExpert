package org.example.expert.domain.log.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "log")
public class Log {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long managerId;
    private Long todoId;
    private String message;
    private String ipAddress;

    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    public Log(Long managerId, Long todoId, String message, String ipAddress) {
        this.managerId = managerId;
        this.todoId = todoId;
        this.message = message;
        this.ipAddress = ipAddress;  // IP 주소 저장
    }
}