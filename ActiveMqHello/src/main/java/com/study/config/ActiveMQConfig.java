package com.study.config;

import org.apache.activemq.ActiveMQConnection;

/**
 * @author jiayq
 */

public enum ActiveMQConfig {
    /**
     * username
     */
    USERNAME(ActiveMQConnection.DEFAULT_USER),
    /**
     * password
     */
    PASSWORD(ActiveMQConnection.DEFAULT_PASSWORD),
    /**
     * url
     */
    URL(ActiveMQConnection.DEFAULT_BROKER_URL);

    /**
     * value
     */
    private String value;

    private ActiveMQConfig(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
