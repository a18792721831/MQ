package com.study.consume;

import com.study.config.ActiveMQConfig;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * @author jiayq
 */
public class Consume {

    public static void main(String[] args) throws JMSException {
        //创建连接工厂
        ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory(ActiveMQConfig.USERNAME.getValue(),
                ActiveMQConfig.PASSWORD.getValue(), ActiveMQConfig.URL.getValue());
        //创建连接
        Connection connection = activeMQConnectionFactory.createConnection();
        //开启连接
        connection.start();
        //创建会话，不需要事务
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        //创建主题
        Topic topic = session.createTopic("active-test");
        //创建消息消费者
        MessageConsumer consumer = session.createConsumer(topic);
        //注册监听
        consumer.setMessageListener(message -> {
            try {
                System.out.println(((TextMessage)message).getText());
                Thread.sleep(4 * 1000);
            } catch (JMSException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
    }

}
