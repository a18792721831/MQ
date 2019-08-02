package com.study.produce;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * @author jiayq
 */
public class Produce {

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
            System.out.println("connect");
            //启动连接
            connection.start();
            System.out.println("start");
            //创建会话
            Session session = connection.createSession(true, Session.AUTO_ACKNOWLEDGE);
            System.out.println("session");
            //创建队列，需要制定队列名称，消息生产者和消费者将根据它来发送、接收对应的消息
            Queue testQueue = session.createQueue("activemq-queue-test");
            System.out.println("queue");
            //消息生产者
            MessageProducer producer = session.createProducer(testQueue);
            System.out.println("produce");
            //创建一个消息对象
            TextMessage message = session.createTextMessage("测试点对点的消息");
            System.out.println("message");
            //发送一条消息
            producer.send(message);
            System.out.println("send");
            //提交事务
            session.commit();
            System.out.println("commit");
            //关闭资源
            session.close();
            connection.close();
            System.out.println("close");
        } catch (JMSException e){
            e.printStackTrace();
        }
    }

}
