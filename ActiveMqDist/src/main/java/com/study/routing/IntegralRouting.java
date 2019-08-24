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
     * ��ȡ�������ݿ���Ҫ�������û����ݿ���¼�
     * �����������������Ĵ����û�
     * @return
     */
    List<Event> getNewIntegralEvent();

    /**
     * �޸Ļ������ݿ���¼�
     * @param event
     */
    void modifyEvent(Event event);

    /**
     * ͬ���¼���
     * ͬ���û����ݿⷢ�����¼����������ݿ�
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
