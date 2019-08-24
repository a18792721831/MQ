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
import com.study.routing.IntegralRouting;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.jms.Destination;
import java.util.List;

/**
 * @author jiayq
 */
@Service("integralBusiness")
public class IntegralBusinessImpl implements IntegralBusiness {

    @Autowired
    private IntegralRouting integralRouting;

    @Autowired
    private IntegralMqService integralMqService;

    @Resource(name = "integralTopic")
    private Destination integralTopic;

    @Override
    public void addIntegral(Integral integral) {
        integralRouting.addIntegral(integral);
        EventCondition eventCondition = new EventCondition();
        eventCondition.setSubscriberId(integral.getSubscriberId());
        eventCondition.setEventType(EventType.REG_INTEGRAL);
        List<Event> eventList = integralRouting.queryEventByEventCondition(eventCondition);
        if (1 == eventList.size()) {
            Event event = eventList.get(0);
            Subscriber subscriber = new Subscriber();
            subscriber.setName(event.getSubscriberId().toString());
            subscriber.setCode(event.getSubscriberId().toString());
            subscriber.setPassword(event.getSubscriberId().toString());
            subscriber.setState(State.VALID);
            event.setContent(JSON.toJSONString(subscriber));
            integralMqService.publishIntegralEvent(integralTopic, event,
                    event1 -> integralRouting.modifyEvent(event1));
        }
    }

    @Override
    public void deleteIntegral(Integral integral) {

    }
}
