package com.shiyu.web;

import com.shiyu.biz.TestBizService;
import com.shiyu.core.TestCoreService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    private final TestBizService testBizService;
    private final TestCoreService testCoreService;

    public TestController(TestBizService testBizService, TestCoreService testCoreService) {
        this.testBizService = testBizService;
        this.testCoreService = testCoreService;
    }


    @GetMapping
    public String test() {
        return "test"+testBizService.testBiz()+testCoreService.testCore();
    }
}
