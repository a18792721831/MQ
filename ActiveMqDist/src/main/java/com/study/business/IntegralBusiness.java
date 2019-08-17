package com.study.business;

import com.study.domain.Integral;

/**
 * @author jiayq
 */
public interface IntegralBusiness {

    /**
     * add integral
     * with publish mq
     * @param integral
     */
    void addIntegral(Integral integral);

    /**
     * delete integral
     * with publish mq
     * @param integral
     */
    void deleteIntegral(Integral integral);

}
