package com.study.serviceimpl.subscriber;

import com.study.condition.EventCondition;
import com.study.condition.SubscriberCondition;
import com.study.dao.EventDao;
import com.study.dao.sub.SubscriberDao;
import com.study.domain.Event;
import com.study.domain.Subscriber;
import com.study.exception.MyBusinessException;
import com.study.nems.EventType;
import com.study.nems.ProcessType;
import com.study.service.SubscriberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * @author jiayq
 */
@Service("subscriberService")
public class SubscriberServiceImpl implements SubscriberService{

    @Autowired
    private SubscriberDao subscriberDao;

    @Autowired
    private EventDao eventDao;

    @Override
    @Transactional(rollbackFor = MyBusinessException.class)
    public void addSubscriber(Subscriber subscriber) {
        subscriber.setId(null);
        subscriberDao.addSubscriber(subscriber);
        EventCondition eventCondition = new EventCondition();
        eventCondition.setSubscriberId(subscriber.getId());
        eventCondition.setEventType(EventType.REG_SUB);
        eventCondition.setProcessType(ProcessType.NEW);
        if (0 == eventDao.queryEventByCondition(eventCondition).size()){
            Event event = new Event();
            event.setCreatedt(new Date());
            event.setSubscriberId(subscriber.getId());
            event.setEventType(EventType.REG_SUB);
            event.setProcessType(ProcessType.NEW);
            event.setId(null);
            eventDao.addEvent(event);
        }
    }

    @Override
    @Transactional(rollbackFor = MyBusinessException.class)
    public void modifySubscriber(Subscriber subscriber) {
        subscriberDao.modifySubscriber(subscriber);
    }

    @Override
    @Transactional(rollbackFor = MyBusinessException.class)
    public void deleteSubscriber(Subscriber subscriber) {
        EventCondition eventCondition = new EventCondition();
        eventDao.queryEventByCondition(eventCondition).forEach(event -> eventDao.delteEvent(event));
        Event event = new Event();
        event.setCreatedt(new Date());
        event.setSubscriberId(subscriber.getId());
        event.setEventType(EventType.DEL_SUB);
        event.setProcessType(ProcessType.NEW);
        eventDao.addEvent(event);
        subscriberDao.deleteSubscriber(subscriber);
    }

    @Override
    public Subscriber querySubscriberById(Long subsriberId) {
        return subscriberDao.querySubscriberById(subsriberId);
    }

    @Override
    public List<Subscriber> querySubscriberByCondition(SubscriberCondition condition) {
        return subscriberDao.querySubscriberByCondition(condition);
    }

    @Override
    public List<Event> getNewEvent() {
        EventCondition eventCondition = new EventCondition();
        eventCondition.setProcessType(ProcessType.NEW);
        eventCondition.setEventType(EventType.REG_SUB);
        return eventDao.queryEventByCondition(eventCondition);
    }

    @Override
    @Transactional(rollbackFor = MyBusinessException.class)
    public void modifyEvent(Event event) {
        eventDao.modifyEvent(event);
    }

    @Override
    @Transactional(rollbackFor = MyBusinessException.class)
    public void addEvent(Event event) {
        event.setId(null);
        eventDao.addEvent(event);
    }

    @Override
    public List<Event> queryEventByEventCondition(EventCondition eventCondition) {
        return eventDao.queryEventByCondition(eventCondition);
    }

}
