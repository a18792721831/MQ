package com.study.publish;


import com.study.config.ActiveMQConfig;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;
import java.util.Random;

/**
 * @author jiayq
 */
public class Publish {

    public static void main(String[] args) {
        //创建连接工厂
        ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory(ActiveMQConfig.USERNAME.getValue(),
                ActiveMQConfig.PASSWORD.getValue(),ActiveMQConfig.URL.getValue());
        try{
            //创建连接
            Connection connection = activeMQConnectionFactory.createConnection();
            //开启连接
            connection.start();
            //创建会话
            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            //创建主题，用于订阅消息
            Topic topic = session.createTopic("active-test");
            //消息生产者
            MessageProducer producer = session.createProducer(topic);
            while (true) {
                //创建消息
                TextMessage message = session.createTextMessage(new String(new Random().nextDouble() + ""));
                System.out.println(message.getText());
                //发送
                producer.send(message);
                //模拟生产消息的耗时
                Thread.sleep(3 * 1000);
            }
        } catch (JMSException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


}
