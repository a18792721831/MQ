package com.study.aspect;

import com.study.data.manage.DataSourceManage;
import com.study.nems.DataSourceType;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.logging.Logger;

/**
 * @author jiayq
 */
@Component
@Aspect
public class MyBusinessAspect {

    private static Logger logger = Logger.getLogger(MyBusinessAspect.class.getName());

    @Pointcut("execution(* com.study.serviceimpl.subscriber.*.*(..))")
    public void subscriberServicePointCut() {}

    @Pointcut("execution(* com.study.serviceimpl.integral.*.*(..))")
    public void integralServicePointCut() {}

    @After("integralServicePointCut()")
    public void integralAfter(JoinPoint joinPoint) {
        logger.info("integralAfter");
        Arrays.stream(joinPoint.getArgs()).forEach(integral -> {
            logger.info("integral:" + integral);
        });
        if (DataSourceManage.get() != null) {
            logger.info("set db is null");
            DataSourceManage.set(null);
        }
    }

    @Before("integralServicePointCut()")
    public void integralBefore(JoinPoint joinPoint){
        logger.info("integralBefore");
        Arrays.stream(joinPoint.getArgs()).forEach(integral -> {
            logger.info("integral:" + integral);
        });
        if (!DataSourceType.DATA_SOURCE_INTE.equals(DataSourceManage.get())) {
            logger.info("set db is DATA_SOURCE_INTE");
            DataSourceManage.set(DataSourceType.DATA_SOURCE_INTE);
        }
    }

    @Before("subscriberServicePointCut()")
    public void subscriberBefore(JoinPoint joinPoint) {
        logger.info("subscriberBefore");
        Arrays.stream(joinPoint.getArgs()).forEach(subscriber -> {
            logger.info("subscriber:" + subscriber);
        });
        if (!DataSourceType.DATA_SOURCE_SUB.equals(DataSourceManage.get())) {
            logger.info("set db is DATA_SOURCE_SUB");
            DataSourceManage.set(DataSourceType.DATA_SOURCE_SUB);
        }
    }

    @After("subscriberServicePointCut()")
    public void subscriberAfter(JoinPoint joinPoint) {
        logger.info("subscriberAfter");
        Arrays.stream(joinPoint.getArgs()).forEach(subscriber -> {
            logger.info("subscriber:" + subscriber);
        });
        if (DataSourceManage.get() != null) {
            logger.info("set db is null");
            DataSourceManage.set(null);
        }
    }

}
