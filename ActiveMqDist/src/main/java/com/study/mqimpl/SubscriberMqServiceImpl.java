package com.study.mqimpl;

import com.alibaba.fastjson.JSON;
import com.study.domain.Event;
import com.study.mqservice.SubscriberMqService;
import com.study.nems.ProcessType;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.jms.Destination;
import java.util.function.Consumer;

/**
 * @author jiayq
 */
@Component("subscriberMqService")
public class SubscriberMqServiceImpl implements SubscriberMqService {

    @Resource(name = "jmsSubTemplate")
    private JmsTemplate jmsTemplate;

    @Override
    public void publishSubsciberEvent(Destination topic, Event event, Consumer<Event> action) {
        jmsTemplate.send(topic,
                session -> session.createTextMessage(JSON.toJSONString(event)));
        event.setProcessType(ProcessType.PUBLISH);
        action.accept(event);
    }
}
