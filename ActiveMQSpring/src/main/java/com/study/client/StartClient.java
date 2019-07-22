package com.study.client;

import com.study.product.ProduceService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Random;

/**
 * @author jiayq
 */
public class StartClient {
    public static void main(String[] args) throws InterruptedException {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("beans.xml");
        ProduceService produceService = applicationContext.getBean(ProduceService.class);
        while (true) {
            produceService.sendMessage("queueTestMessage:\t" + new Random().nextDouble());
            produceService.sendTopicMessage("topicTestMessage:\t" + new Random().nextDouble());
            Thread.sleep(3 * 1000);
        }

    }
}
