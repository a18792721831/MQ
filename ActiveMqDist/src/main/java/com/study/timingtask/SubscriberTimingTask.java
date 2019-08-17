package com.study.timingtask;

import com.study.domain.Event;
import com.study.mqservice.SubscriberMqService;
import com.study.service.SubscriberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.jms.Destination;
import java.util.List;

/**
 * @author jiayq
 */
@Component
public class SubscriberTimingTask {

    @Autowired
    private SubscriberService subscriberService;

    @Autowired
    private SubscriberMqService subscriberMqService;

    @Resource(name = "subscriberTopic")
    private Destination subscriberTopic;

    /**
     * 定时任务将新增的事件发布为消息
     * 发布完成后
     * 5seconds/times
     */
    @Scheduled(cron = "*/5 * * * * *")
    public void execute() {
        List<Event> subscriberEvent = subscriberService.getNewEvent();
        subscriberEvent.forEach(event ->
                subscriberMqService.publishSubsciberEvent(
                        subscriberTopic, event, event1 ->
                                subscriberService.modifyEvent(event1)));
    }
}
