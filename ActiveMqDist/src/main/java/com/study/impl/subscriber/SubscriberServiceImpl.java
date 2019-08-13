package com.study.impl.subscriber;

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
import java.util.logging.Logger;

/**
 * @author jiayq
 */
@Service("subscriberService")
public class SubscriberServiceImpl implements SubscriberService{

    private static Logger logger = Logger.getLogger(SubscriberServiceImpl.class.getName());

    @Autowired
    private SubscriberDao subscriberDao;

    @Autowired
    private EventDao eventDao;

    @Transactional(rollbackFor = MyBusinessException.class)
    @Override
    public void addSubscriber(Subscriber subscriber) {
        Event event = new Event();
        event.setCreatedt(new Date());
        event.setContent(subscriber.getName() + ":" +subscriber.getCode() + ":" + subscriber.getPassword());
        event.setEventType(EventType.REG_SUB);
        event.setProcessType(ProcessType.NEW);
        eventDao.addEvent(event);
        subscriberDao.addSubscriber(subscriber);
    }

    @Override
    public void modifySubscriber(Subscriber subscriber) {
        subscriberDao.modifySubscriber(subscriber);
    }

    @Override
    public void deleteSubscriber(Subscriber subscriber) {
    }

    @Override
    public Subscriber querySubscriberById(Long subsriberId) {
        return null;
    }

    @Override
    public List<Subscriber> querySubscriberByCondition(SubscriberCondition condition) {
        return null;
    }
}
