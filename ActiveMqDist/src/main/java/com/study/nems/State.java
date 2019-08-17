package com.study.nems;

/**
 * @author jiayq
 */

public enum State {


    /**
     * 有效
     */
    VALID("有效", 0L),

    /**
     * 无效
     */
    INVALID("无效", 1L);

    /**
     * name
     */
    private String name;

    /**
     * value
     */
    private Long value;

    private State(String name, Long value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getValue() {
        return value;
    }

    public void setValue(Long value) {
        this.value = value;
    }
}
