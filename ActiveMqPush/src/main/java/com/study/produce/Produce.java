package com.study.produce;

import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.jms.Destination;
import java.util.Random;

/**
 * @author jiayq
 */
@Service("produce")
public class Produce {
    @Resource(name = "jmsTemplate")
    private JmsTemplate jmsTemplate;

    @Resource(name = "testQueue")
    private Destination testQueue;

    public void sendMessage(String messageContent) {
        jmsTemplate.send(testQueue, session -> session.createTextMessage(new Random().nextDouble() + "queue produce"));
    }
}
