package com.study.consume;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

/**
 * @author jiayq
 */
public class Topic1Listener implements MessageListener {
    @Override
    public void onMessage(Message message) {
        if (message instanceof TextMessage) {
            TextMessage textMessage = (TextMessage) message;
            try {
                String messageText = textMessage.getText();
                System.out.println("message:\t" + messageText);
                System.out.println("topic listener1");
            } catch (JMSException e) {
                e.printStackTrace();
            }
        } else {
            throw new IllegalArgumentException("Only Text Message");
        }
    }
}
