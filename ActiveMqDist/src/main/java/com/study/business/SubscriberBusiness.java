package com.study.business;

import com.study.domain.Subscriber;

/**
 * @author jiayq
 */
public interface SubscriberBusiness {

    /**
     * add subscriber
     * with publish mq
     * @param subscriber
     */
    void addSubsciber(Subscriber subscriber);

    /**
     * delete subscriber
     * with public mq
     * @param subscriber
     */
    void deleteSubscriber(Subscriber subscriber);
}
