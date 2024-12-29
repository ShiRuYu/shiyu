package com.shiyu.commons.utils.exception;

import com.shiyu.commons.utils.BizResultCode;
import lombok.Getter;

@Getter
public class ValidatorException extends RuntimeException {

    private final BizResultCode errorCode;

    public ValidatorException(String message) {
        super(message);
        this.errorCode = BizResultCode.ERR_10007;
    }

    public ValidatorException(String message, Throwable cause) {
        super(message, cause);
        this.errorCode = BizResultCode.ERR_10007;
    }

    public ValidatorException(BizResultCode errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }

    public ValidatorException(BizResultCode errorCode, String message, Throwable cause) {
        super(message, cause);
        this.errorCode = errorCode;
    }
}
