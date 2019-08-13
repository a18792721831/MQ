package com.study.dao.sub;

import com.study.condition.SubscriberCondition;
import com.study.domain.Subscriber;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author jiayq
 */
@Repository
public interface SubscriberDao {

    /**
     * add Subscriber
     * @param subscriber
     * @return
     */
    void addSubscriber(Subscriber subscriber);

    /**
     * modify Subscriber
     * @param subscriber
     * @return
     */
    void modifySubscriber(Subscriber subscriber);

    /**
     * delete Subscriber
     * @param subscriber
     * @return
     */
    void deleteSubscriber(Subscriber subscriber);

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
