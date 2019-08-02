package com.study.aspect;

import com.study.data.manage.DataSourceManage;
import com.study.nems.DataSourceType;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

import java.text.SimpleDateFormat;
import java.util.Date;

@Aspect
public aspect MyBusinessAspect {

    @Pointcut("execution(* com.study.dao.*+.*(..))")
    public void pointCutDao() {}

    @Pointcut("execution(* com.study.dao.sub.*+.*(..))")
    public void pointCutSubDao() {}

    @Pointcut("execution(* com.study.dao.inte.*+.*(..))")
    public void pointCutInteDao() {}

    @Before("pointCutInteDao()")
    public void beforeInteDao(JoinPoint joinPoint) {
        DataSourceManage.set(DataSourceType.DATA_SOURCE_INTE);
    }

    @After("pointCutInteDao()")
    public void afterInteDao(JoinPoint joinPoint) {
        DataSourceManage.set(DataSourceType.DATA_SOURCE_SUB);
    }

    @Before("pointCutSubDao()")
    public void beforeSubDao(JoinPoint joinPoint) {
        DataSourceManage.set(DataSourceType.DATA_SOURCE_SUB);
    }

    @After("pointCutSubDao()")
    public void afterSubDao(JoinPoint joinPoint) {
        DataSourceManage.set(DataSourceType.DATA_SOURCE_SUB);
    }

    @Before("pointCutDao()")
    public void beforeDao(JoinPoint joinPoint) {
        System.out.println("Dao     Info  "+showTime() + "[" + joinPoint.getSignature().getName()+ "]    before");
    }

    @After("pointCutDao()")
    public void afterDao(JoinPoint joinPoint) {
        System.out.println("Dao     Info  "+showTime() + "[" + joinPoint.getSignature().getName()+ "]    after");
    }

    private String showTime(){
        return "  "+new SimpleDateFormat("yyyy-mm-dd HH:mm:ss,s").format(new Date())+"  ";
    }
}
