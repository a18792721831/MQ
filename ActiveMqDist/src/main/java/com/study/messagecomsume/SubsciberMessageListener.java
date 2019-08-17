package com.study.messagecomsume;

import com.alibaba.fastjson.JSON;
import com.study.condition.EventCondition;
import com.study.domain.Event;
import com.study.domain.Subscriber;
import com.study.exception.MyBusinessException;
import com.study.mqservice.SubscriberMqService;
import com.study.nems.EventType;
import com.study.nems.ProcessType;
import com.study.service.SubscriberService;
import com.study.util.EventFinishUtil;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;
import javax.jms.*;
import java.util.Optional;

/**
 * @author jiayq
 */
public class SubsciberMessageListener implements MessageListener {

    @Autowired
    private SubscriberService subscriberService;

    @Autowired
    private SubscriberMqService subscriberMqService;

    @Resource(name = "subscriberTopic")
    private Destination subscriberTopic;

    /**
     * 监听到积分发布的消息后，会将消息的内容即事件，
     * 同步到用户数据库，然后增加用户（此时因为已有用户Id的事件，所以
     * 不会新增事件），用户新增成功后会将用户数据库中的事件
     * 的状态置为已完成，然后将已完成的事件发布到积分数据库
     * 积分数据库收到时间后，判断是已完成的事件，会更新积分数据库的
     * 事件的状态为已完成。
     * @param message
     */
    @Override
    public void onMessage(Message message) {
        if (message instanceof TextMessage) {
            try{
                TextMessage textMessage = (TextMessage) message;
                Event event = JSON.parseObject(textMessage.getText(), Event.class);
                switch (event.getProcessType()) {
                    case NEW :
                        if(EventType.REG_SUB.equals(event.getEventType())) {
                            //说明是从新增用户的完成返回
                        }
                        Subscriber subscriber = JSON.parseObject(event.getContent(), Subscriber.class);
                        subscriberService.addEvent(event);
                        subscriberService.addSubscriber(subscriber);
                        event.setProcessType(ProcessType.FINISH);
                        subscriberService.modifyEvent(event);
                        subscriberMqService.publishSubsciberEvent(subscriberTopic, event,
                                event1 -> System.out.println());
                        break;
                    case FINISH:
                        EventCondition eventCondition = new EventCondition();
                        eventCondition.setSubscriberId(event.getSubscriberId());
                        eventCondition.setEventType(event.getEventType());
                        subscriberService.modifyEvent(EventFinishUtil.finishEvent(
                                Optional.of(subscriberService.queryEventByEventCondition(eventCondition))));
                        break;
                        default:
                            throw new MyBusinessException("event process type is error");
                }
            } catch (JMSException e) {
                throw new MyBusinessException("get message text error");
            }

        } else {
            throw new MyBusinessException("only can text!");
        }
    }
}
