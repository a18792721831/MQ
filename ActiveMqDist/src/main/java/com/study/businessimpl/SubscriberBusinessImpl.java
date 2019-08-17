package com.study.businessimpl;

import com.alibaba.fastjson.JSON;
import com.study.business.SubscriberBusiness;
import com.study.condition.EventCondition;
import com.study.domain.Event;
import com.study.domain.Integral;
import com.study.domain.Subscriber;
import com.study.mqservice.SubscriberMqService;
import com.study.nems.EventType;
import com.study.nems.ProcessType;
import com.study.nems.State;
import com.study.service.SubscriberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.jms.Destination;
import java.util.List;
import java.util.Random;

/**
 * @author jiayq
 */
@Service("subscriberBusiness")
public class SubscriberBusinessImpl implements SubscriberBusiness {

    @Autowired
    private SubscriberService subscriberService;

    @Autowired
    private SubscriberMqService subscriberMqService;


    @Resource(name = "subscriberTopic")
    private Destination subscriberTopic;

    @Override
    public void addSubsciber(Subscriber subscriber) {
        subscriberService.addSubscriber(subscriber);
        EventCondition eventCondition = new EventCondition();
        eventCondition.setSubscriberId(subscriber.getId());
        eventCondition.setEventType(EventType.REG_SUB);
        eventCondition.setProcessType(ProcessType.NEW);
        List<Event> eventList = subscriberService.queryEventByEventCondition(eventCondition);
        if (1 == eventList.size()) {
            Event event = eventList.get(0);
            Integral integral = new Integral();
            integral.setSubscriberId(event.getSubscriberId());
            integral.setAmount(new Random().nextDouble());
            integral.setState(State.VALID);
            event.setContent(JSON.toJSONString(integral));
            subscriberMqService.publishSubsciberEvent(subscriberTopic, event,
                    event1 -> subscriberService.modifyEvent(event1));
        }
    }

    @Override
    public void deleteSubscriber(Subscriber subscriber) {
        //TODO
    }
}
