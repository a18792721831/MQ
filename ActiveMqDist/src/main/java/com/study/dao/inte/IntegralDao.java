package com.study.dao.inte;

import com.study.domain.Integral;

import java.util.List;

/**
 * @author jiayq
 */
public interface IntegralDao {

    /**
     * add Integral
     * @param integral
     * @return
     */
    Integral addIntegral(Integral integral);

    /**
     * modify Integral
     * @param integral
     * @return
     */
    Integral modifyIntegral(Integral integral);

    /**
     * delete Integral
     * @param integral
     * @return
     */
    Integral deleteIntegral(Integral integral);

    /**
     * query Integral by id
     * @param id
     * @return
     */
    Integral queryIntegralById(Long id);

    /**
     * query Integral by subscriber id
     * @param id
     * @return
     */
    Integral queryIntegralBySubscriberId(Long id);

    /**
     * query Integral by amount
     * @param amount
     * @return
     */
    List<Integral> queryIntegralByAmount(Double amount);
}
