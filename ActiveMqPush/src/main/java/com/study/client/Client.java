package com.study.client;

import com.study.produce.Produce;
import org.apache.xbean.spring.context.ClassPathXmlApplicationContext;
import org.springframework.context.ApplicationContext;

import java.util.Random;

/**
 * @author jiayq
 */
public class Client {

    public static void main(String[] args) throws InterruptedException {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("beans.xml");
        Produce produce = applicationContext.getBean("produce",Produce.class);
        while (true) {
            produce.sendMessage(new Random().nextDouble() + "");
            Thread.sleep(3 * 1000);
        }
    }

}
