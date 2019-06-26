package com.study.consume;

import org.springframework.amqp.core.AcknowledgeMode;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;

import java.io.UnsupportedEncodingException;

/**
 * @author jiayq
 */
public class Consume implements MessageListener {
    @Override
    public void onMessage(Message message) {
        try {
            System.out.println("message:\t" + new String(message.getBody(),"utf-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void containerAckMode(AcknowledgeMode mode) {
        System.out.println("ackmode:\t" + mode.name());
    }
}
