package com.study.consume;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * @author jiayq
 */
public class Consume {

    /**
     * 默认用户名
     */
    public static final String USERNAME = ActiveMQConnection.DEFAULT_USER;

    /**
     * 默认密码
     */
    public static final String PASSWORD = ActiveMQConnection.DEFAULT_PASSWORD;

    /**
     * 默认连接地址
     */
    public static final String BROKER_URL = ActiveMQConnection.DEFAULT_BROKER_URL;

    public static void main(String[] args){
        //创建连接工厂
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(USERNAME, PASSWORD, BROKER_URL);
        System.out.println("factory");
        try{
            //创建连接
            Connection connection = connectionFactory.createConnection();
            System.out.println("connection");
            //开启连接
            connection.start();
            System.out.println("start");
            //创建会话
            final Session session = connection.createSession(true, Session.AUTO_ACKNOWLEDGE);
            System.out.println("session");
            //创建队列
            Queue testQueue = session.createQueue("activemq-queue-test");
            System.out.println("queue");
            //创建消费者
            MessageConsumer consumer = session.createConsumer(testQueue);
            System.out.println("consumer");
            //消费者监听接口消费消息
            consumer.setMessageListener(message -> {
                try {
                    TextMessage textMessage = (TextMessage) message;
                    System.out.println(textMessage.getText());
                    session.commit();
                    System.out.println("commit");
                } catch (JMSException e) {
                    e.printStackTrace();
                }
            });
            System.out.println("send");
            //让主线程休眠100秒。使消息消费者对象能继续存活一段时间，从而能监听到消息
            Thread.sleep(100 * 1000);
            System.out.println("sleep");
            //关闭资源
            session.close();
            connection.close();
            System.out.println("close");
        } catch (JMSException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
