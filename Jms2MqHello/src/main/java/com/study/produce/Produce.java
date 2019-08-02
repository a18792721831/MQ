package com.study.produce;

import org.apache.activemq.artemis.jms.client.ActiveMQJMSConnectionFactory;

import javax.jms.JMSContext;

/**
 * @author jiayq
 */
public class Produce {

    public static void main(String[] args){
        ActiveMQJMSConnectionFactory factory = new ActiveMQJMSConnectionFactory();
        try (
                JMSContext jmsContext = factory.createContext()
                ) {
            jmsContext.createProducer().send(jmsContext.createQueue("jms") ,"jms2.0:produce");
        }


    }

}
