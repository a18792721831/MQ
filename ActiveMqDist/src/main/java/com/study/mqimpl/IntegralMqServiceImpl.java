package com.study.mqimpl;

import com.alibaba.fastjson.JSON;
import com.study.domain.Event;
import com.study.mqservice.IntegralMqService;
import com.study.nems.ProcessType;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.jms.Destination;
import java.util.function.Consumer;

/**
 * @author jiayq
 */
@Service("integralMqService")
public class IntegralMqServiceImpl implements IntegralMqService {

    @Resource(name = "jmsIntegralTemplate")
    private JmsTemplate jmsTemplate;

    @Override
    public void publishIntegralEvent(Destination topic, Event event, Consumer<Event> action) {
        jmsTemplate.send(topic,
                session -> session.createTextMessage(JSON.toJSONString(event)));
        event.setProcessType(ProcessType.PUBLISH);
        action.accept(event);
    }
}
