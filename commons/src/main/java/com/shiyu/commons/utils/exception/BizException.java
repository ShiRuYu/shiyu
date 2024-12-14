package com.shiyu.commons.utils.exception;

import lombok.Getter;

/**
 * 业务异常，这种异常一般是可预知的
 *
 */
@Getter
public class BizException extends RuntimeException {

    private final BizResultCode code;

    public BizException(BizResultCode bizResultCode) {
        super(bizResultCode.getMsg());
        this.code = bizResultCode;
    }

    public BizException(BizResultCode bizResultCode, String msg) {
        super(msg);
        this.code = bizResultCode;
    }

}
