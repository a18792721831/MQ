package com.study.serviceimpl.integral;

import com.study.condition.EventCondition;
import com.study.dao.EventDao;
import com.study.dao.inte.IntegralDao;
import com.study.domain.Event;
import com.study.domain.Integral;
import com.study.exception.MyBusinessException;
import com.study.nems.EventType;
import com.study.nems.ProcessType;
import com.study.service.IntegralService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * @author jiayq
 */
@Service("integralService")
public class IntegralServiceImpl implements IntegralService {

    @Autowired
    private IntegralDao integralDao;

    @Autowired
    private EventDao eventDao;

    @Override
    @Transactional(rollbackFor = MyBusinessException.class)//遇到自定义异常就回滚
    public void addIntegral(Integral integral) {
        integral.setId(null);
        integralDao.addIntegral(integral);
        EventCondition eventCondition = new EventCondition();
        eventCondition.setSubscriberId(integral.getSubscriberId());
        eventCondition.setEventType(EventType.REG_INTEGRAL);
        eventCondition.setProcessType(ProcessType.NEW);
        if (0 == eventDao.queryEventByCondition(eventCondition).size()){
            Event event = new Event();
            event.setCreatedt(new Date());
            event.setSubscriberId(integral.getSubscriberId());
            event.setEventType(EventType.REG_INTEGRAL);
            event.setProcessType(ProcessType.NEW);
            event.setId(null);
            eventDao.addEvent(event);
        }
    }

    @Override
    @Transactional(rollbackFor = MyBusinessException.class)//遇到自定义异常就回滚
    public void modifyIntegral(Integral integral) {
        integralDao.modifyIntegral(integral);
    }

    @Override
    @Transactional(rollbackFor = MyBusinessException.class)//遇到自定义异常就回滚
    public void deleteIntegral(Integral integral) {
        EventCondition eventCondition = new EventCondition();
        eventCondition.setSubscriberId(integral.getSubscriberId());
        eventDao.queryEventByCondition(eventCondition).stream().forEach(event -> eventDao.delteEvent(event));
        Event event = new Event();
        event.setCreatedt(new Date());
        event.setSubscriberId(integral.getSubscriberId());
        event.setProcessType(ProcessType.NEW);
        event.setEventType(EventType.DEL_INTEGRAL);
        eventDao.addEvent(event);
    }

    @Override
    public Integral queryIntegralById(Long id) {
        return integralDao.queryIntegralById(id);
    }

    @Override
    public Integral queryIntegralBySubscriberId(Long subscriberId) {
        return integralDao.queryIntegralBySubscriberId(subscriberId);
    }

    @Override
    public List<Integral> queryIntegralByAmount(Double amount) {
        return integralDao.queryIntegralByAmount(amount);
    }

    @Override
    public List<Event> getNewIntegralEvent() {
        EventCondition eventCondition = new EventCondition();
        eventCondition.setEventType(EventType.REG_INTEGRAL);
        eventCondition.setProcessType(ProcessType.NEW);
        return eventDao.queryEventByCondition(eventCondition);
    }

    @Override
    @Transactional(rollbackFor = MyBusinessException.class)//遇到自定义异常就回滚
    public void modifyEvent(Event event) {
        eventDao.modifyEvent(event);
    }

    @Override
    @Transactional(rollbackFor = MyBusinessException.class)//遇到自定义异常就回滚
    public void addEvent(Event event) {
        event.setId(null);
        eventDao.addEvent(event);
    }

    @Override
    public List<Event> queryEventByEventCondition(EventCondition eventCondition) {
        return eventDao.queryEventByCondition(eventCondition);
    }
}
