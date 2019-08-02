package com.study.dao;

import com.study.condition.EventCondition;
import com.study.domain.Event;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.beans.Transient;
import java.util.List;

/**
 * @author jiayq
 */
@Repository(value = "eventDao")
public interface EventDao {

    /**
     * add Event
     * @param event
     * @return
     */
    void addEvent(Event event);

    /**
     * modify Event
     * @param event
     * @return
     */
    void modifyEvent(Event event);

    /**
     * delete Event
     * @param event
     * @return
     */
    void delteEvent(Event event);

    /**
     * query Event by id
     * @param id
     * @return
     */
    Event queryEventById(Long id);

    /**
     * query Event by condition
     * @param condition
     * @return
     */
    List<Event> queryEventByCondition(EventCondition condition);
}
