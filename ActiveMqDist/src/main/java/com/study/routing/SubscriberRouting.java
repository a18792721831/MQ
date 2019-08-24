package com.study.routing;

import com.study.condition.EventCondition;
import com.study.condition.SubscriberCondition;
import com.study.domain.Event;
import com.study.domain.Subscriber;

import java.util.List;

/**
 * @author jiayq
 */
public interface SubscriberRouting {

    /**
     * add subscriber and add event
     * event new,reg
     * @param subscriber
     */
    void addSubscriber(Subscriber subscriber);

    /**
     * modify subscriber
     * @param subscriber
     */
    void modifySubscriber(Subscriber subscriber);

    /**
     * delete subsciber
     * subscriber isn't real delete, just modfit subscriber's state
     * if delete subsciber then event will be delete
     * and create event to delete integral
     * @param subscriber
     */
    void deleteSubscriber(Subscriber subscriber);

    /**
     * query subscriber by subscriber's id
     * it will be return one subscriber or null
     * @param subsriberId
     * @return
     */
    Subscriber querySubscriberById(Long subsriberId);

    /**
     * query subscriber by subscriber condition
     * it will be return a List.
     * the List's type is ArrayList, List has more than one subscriber or null
     * @param condition
     * @return
     */
    List<Subscriber> querySubscriberByCondition(SubscriberCondition condition);


    /**
     * ��ȡ�û����ݿ���Ҫ�������������ݿ���¼�
     * �����û����ݿ����½����¼�
     * @return
     */
    List<Event> getNewEvent();

    /**
     * �޸��û��¼�����¼�
     * @param event
     */
    void modifyEvent(Event event);


    /**
     * �����¼����ӻ������ݿⷢ������Ϣ�õ�
     * �����������ݿ����½��Ļ��֣�ͬ�����û����ݿ�
     * @param event
     */
    void addEvent(Event event);

    /**
     * ����������ѯ�¼�
     * @param eventCondition
     * @return
     */
    List<Event> queryEventByEventCondition(EventCondition eventCondition);

}
