package com.study.domain;

import com.study.nems.State;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * @author jiayq
 */
@Component(value = "integral")
@Scope("prototype")
public class Integral implements Serializable {

    private static final long serialVersionUID = -5399381421515509493L;
    /**
     * id
     */
    private Long id;

    /**
     * subscriberId
     */
    private Long subscriberId;

    /**
     * amount
     */
    private Double amount;

    /**
     * state
     */
    private State state;

    @Override
    public String toString() {
        return "Integral\t[id=" + this.id + ",subscriberId=" + this.subscriberId
                + ",amount" + this.amount + ",state=" + this.state == null ? "" : this.state.name() + "]";
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getSubscriberId() {
        return subscriberId;
    }

    public void setSubscriberId(Long subscriberId) {
        this.subscriberId = subscriberId;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }
}
