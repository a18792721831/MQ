package com.study.util;

import com.study.domain.Event;
import com.study.exception.MyBusinessException;
import com.study.nems.ProcessType;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author jiayq
 */
@Component
public class EventFinishUtil {

    /**
     * 抽取的事件完工的操作
     * @param optional
     * @throws MyBusinessException
     */
    public static Event finishEvent(Optional optional) throws MyBusinessException {
        if (optional.isPresent()) {
            List<Event> eventList = (List<Event>) optional.get();
            if (eventList.size() > 1) {
                throw new MyBusinessException("find more than one event:subscriberId = " +
                        eventList.stream().map(event1 ->
                                event1.getSubscriberId()).collect(Collectors.toList()).toString());
            }
            Event finshEvent = eventList.get(0);
            finshEvent.setProcessType(ProcessType.FINISH);
            return finshEvent;
        }
        throw new MyBusinessException("not find finish event");
    }

}
