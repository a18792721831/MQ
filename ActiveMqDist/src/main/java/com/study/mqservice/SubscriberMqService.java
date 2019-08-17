package com.study.mqservice;

import com.study.domain.Event;

import javax.jms.Destination;
import java.util.function.Consumer;

/**
 * @author jiayq
 */
public interface SubscriberMqService {

    /**
     * �����û����ݿ��¼����������ݿ�
     * �������ݿ�1�����¼������ݿ�2
     * @param topic ָ������
     * @param event �������¼�
     * @param action �����ɹ���Ļص�(lambda)
     */
    void publishSubsciberEvent(Destination topic , Event event, Consumer<Event> action);

}
