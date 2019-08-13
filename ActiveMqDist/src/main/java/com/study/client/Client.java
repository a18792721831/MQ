package com.study.client;

import com.study.domain.Subscriber;
import com.study.service.SubscriberService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author jiayq
 */
public class Client {

    public static void main(String[] args) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring/*.xml");
        SubscriberService subscriberService = applicationContext.getBean("subscriberService", SubscriberService.class);
        Subscriber subscriber = applicationContext.getBean("subscriber", Subscriber.class);
        subscriberService.addSubscriber(subscriber);
    }

}