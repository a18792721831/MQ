package com.study.businessimpl;

import com.alibaba.fastjson.JSON;
import com.study.business.IntegralBusiness;
import com.study.condition.EventCondition;
import com.study.domain.Event;
import com.study.domain.Integral;
import com.study.domain.Subscriber;
import com.study.mqservice.IntegralMqService;
import com.study.nems.EventType;
import com.study.nems.ProcessType;
import com.study.nems.State;
import com.study.service.IntegralService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.jms.Destination;
import java.util.List;
import java.util.Optional;
import java.util.Random;

/**
 * @author jiayq
 */
@Service("integralBusiness")
public class IntegralBusinessImpl implements IntegralBusiness {

    @Autowired
    private IntegralService integralService;

    @Autowired
    private IntegralMqService integralMqService;

    @Resource(name = "integralTopic")
    private Destination integralTopic;

    @Override
    public void addIntegral(Integral integral) {
        integralService.addIntegral(integral);
        EventCondition eventCondition = new EventCondition();
        eventCondition.setSubscriberId(integral.getSubscriberId());
        eventCondition.setProcessType(ProcessType.NEW);
        eventCondition.setEventType(EventType.REG_INTEGRAL);
        List<Event> eventList = integralService.queryEventByEventCondition(eventCondition);
        if (1 == eventList.size()) {
            Event event = eventList.get(0);
            Subscriber subscriber = new Subscriber();
            subscriber.setName(event.getSubscriberId().toString());
            subscriber.setCode(event.getSubscriberId().toString());
            subscriber.setPassword(event.getSubscriberId().toString());
            subscriber.setState(State.VALID);
            event.setContent(JSON.toJSONString(subscriber));
            integralMqService.publishIntegralEvent(integralTopic, event,
                    event1 -> integralService.modifyEvent(event1));
        }
    }

    @Override
    public void deleteIntegral(Integral integral) {

    }
}
