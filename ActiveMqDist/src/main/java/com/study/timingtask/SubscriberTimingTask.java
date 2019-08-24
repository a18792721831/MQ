package com.study.timingtask;

import com.study.domain.Event;
import com.study.mqservice.SubscriberMqService;
import com.study.routing.SubscriberRouting;
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
    private SubscriberRouting subscriberRouting;

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
//        System.out.println(new SimpleDateFormat("yyyy-mm-dd hh:mm:ss").format(new Date().toString()));
        List<Event> subscriberEvent = subscriberRouting.getNewEvent();
        subscriberEvent.forEach(event ->
                subscriberMqService.publishSubsciberEvent(
                        subscriberTopic, event, event1 ->
                                subscriberRouting.modifyEvent(event1)));
    }
}
