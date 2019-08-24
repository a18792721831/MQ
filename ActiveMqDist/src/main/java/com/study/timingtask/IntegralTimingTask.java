package com.study.timingtask;

import com.study.domain.Event;
import com.study.mqservice.IntegralMqService;
import com.study.routing.IntegralRouting;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.jms.Destination;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @author jiayq
 */
@Component
public class IntegralTimingTask {

    @Autowired
    private IntegralRouting integralRouting;

    @Autowired
    private IntegralMqService integralMqService;

    @Resource(name = "integralTopic")
    private Destination integralTopic;

    /**
     * 定时任务将新增的事件发布为消息
     * 发布完成后
     * 5seconds/times
     */
    @Scheduled(cron = "*/5 * * * * *")
    public void execute() {
//        System.out.println(new SimpleDateFormat("yyyy-mm-dd hh:mm:ss").format(new Date().toString()));
        List<Event> integralEventList = integralRouting.getNewIntegralEvent();
        integralEventList.forEach(event ->
                integralMqService.publishIntegralEvent(
                        integralTopic, event, event1 ->
                                integralRouting.modifyEvent(event1)));
    }
}
