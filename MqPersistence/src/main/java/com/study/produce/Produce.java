package com.study.produce;

import com.rabbitmq.client.*;
import com.study.config.RabbitMqConfig;

import java.io.IOException;
import java.util.Scanner;
import java.util.concurrent.TimeoutException;

/**
 * @author jiayq
 */
public class Produce {

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            while (true){
                System.out.println("Please input message(one row one message,quit to exits):");
                String message = scanner.nextLine();
                if ("quit".equals(message)) {
                    System.out.println("Will be quit!!!");
                    break;
                }
                sendMessage(message);
            }
        }
    }

    private static void sendMessage(String message) {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost(RabbitMqConfig.HOST_NAME);
        try (
                Connection connection = connectionFactory.newConnection();
                Channel channel = connection.createChannel()
                ) {
            channel.queueDeclare(RabbitMqConfig.QUEUE_NAME, true, false, false, null);
            channel.exchangeDeclare(RabbitMqConfig.EXCHANGE_NAME, "direct", true, false,null);
            channel.queueBind(RabbitMqConfig.QUEUE_NAME, RabbitMqConfig.EXCHANGE_NAME, RabbitMqConfig.ROUNT_KEY);
            channel.basicPublish(RabbitMqConfig.EXCHANGE_NAME, RabbitMqConfig.ROUNT_KEY, MessageProperties.PERSISTENT_TEXT_PLAIN, message.getBytes());
            System.out.println("The message send over:\t" + message);
        } catch (TimeoutException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
