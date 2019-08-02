package com.study.dao.sub;

import com.study.condition.SubscriberCondition;
import com.study.domain.Subscriber;

import java.util.List;

/**
 * @author jiayq
 */
public interface SubscriberDao {

    /**
     * add Subscriber
     * @param subscriber
     * @return
     */
    Subscriber addSubscriber(Subscriber subscriber);

    /**
     * modify Subscriber
     * @param subscriber
     * @return
     */
    Subscriber modifySubscriber(Subscriber subscriber);

    /**
     * delete Subscriber
     * @param subscriber
     * @return
     */
    Subscriber deleteSubscriber(Subscriber subscriber);

    /**
     * query Subscriber by id
     * @param id
     * @return
     */
    Subscriber querySubscriberById(Long id);

    /**
     * query Subscriber by condition
     * @param condition
     * @return
     */
    List<Subscriber> querySubscriberByCondition(SubscriberCondition condition);

}
