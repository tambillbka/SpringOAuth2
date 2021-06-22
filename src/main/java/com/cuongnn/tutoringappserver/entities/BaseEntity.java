package com.cuongnn.tutoringappserver.entities;

import lombok.Data;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import java.util.Date;

@Data
public abstract class BaseEntity {
    @CreatedDate
    private Date created;

    @CreatedBy
    private String creator;

    @LastModifiedDate
    private Date updated;

    @LastModifiedBy
    private String updater;
}
