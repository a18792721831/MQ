package com.study.routing;

import com.study.condition.EventCondition;
import com.study.domain.Event;
import com.study.domain.Integral;

import java.util.List;

/**
 * @author jiayq
 */
public interface IntegralRouting {

    /**
     * add integral and add event
     * event new,reg
     * @param integral
     */
    void addIntegral(Integral integral);

    /**
     * modify integral
     * @param integral
     */
    void modifyIntegral(Integral integral);

    /**
     * delete integral
     * integral isn's real delete
     * if deltete integral then event will be delete
     * and create event to delete subscriber
     * @param integral
     */
    void deleteIntegral(Integral integral);

    /**
     * query integral by integral's id
     * it will be return one integral or null
     * @param id
     * @return
     */
    Integral queryIntegralById(Long id);

    /**
     * query integral by subscriber's id
     * it will be return one integral or null
     * @param subscriberId
     * @return
     */
    Integral queryIntegralBySubscriberId(Long subscriberId);

    /**
     * query integral by amount
     * it will be return a List
     * the List's type is ArrayList,List has more than one integral or null
     * integral's amount greater than condition amount
     * @param amount
     * @return
     */
    List<Integral> queryIntegralByAmount(Double amount);

    /**
     * 获取积分数据库需要发布到用户数据库的事件
     * 即，创建积分引发的创建用户
     * @return
     */
    List<Event> getNewIntegralEvent();

    /**
     * 修改积分数据库的事件
     * @param event
     */
    void modifyEvent(Event event);

    /**
     * 同步事件：
     * 同步用户数据库发布的事件到积分数据库
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
