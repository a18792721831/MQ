package com.study.product;

import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.jms.Destination;
import java.util.Random;

/**
 * @author jiayq
 */
@Service("produceService")
public class ProduceService {

    @Resource(name = "jmsTemplate")
    private JmsTemplate jmsTemplate;

    @Resource(name = "testQueue")
    private Destination testQueue;

    @Resource(name = "testTopic")
    private Destination testTopic;

    public void sendMessage(String messageContent) {
        jmsTemplate.send(testQueue, session -> session.createTextMessage(new Random().nextDouble() + "queue produce"));
    }

    public void sendTopicMessage(String messageContent) {
        jmsTemplate.send(testTopic, session -> session.createTextMessage(new Random().nextDouble() + "topic produce"));
    }

}
