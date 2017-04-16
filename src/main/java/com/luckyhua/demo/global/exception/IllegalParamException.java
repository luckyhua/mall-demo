package com.luckyhua.demo.global.exception;

import com.luckyhua.demo.enums.ResultEnums;

/**
 * @author luckyhua
 * @date 2016/11/30
 * @description 非法参数异常
 */
public class IllegalParamException extends RuntimeException {

    private ResultEnums resultEnums;

    public ResultEnums getResultEnums() {
        return resultEnums;
    }

    public void setResultEnums(ResultEnums resultEnums) {
        this.resultEnums = resultEnums;
    }

    private IllegalParamException() {}

    public IllegalParamException(ResultEnums resultEnums) {
        super(resultEnums.getMsg());
        this.resultEnums = resultEnums;
    }

}
