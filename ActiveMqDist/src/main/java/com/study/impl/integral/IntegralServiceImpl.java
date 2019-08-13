package com.study.impl.integral;

import com.study.dao.EventDao;
import com.study.dao.inte.IntegralDao;
import com.study.domain.Integral;
import com.study.service.IntegralService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author jiayq
 */
public class IntegralServiceImpl implements IntegralService {

    @Autowired
    private IntegralDao integralDao;

    @Autowired
    private EventDao eventDao;

    @Override
    public void addIntegral(Integral integral) {

    }

    @Override
    public void modifyIntegral(Integral integral) {

    }

    @Override
    public void deleteIntegral(Integral integral) {

    }

    @Override
    public Integral queryIntegralById(Long id) {
        return null;
    }

    @Override
    public Integral queryIntegralBySubscriberId(Long subscriberId) {
        return null;
    }

    @Override
    public List<Integral> queryIntegralByAmount(Double amount) {
        return null;
    }
}
