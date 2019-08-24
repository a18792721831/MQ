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
     * 获取用户数据库需要发布到积分数据库的事件
     * 即，用户数据库中新建的事件
     * @return
     */
    List<Event> getNewEvent();

    /**
     * 修改用户事件表的事件
     * @param event
     */
    void modifyEvent(Event event);


    /**
     * 增加事件，从积分数据库发布的消息得到
     * 即，积分数据库中新建的积分，同步到用户数据库
     * @param event
     */
    void addEvent(Event event);

    /**
     * 根据条件查询事件
     * @param eventCondition
     * @return
     */
    List<Event> queryEventByEventCondition(EventCondition eventCondition);

}
