package com.fanr.graduation.service.impl;

import com.fanr.graduation.common.alipay.Alipay;
import com.fanr.graduation.common.alipay.AlipayBean;
import com.fanr.graduation.service.PayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alipay.api.AlipayApiException;

@Service
public class PayServiceImpl implements PayService {

    @Autowired
    private Alipay alipay;
    
    @Override
    public String aliPay(AlipayBean alipayBean) throws AlipayApiException {
        return alipay.pay(alipayBean);
    }

}