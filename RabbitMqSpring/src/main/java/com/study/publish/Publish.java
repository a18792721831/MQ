package com.study.publish;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author jiayq
 */
public class Publish {

    public static void main(String[] args) {
        try (
                AbstractApplicationContext abstractApplicationContext =
                        new ClassPathXmlApplicationContext("beans.xml")) {
            RabbitTemplate rabbitTemplate = abstractApplicationContext.getBean(RabbitTemplate.class);
            rabbitTemplate.convertAndSend("hello spring");
        }
    }

}
