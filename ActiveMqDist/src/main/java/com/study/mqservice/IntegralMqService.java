package com.study.mqservice;

import com.study.domain.Event;

import javax.jms.Destination;
import java.util.function.Consumer;

/**
 * @author jiayq
 */
public interface IntegralMqService {

    /**
     * 从积分数据库发布消息到用户数据库
     * 即从数据库2发布消息到数据库1
     * @param topic 主题
     * @param event 事件
     * @param action 回调
     */
    void publishIntegralEvent(Destination topic, Event event, Consumer<Event> action);

}
