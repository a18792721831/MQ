package com.study.routingimpl.integral;

import com.study.condition.EventCondition;
import com.study.domain.Event;
import com.study.domain.Integral;
import com.study.routing.IntegralRouting;
import com.study.service.IntegralService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author jiayq
 */
@Service("integralRouting")
public class IntegralRoutingImpl implements IntegralRouting {

    @Autowired
    private IntegralService integralService;

    @Override
    public void addIntegral(Integral integral) {
        integralService.addIntegral(integral);
    }

    @Override
    public void modifyIntegral(Integral integral) {
        integralService.modifyIntegral(integral);
    }

    @Override
    public void deleteIntegral(Integral integral) {
        integralService.deleteIntegral(integral);
    }

    @Override
    public Integral queryIntegralById(Long id) {
        return integralService.queryIntegralById(id);
    }

    @Override
    public Integral queryIntegralBySubscriberId(Long subscriberId) {
        return integralService.queryIntegralBySubscriberId(subscriberId);
    }

    @Override
    public List<Integral> queryIntegralByAmount(Double amount) {
        return integralService.queryIntegralByAmount(amount);
    }

    @Override
    public List<Event> getNewIntegralEvent() {
        return integralService.getNewIntegralEvent();
    }

    @Override
    public void modifyEvent(Event event) {
        integralService.modifyEvent(event);
    }

    @Override
    public void addEvent(Event event) {
        integralService.addEvent(event);
    }

    @Override
    public List<Event> queryEventByEventCondition(EventCondition eventCondition) {
        return integralService.queryEventByEventCondition(eventCondition);
    }
}
