package com.study.timingtask;

import com.study.mqservice.IntegralMqService;
import com.study.service.IntegralService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.jms.Destination;

/**
 * @author jiayq
 */
@Component
public class IntegralTimingTask {

    @Autowired
    private IntegralService integralService;

    @Autowired
    private IntegralMqService integralMqService;

    @Resource(name = "integralTopic")
    private Destination integralTopic;

    /**
     * ��ʱ�����������¼�����Ϊ��Ϣ
     * ������ɺ�
     * 5seconds/times
     */
    @Scheduled(cron = "*/5 * * * * *")
    public void execute() {
        integralService.getNewIntegralEvent().forEach(event ->
                integralMqService.publishIntegralEvent(
                        integralTopic, event, event1 ->
                                integralService.modifyEvent(event1)));
    }
}
