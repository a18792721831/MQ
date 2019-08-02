package com.study.nems;

/**
 * @author jiayq
 */

public enum DataSourceType {

    /**
     * �����û�������ݿ�
     */
    DATA_SOURCE_SUB("dataSource1"),

    /**
     * ���л��ֱ�����ݿ�
     */
    DATA_SOURCE_INTE("dataSource2");

    private String name;

    private DataSourceType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
