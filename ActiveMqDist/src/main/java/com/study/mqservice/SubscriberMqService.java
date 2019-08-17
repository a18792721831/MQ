package com.study.mqservice;

import com.study.domain.Event;

import javax.jms.Destination;
import java.util.function.Consumer;

/**
 * @author jiayq
 */
public interface SubscriberMqService {

    /**
     * 发布用户数据库事件到积分数据库
     * 即从数据库1发布事件到数据库2
     * @param topic 指定主题
     * @param event 发布的事件
     * @param action 发布成功后的回调(lambda)
     */
    void publishSubsciberEvent(Destination topic , Event event, Consumer<Event> action);

}
