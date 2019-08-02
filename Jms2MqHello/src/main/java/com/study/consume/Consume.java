package com.study.consume;

import org.apache.activemq.artemis.jms.client.ActiveMQJMSConnectionFactory;

import javax.jms.JMSContext;
import javax.jms.JMSException;
import javax.jms.TextMessage;

/**
 * @author jiayq
 */
public class Consume {

    public static void main(String[] args){
        //创建factory
        ActiveMQJMSConnectionFactory activeMQJMSConnectionFactory = new ActiveMQJMSConnectionFactory();
        try (
                JMSContext jmsContext = activeMQJMSConnectionFactory.createContext()
        ) {
            jmsContext.createConsumer(jmsContext.createQueue("jms")).setMessageListener(message -> {
                try{
                    TextMessage textMessage = (TextMessage)message;
                    System.out.println(textMessage.getText());
                } catch (JMSException e){
                    e.printStackTrace();
                }
            });
            Thread.sleep(10 * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
