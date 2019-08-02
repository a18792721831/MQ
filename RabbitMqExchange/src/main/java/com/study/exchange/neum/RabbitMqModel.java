package com.study.exchange.neum;

/**
 * @author jiayq
 */

public enum RabbitMqModel {

    /**
     * 默认模式
     */
    NONE(0,"默认模式"),

    /**
     * 事务模式
     */
    TX(1,"事务模式"),

    /**
     * 消息确认模式
     */
    ACK(2,"确认模式");

    /**
     * id
     */
    private int id;

    /**
     * name
     */
    private String name;

    private RabbitMqModel(int id, String name){
        this.id = id;
        this.name = name;
    }

    /**
     * @return
     */
    public int getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }
}
