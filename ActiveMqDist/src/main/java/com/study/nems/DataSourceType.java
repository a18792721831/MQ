package com.study.nems;

/**
 * @author jiayq
 */

public enum DataSourceType {

    /**
     * 含有用户表的数据库
     */
    DATA_SOURCE_SUB("dataSource1"),

    /**
     * 含有积分表的数据库
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
