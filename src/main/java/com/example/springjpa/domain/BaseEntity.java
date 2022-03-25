package com.example.springjpa.domain;

import com.example.springjpa.domain.listener.Auditable;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;
import java.util.Locale;

@Data
@MappedSuperclass
@EntityListeners(value = AuditingEntityListener.class)
public class BaseEntity implements Auditable {
    @CreatedDate
    @Column(columnDefinition = "datetime(6) default now(6)", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(columnDefinition = "datetime(6) default now(6)", nullable = false)
    private LocalDateTime updatedAt;
}
