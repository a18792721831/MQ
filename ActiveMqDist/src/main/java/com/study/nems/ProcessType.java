package com.study.nems;

/**
 * @author jiayq
 */

public enum ProcessType {

    /**
     * �½�
     */
    NEW("�½�",0L),

    /**
     * �ѷ���
     */
    PUBLISH("�ѷ���",1L),

    /**
     * �����
     */
    FINISH("�����",2L);

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
