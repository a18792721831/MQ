package com.study.client;

import com.study.condition.EventCondition;
import com.study.dao.EventDao;
import com.study.data.manage.DataSourceManage;
import com.study.domain.Event;
import com.study.nems.DataSourceType;
import com.study.nems.ProcessType;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author jiayq
 */
public class Client {

    public static void main(String[] args) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring/*.xml");
        Event event = applicationContext.getBean("event", Event.class);
        EventDao eventDao = applicationContext.getBean("eventDao", EventDao.class);
        DataSourceManage.set(DataSourceType.DATA_SOURCE_INTE);
        eventDao.addEvent(event);
    }

}
