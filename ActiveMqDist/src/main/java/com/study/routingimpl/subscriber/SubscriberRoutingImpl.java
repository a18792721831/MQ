package com.study.routingimpl.subscriber;

import com.study.condition.EventCondition;
import com.study.condition.SubscriberCondition;
import com.study.domain.Event;
import com.study.domain.Subscriber;
import com.study.routing.SubscriberRouting;
import com.study.service.SubscriberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 10008377
 */
@Service("subscriberRouting")
public class SubscriberRoutingImpl implements SubscriberRouting {

    @Autowired
    private SubscriberService subscriberService;

    @Override
    public void addSubscriber(Subscriber subscriber) {
        subscriberService.addSubscriber(subscriber);
    }

    @Override
    public void modifySubscriber(Subscriber subscriber) {
        subscriberService.modifySubscriber(subscriber);
    }

    @Override
    public void deleteSubscriber(Subscriber subscriber) {
        subscriberService.deleteSubscriber(subscriber);
    }

    @Override
    public Subscriber querySubscriberById(Long subsriberId) {
        return subscriberService.querySubscriberById(subsriberId);
    }

    @Override
    public List<Subscriber> querySubscriberByCondition(SubscriberCondition condition) {
        return subscriberService.querySubscriberByCondition(condition);
    }

    @Override
    public List<Event> getNewEvent() {
        return subscriberService.getNewEvent();
    }

    @Override
    public void modifyEvent(Event event) {
        subscriberService.modifyEvent(event);
    }

    @Override
    public void addEvent(Event event) {
        subscriberService.addEvent(event);
    }

    @Override
    public List<Event> queryEventByEventCondition(EventCondition eventCondition) {
        return subscriberService.queryEventByEventCondition(eventCondition);
    }
}
