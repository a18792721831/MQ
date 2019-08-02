package com.study.nems;

/**
 * @author jiayq
 */

public enum EventType {

    /**
     * 创建用户
     */
    REG_SUB("创建用户",0L),

    /**
     * 创建积分
     */
    REG_INTEGRAL("创建积分",1L);

    private String name;

    private Long value;

    private EventType(String name, Long value) {
        this.name = name;
        this.value = value;
    }

    public Long getValue() {
        return value;
    }

    public void setValue(Long value) {
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
