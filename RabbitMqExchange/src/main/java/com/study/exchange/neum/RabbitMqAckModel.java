package com.study.exchange.neum;

/**
 * @author jiayq
 */

public enum RabbitMqAckModel {

    /**
     * 普通确认模式
     */
    COMMON(0,"普通确认模式"),


    /**
     * 批量确认模式
     */
    BATCHACK(1,"批量确认模式"),

    /**
     * 异步确认模式
     */
    ASYNACK(2,"异步确认模式");

    /**
     * id
     */
    private int id;

    /**
     * name
     */
    private String name;

    private RabbitMqAckModel(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
