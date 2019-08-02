package com.study.nems;

/**
 * @author jiayq
 */

public enum ProcessType {

    /**
     * 新建
     */
    NEW("新建",0L),

    /**
     * 已发布
     */
    PUBLISH("已发布",1L),

    /**
     * 已完成
     */
    FINISH("已完成",2L);

    private String name;

    private Long value;

    private ProcessType(String name, Long value) {
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
