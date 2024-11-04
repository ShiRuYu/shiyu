package com.shiyu.core;

import org.springframework.stereotype.Service;

@Service
public class TestCoreServiceImpl implements TestCoreService {
    @Override
    public String testCore() {
        return "testServiceCore";
    }
}
