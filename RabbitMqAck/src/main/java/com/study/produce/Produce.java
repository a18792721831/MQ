package com.study.produce;

import com.rabbitmq.client.*;
import com.study.config.RabbitMqConfig;
import com.study.neum.RabbitMqAckModel;
import com.study.neum.RabbitMqModel;

import java.util.Scanner;

/**
 * @author jiayq
 */
public class Produce {

    private static RabbitMqModel rabbitMqModel = RabbitMqModel.ACK;

    private static RabbitMqAckModel rabbitMqAckModel = RabbitMqAckModel.ASYNACK;

    public static void main(String[] args) {
        System.out.println("produce model is " + rabbitMqModel.getName());
        if (RabbitMqModel.ACK.equals(rabbitMqModel)) {
            System.out.println("produce ack model is " + rabbitMqAckModel.getName());
        }
        try (Scanner scanner = new Scanner(System.in)) {
            while (true) {
                System.out.println("Please input message(one row one message,quit to exits):");
                String message = scanner.nextLine();
                if ("quit".equals(message)) {
                    System.out.println("Will be quit!!!");
                    break;
                }
                sendMessage(message);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void sendMessage(String message) throws Exception{
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost(RabbitMqConfig.HOST_NAME);
        Connection connection = connectionFactory.newConnection();
        Channel channel = connection.createChannel();
        channel.queueDeclare(RabbitMqConfig.QUEUE_NAME, true, false, false, null);
        channel.exchangeDeclare(RabbitMqConfig.EXCHANGE_NAME, "direct", true, false, null);
        channel.queueBind(RabbitMqConfig.QUEUE_NAME, RabbitMqConfig.EXCHANGE_NAME, RabbitMqConfig.ROUNT_KEY);
        switch (rabbitMqModel) {
            case NONE:
                break;
            case TX:
                channel.txSelect();
                break;
            case ACK:
                channel.confirmSelect();
                break;
                default:
                    break;
        }
        channel.basicPublish(RabbitMqConfig.EXCHANGE_NAME, RabbitMqConfig.ROUNT_KEY, MessageProperties.PERSISTENT_TEXT_PLAIN, message.getBytes());
        switch (rabbitMqModel) {
            case NONE:
                break;
            case TX:
                channel.txCommit();
                break;
            case ACK:
                switch (rabbitMqAckModel) {
                    case COMMON:
                        channel.waitForConfirms();
                        break;
                    case BATCHACK:
                        channel.waitForConfirms();
                        break;
                    case ASYNACK:
                        ConfirmCallback confirmCallback = (deliverTag, multiple) ->{
                            System.out.println("deliverTag:\t" + deliverTag);
                            System.out.println("multiple:\t" + multiple);
                        };
                        channel.addConfirmListener(confirmCallback, confirmCallback);
                        break;
                        default:
                            break;
                }
                break;
                default:
                    break;
        }
    }

}
