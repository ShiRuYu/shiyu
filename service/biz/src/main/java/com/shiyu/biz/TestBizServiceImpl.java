package com.shiyu.biz;

import org.springframework.stereotype.Service;

@Service
public class TestBizServiceImpl implements TestBizService{
    @Override
    public String testBiz() {
        return "testServiceBiz";
    }
}
