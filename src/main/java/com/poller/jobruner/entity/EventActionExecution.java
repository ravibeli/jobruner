package com.poller.jobruner.entity;

import com.poller.jobruner.entity.base.Auditable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString(callSuper = true)
@Entity
@Table(name = "event_action_execution")
public class EventActionExecution extends Auditable {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id")
    private String id;

    @NotEmpty(message = "eventActionId can not be empty or null")
    @Column(name = "event_action_id", updatable = false, nullable = false)
    private String eventActionId;

    @NotEmpty(message = "executionId can not be empty or null")
    @Column(name = "execution_id", updatable = false, nullable = false)
    private String executionId;

    @NotEmpty(message = "tenantId can not be empty or null")
    @Column(name = "tenant_id", updatable = false, nullable = false)
    private String tenantId;

    @NotEmpty(message = "status can not be empty or null")
    @Column(name = "status")
    private String status;

}
