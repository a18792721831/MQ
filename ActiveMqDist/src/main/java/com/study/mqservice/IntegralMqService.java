package com.study.mqservice;

import com.study.domain.Event;

import javax.jms.Destination;
import java.util.function.Consumer;

/**
 * @author jiayq
 */
public interface IntegralMqService {

    /**
     * �ӻ������ݿⷢ����Ϣ���û����ݿ�
     * �������ݿ�2������Ϣ�����ݿ�1
     * @param topic ����
     * @param event �¼�
     * @param action �ص�
     */
    void publishIntegralEvent(Destination topic, Event event, Consumer<Event> action);

}
