/**
 *               Copyright © 2022-2022, Blue Yonder Group, Inc.
 *
 *                              All Rights Reserved
 *
 *                 THIS IS UNPUBLISHED PROPRIETARY SOURCE CODE OF
 *
 *                               BLUE YONDER GROUP, INC.
 *
 *               The copyright notice above does not evidence any actual
 *
 *                    or intended publication of such source code.
 */

package com.poller.jobruner.entity.base;

/*
 * Role Based Access Control.
 * @author Chandra Sekhar Ramamoorthi : 1027583
 * @project plan-sop-rbac
 * @created on 14-May-21
 **/

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.time.LocalDateTime;


@MappedSuperclass
@NoArgsConstructor
@Getter
@Setter
@ToString
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(
    value = {"createdOn", "updatedOn"},
    allowGetters = true
)
public abstract class Auditable implements Serializable {

    @Column(name = "created_on", updatable = false)
    @CreatedDate
    private LocalDateTime createdOn = LocalDateTime.now();

    @Column(name = "updated_on")
    @LastModifiedDate
    private LocalDateTime updatedOn;

    @CreatedBy
    @Column(name = "created_by")
    private String createdBy;

    @LastModifiedBy
    @Column(name = "updated_by")
    private String updatedBy;
}