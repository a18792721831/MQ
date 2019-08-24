package com.study.client;

import com.study.business.IntegralBusiness;
import com.study.business.SubscriberBusiness;
import com.study.domain.Integral;
import com.study.domain.Subscriber;
import com.study.exception.MyBusinessException;
import com.study.nems.State;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Scanner;

/**
 * @author jiayq
 */
public class Client {

    private static Scanner scanner = new Scanner(System.in);

    private static final String SUB = "1";

    public static void main(String[] args) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring/*.xml");
        SubscriberBusiness subscriberBusiness = applicationContext.getBean(
                "subscriberBusiness", SubscriberBusiness.class);
        IntegralBusiness integralBusiness = applicationContext.getBean(
                "integralBusiness", IntegralBusiness.class);
        while (true) {
            showWelcome();
            String inputModel = getInputModelChar();
            if (SUB.equals(inputModel)) {
                subscriberBusiness.addSubsciber(getSubscriberInfo());
            } else {
                integralBusiness.addIntegral(getIntegralInfo());
            }
        }

    }

    private static Integral getIntegralInfo() {
        Integral integral = new Integral();
        System.out.println("please input integral info:");
        System.out.println("integral amount:");
        integral.setAmount(scanner.nextDouble());
        System.out.println("integral subscriberId:");
        integral.setSubscriberId(scanner.nextLong());
        integral.setState(State.VALID);
        return integral;
    }

    private static Subscriber getSubscriberInfo() {
        Subscriber subscriber = new Subscriber();
        System.out.println("please input subscriber info:");
        System.out.println("subscriber name:");
        subscriber.setName(getInputInfo());
        System.out.println("subscriber code:");
        subscriber.setCode(getInputInfo());
        System.out.println("subscriber password");
        subscriber.setPassword(getInputInfo());
        subscriber.setState(State.VALID);
        return subscriber;
    }

    private static String getInputInfo() {
        return scanner.next();
    }

    private static void showWelcome() {
        System.out.println("欢迎使用基于spring+mybatis集成ActiveMQ实现分布式数据库以事件表的方式实现的一致性测试项目");
        System.out.println("本项目使用spring作为基础框架，用来管理项目中的bean和service");
        System.out.println("项目的数据持久集成了mybatis作为持久化框架，因为mybatis可以很好的支持动态SQL" +
                "虽然动态SQL做的验证应该在service中就完成");
        System.out.println("为了实现控制-业务-数据分离，所以分为控制层、业务层、数据持久层");
        System.out.println("dao层用mybatis的动态SQL实现");
        System.out.println("service层分为控制层和业务层，业务层是service实现");
        System.out.println("而mqservice是控制层，控制消息发布的服务");
        System.out.println("还有消息消费，是在messagecomsume包中");
        System.out.println("菜单：");
        System.out.println("1:增加用户");
        System.out.println("2:增加积分");
        System.out.println("q:退出");
        System.out.println("so, please input:");
    }

    private static String getInputModelChar() {
        String input = scanner.next();
        switch (input) {
            case "1":
            case "2":
                return input;
            case "q":
                System.out.println("thank you, will be quit!");
                scanner.close();
                System.exit(1);
                break;
                default:
                    System.out.println("you input others,please input one of \"1\",\"2\" or \"q\"");
                    return getInputModelChar();
        }
        throw new MyBusinessException("get input error");
    }

}