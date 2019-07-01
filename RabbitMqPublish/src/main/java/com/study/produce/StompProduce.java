package com.study.produce;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Random;

/**
 * @author jiayq
 */
public class StompProduce {

    public static void main(String[] args) throws Exception {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("beans.xml");
        ConnectionFactory factory = (ConnectionFactory) applicationContext.getBean("factory");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();
        channel.exchangeDeclare("exchange_stomp", "topic");
        channel.queueDeclare("queue_stomp", false, false, false, null);
        channel.queueBind("queue_stomp", "exchange_stomp", "*.test");
        while (true) {
            String message = new String(new Random().nextDouble()+"");
            channel.basicPublish("exchange_stomp", "test.test",
                    null, message.getBytes());
            System.out.println("message:\t" + message);
            Thread.sleep(3 * 1000);
        }
    }

}
