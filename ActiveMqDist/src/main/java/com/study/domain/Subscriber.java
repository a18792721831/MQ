package com.study.domain;

import com.study.nems.State;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * @author jiayq
 */
@Component(value = "subscriber")
@Scope("prototype")
public class Subscriber implements Serializable {

    private static final long serialVersionUID = -9215519607931081134L;

    /**
     * id
     */
    private Long id;

    /**
     * code
     */
    private String code;

    /**
     * name
     */
    private String name;

    /**
     * password
     */
    private String password;

    /**
     * state
     */
    private State state;

    @Override
    public String toString() {
        return "Subscriber\t[id=" + this.id + ",code=" + this.code +
                ",name=" + this.name + ",password=" + this.password +
                ",state=" + (this.state == null ? "" : this.state.name()) + "]";
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
