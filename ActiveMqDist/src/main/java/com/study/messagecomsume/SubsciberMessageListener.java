package com.study.messagecomsume;

import com.alibaba.fastjson.JSON;
import com.study.condition.EventCondition;
import com.study.domain.Event;
import com.study.domain.Subscriber;
import com.study.exception.MyBusinessException;
import com.study.mqservice.SubscriberMqService;
import com.study.nems.EventType;
import com.study.nems.ProcessType;
import com.study.routing.SubscriberRouting;
import com.study.util.EventFinishUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.jms.*;
import java.util.Optional;

/**
 * @author jiayq
 */
@Component
public class SubsciberMessageListener implements MessageListener {

    @Autowired
    private SubscriberRouting subscriberRouting;

    @Autowired
    private SubscriberMqService subscriberMqService;

    @Resource(name = "subscriberTopic")
    private Destination subscriberTopic;

    /**
     * ���������ַ�������Ϣ�󣬻Ὣ��Ϣ�����ݼ��¼���
     * ͬ�����û����ݿ⣬Ȼ�������û�����ʱ��Ϊ�����û�Id���¼�������
     * ���������¼������û������ɹ���Ὣ�û����ݿ��е��¼�
     * ��״̬��Ϊ����ɣ�Ȼ������ɵ��¼��������������ݿ�
     * �������ݿ��յ�ʱ����ж�������ɵ��¼�������»������ݿ��
     * �¼���״̬Ϊ����ɡ�
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
                            //˵���Ǵ������û�����ɷ���
                        }
                        Subscriber subscriber = JSON.parseObject(event.getContent(), Subscriber.class);
                        subscriberRouting.addEvent(event);
                        subscriberRouting.addSubscriber(subscriber);
                        event.setProcessType(ProcessType.FINISH);
                        subscriberRouting.modifyEvent(event);
                        subscriberMqService.publishSubsciberEvent(subscriberTopic, event,
                                event1 -> System.out.println());
                        break;
                    case FINISH:
                        EventCondition eventCondition = new EventCondition();
                        eventCondition.setSubscriberId(event.getSubscriberId());
                        eventCondition.setEventType(event.getEventType());
                        subscriberRouting.modifyEvent(EventFinishUtil.finishEvent(
                                Optional.of(subscriberRouting.queryEventByEventCondition(eventCondition))));
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