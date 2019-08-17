package com.study.messagecomsume;

import com.alibaba.fastjson.JSON;
import com.study.condition.EventCondition;
import com.study.domain.Event;
import com.study.domain.Integral;
import com.study.exception.MyBusinessException;
import com.study.mqservice.IntegralMqService;
import com.study.nems.ProcessType;
import com.study.service.IntegralService;
import com.study.util.EventFinishUtil;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;
import javax.jms.*;
import java.util.Optional;

/**
 * @author jiayq
 */
public class IntegralMessageListener implements MessageListener {

    @Autowired
    private IntegralService integralService;

    @Autowired
    private IntegralMqService integralMqService;

    @Resource(name = "integralTopic")
    private Destination integralTopic;

    @Override
    public void onMessage(Message message) {
        if (message instanceof TextMessage) {
            try{
                TextMessage textMessage = (TextMessage) message;
                Event subEvent = JSON.parseObject(textMessage.getText(), Event.class);
                switch (subEvent.getProcessType()) {
                    case NEW:
                        Integral integral = JSON.parseObject(subEvent.getContent(), Integral.class);
                        integralService.addEvent(subEvent);
                        integralService.addIntegral(integral);
                        subEvent.setProcessType(ProcessType.FINISH);
                        integralService.modifyEvent(subEvent);
                        integralMqService.publishIntegralEvent(integralTopic, subEvent,
                                event -> System.out.println());
                        break;
                    case FINISH:
                        EventCondition eventCondition = new EventCondition();
                        eventCondition.setEventType(subEvent.getEventType());
                        eventCondition.setSubscriberId(subEvent.getSubscriberId());
                        integralService.modifyEvent(EventFinishUtil.finishEvent(
                                Optional.of(integralService.queryEventByEventCondition(eventCondition))));
                        break;
                        default:
                            throw new MyBusinessException("event process type is error");
                }
            } catch (JMSException e) {
                throw new MyBusinessException("get message text error");
            }
        } else {
            throw new MyBusinessException("only can text message");
        }
    }
}
