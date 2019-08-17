package com.study.domain;

import com.study.nems.EventType;
import com.study.nems.ProcessType;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author jiayq
 */
@Component(value = "event")
@Scope("prototype")
public class Event implements Serializable {

    private static final long serialVersionUID = 5617812697884788192L;
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
     * subscriberId
     * (实现用户多个操作全部删除等场景，避免 1->2->1 => 1->1->2的情况)
     */
    private Long subscriberId;

    /**
     * createdt
     */
    private Date createdt;

    /**
     * updatedt
     */
    private Date updatedt;

    /**
     * 通过事件传输的信息
     * (主要用来积分传输给用户时，定义的用户信息)
     */
    private String content;

    @Override
    public String toString() {
        try {
            return "Event\t[id=" + this.id + ",eventType=" + (this.eventType == null ? "" : new String(this.eventType.getName().getBytes("GBK"),"UTF-8")) +
                    ",processType=" + (this.processType == null ? "" : new String(this.processType.getName().getBytes("UTF-8"),"UTF-8")) +
                    ",subscriberId=" + (this.subscriberId == null ? "" : this.subscriberId) +
                    ",createdt=" + (this.createdt == null ? "" : new SimpleDateFormat("yyyy-mm-dd HH:mm:ss").format(this.createdt)) +
                    ",updatedt=" + (this.updatedt == null ? "" : new SimpleDateFormat("yyyy-mm-dd HH:mm:ss").format(this.updatedt)) +
                    ",content=" + this.content + "]";
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return super.toString();
    }

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

    public Date getCreatedt() {
        return createdt;
    }

    public void setCreatedt(Date createdt) {
        this.createdt = createdt;
    }

    public Date getUpdatedt() {
        return updatedt;
    }

    public void setUpdatedt(Date updatedt) {
        this.updatedt = updatedt;
    }

    public Long getSubscriberId() {
        return subscriberId;
    }

    public void setSubscriberId(Long subscriberId) {
        this.subscriberId = subscriberId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
