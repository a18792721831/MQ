package com.study.exchange.neum;

/**
 * @author jiayq
 */

public enum RabbitMqExchangeModel {

    /**
     * 完全匹配
     */
    DIRECT("direct", "完全匹配"),

    /**
     * 发布订阅
     */
    FANOUT("fanout", "发布订阅"),

    /**
     * 定制化
     */
    TOCPIC("topic", "定制化");

    /**
     * name
     */
    private String name;

    /**
     * code
     */
    private String code;

    RabbitMqExchangeModel(String code, String name) {
        this.name = name;
        this.code = code;
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

    /**
     * @return
     */
    public String getCode() {
        return code;
    }

    /**
     * @param code
     */
    public void setCode(String code) {
        this.code = code;
    }
}
