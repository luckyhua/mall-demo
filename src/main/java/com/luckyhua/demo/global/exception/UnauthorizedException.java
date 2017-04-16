package com.luckyhua.demo.global.exception;

import com.luckyhua.demo.enums.ResultEnums;

/**
 * @author luckyhua
 * @date 2016/11/30
 * @description 未授权异常
 */
public class UnauthorizedException extends RuntimeException {

    private ResultEnums resultEnums;

    public ResultEnums getResultEnums() {
        return resultEnums;
    }

    public void setResultEnums(ResultEnums resultEnums) {
        this.resultEnums = resultEnums;
    }

    private UnauthorizedException() {}

    public UnauthorizedException(ResultEnums resultEnums) {
        super(resultEnums.getMsg());
        this.resultEnums = resultEnums;
    }

}
