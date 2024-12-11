package com.shiyu.web.test.controller;

import com.shiyu.commons.utils.Result;
import com.shiyu.core.domain.auth.model.UserAggregate;
import com.shiyu.core.domain.auth.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
@RequiredArgsConstructor
@Tag(name = "测试")
public class TestController {
    private final AuthService authService;
    @GetMapping("test")
    @Operation(summary = "测试请求")
    public Result<UserAggregate> test() {
        UserAggregate userAggregateById = authService.getUserAggregateById(1L);
        return Result.success(userAggregateById);
    }

}
