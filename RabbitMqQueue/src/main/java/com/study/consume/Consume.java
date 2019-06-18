package com.study.consume;

import com.rabbitmq.client.*;

/**
 * @author jiayq
 */
public class Consume {

    /**
     * 队列名字
     */
    private static final String QUEUE_NAME = "HelloQueue";


    public static void main(String[] args) throws Exception {
        //创建连接工厂
        ConnectionFactory connectionFactory = new ConnectionFactory();
        //设置目标主机
        connectionFactory.setHost("localhost");
        //创建连接：这里不能使用try,因为try-catch-resource会自动调用closeable进行关闭资源
        Connection connection = connectionFactory.newConnection();
        //创建信道
        Channel channel = connection.createChannel();
        //声明队列
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
        //设置信道最大传输信息数
        channel.basicQos(1);
        //创建收到消息的回调
        DeliverCallback deliverCallback = (tag, body) -> {
            //消息字节流转字符串
            String message = new String(body.getBody(), "utf-8");
            System.out.println("Get the messgae:\t" + message);
            int time = 10;
            while (time > 0) {
                System.out.print("...\t");
                try {
                    //延时模拟消费比生产耗时
                    Thread.sleep(1 * 1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                time--;
            }
            //设置消费返回
            channel.basicAck(body.getEnvelope().getDeliveryTag(),false);
        };
        //创建消费失败回调
        CancelCallback cancelCallback = (errorMessage) -> {
            System.out.println("\nGet message error,the error message is:\t" + errorMessage);
            try {
                Thread.sleep(1 * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };
        //消费消息队列：第二个参数表示是不等待返回。
        //消费时，如果指定第二个参数为true,那么只有一个信道返回了ack，此时信道释放队列的占用
        //否则信道会独占队列
        //在独占队列的情况下，可以不返回ack，那么只有当消费者进程终止才会释放队列的独占
        //如果第二个参数为false，表示不独占，但是单信道阻塞：
        //第二个参数为fasle,那么此时多个信道可以并发访问队列。
        //但是对于单个信道而言必须进行返回才能继续消费
        channel.basicConsume(QUEUE_NAME, false, deliverCallback, cancelCallback);

    }

}
