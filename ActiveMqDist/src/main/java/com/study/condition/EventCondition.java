package com.study.condition;

import com.study.nems.EventType;
import com.study.nems.ProcessType;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Date;

/**
 * @author jiayq
 */
@Component(value = "eventCondition")
@Scope("prototype")
public class EventCondition implements Serializable {

    private static final long serialVersionUID = -226306267564140362L;
    /**
     * id
     */
    private Long id;

    /**
     * eventType
     */
    private EventType eventType;

    /**
     * processType
     */
    private ProcessType processType;

    /**
     * createDt
     */
    private Date createDt;

    /**
     * updateDt
     */
    private Date updateDt;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public EventType getEventType() {
        return eventType;
    }

    public void setEventType(EventType eventType) {
        this.eventType = eventType;
    }

    public ProcessType getProcessType() {
        return processType;
    }

    public void setProcessType(ProcessType processType) {
        this.processType = processType;
    }

    public Date getCreateDt() {
        return createDt;
    }

    public void setCreateDt(Date createDt) {
        this.createDt = createDt;
    }

    public Date getUpdateDt() {
        return updateDt;
    }

    public void setUpdateDt(Date updateDt) {
        this.updateDt = updateDt;
    }
}
