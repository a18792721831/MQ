package com.study.broker;

import java.util.concurrent.ArrayBlockingQueue;

/**
 * 消息处理中心：消息的接收、存储、转发等
 * @author jiayq
 */
public class Broker {

    private final static int MAX_SIZE = 3;

    private static ArrayBlockingQueue<String> messageQueue = new ArrayBlockingQueue<>(MAX_SIZE);

    public static void produce(String msg) {
        if (messageQueue.offer(msg)) {
            System.out.printf("成功投递消息%s，当前暂存的消息数量是:%d\n", msg, messageQueue.size());
        } else {
            System.out.printf("消息处理中心暂存达到最大值，无法暂存%s消息\n", msg);
        }
        System.out.println("-----------------------------------");
    }

    public static String consume(){
        String msg = messageQueue.poll();
        if (msg != null) {
            System.out.printf("已经消费消息%s，暂存的消息数量是%d\n", msg, messageQueue.size());
        } else {
            System.out.println("没有暂存的消息了");
        }
        System.out.println("---------------------------------------");
        return msg;
    }

}
