package com.example.usermanagement.entity;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.ZonedDateTime;

@MappedSuperclass
@Data
@EntityListeners({AuditingEntityListener.class})
public class BaseEntity {
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    private String id;

    @CreatedDate
    @Column(updatable = false,nullable = false)
    private ZonedDateTime creationDate;

    @LastModifiedDate
    @Column(nullable = false)
    private ZonedDateTime modificationDate;

    private Boolean active = true;
}
