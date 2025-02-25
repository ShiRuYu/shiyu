package com.shiyu.web.config;

import com.shiyu.commons.utils.BizResultCode;
import com.shiyu.commons.utils.LoggerUtil;
import com.shiyu.commons.utils.Result;
import com.shiyu.commons.utils.exception.BadRequestException;
import com.shiyu.commons.utils.exception.BizException;
import com.shiyu.commons.utils.exception.ValidatorException;
import jakarta.validation.ConstraintViolationException;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 异常处理
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BindException.class)
    public Result<String> exception(BindException e, BindingResult bindingResult) {
        LoggerUtil.COMMON_LOGGER.error(e.getMessage(), e);
        return Result.fail(BizResultCode.ERR_10006, getBindingResult(e, bindingResult));
    }

    private String getBindingResult(BindException e, BindingResult bindingResult) {
        if (CollectionUtils.isNotEmpty(bindingResult.getFieldErrors())) {
            StringBuilder stringBuilder = new StringBuilder();
            for (FieldError fieldError : bindingResult.getFieldErrors()) {
                stringBuilder.append(fieldError.getField())
                        .append(":")
                        .append(fieldError.getDefaultMessage())
                        .append("; ");
            }
            return stringBuilder.toString();
        }
        return e.getMessage();
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public Result<String> exception(ConstraintViolationException e) {
        LoggerUtil.COMMON_LOGGER.error(e.getMessage(), e);
        return Result.fail(BizResultCode.ERR_10007, e.getMessage());
    }

    @ExceptionHandler(ValidatorException.class)
    public Result<String> exception(ValidatorException e) {
        LoggerUtil.COMMON_LOGGER.error(e.getMessage(), e);
        return Result.fail(BizResultCode.ERR_10007, e.getMessage());
    }

    @ExceptionHandler(BadRequestException.class)
    public Result<String> exception(BadRequestException e) {
        LoggerUtil.COMMON_LOGGER.error(e.getMessage(), e);
        return Result.fail(BizResultCode.ERR_10008, e.getMessage());
    }

    @ExceptionHandler(BizException.class)
    public Result<String> exception(BizException e) {
        LoggerUtil.COMMON_LOGGER.error(e.getMessage(), e);
        return Result.fail(e.getCode(), e.getMessage());
    }

    @ExceptionHandler(value = {Exception.class, Throwable.class})
    public Result<String> exception(Throwable e) {
        LoggerUtil.COMMON_LOGGER.error(e.getMessage(), e);
        return Result.fail(BizResultCode.ERR, e.getMessage());
    }
}

