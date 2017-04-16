package com.luckyhua.demo.global.exception.utils;

import com.luckyhua.demo.enums.ResultEnums;
import com.luckyhua.demo.global.exception.IllegalParamException;
import com.luckyhua.demo.global.exception.NoLoginException;
import com.luckyhua.demo.global.exception.ResponseException;
import com.luckyhua.demo.global.exception.UnauthorizedException;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * @author luckyhua
 * @date 2016/11/24
 * @description 异常工具类
 */
public abstract class ExceptionUtils {

    public static void throwResponseException(ResultEnums resultEnums) throws ResponseException {
        throw new ResponseException(resultEnums);
    }

    public static void throwIllegalParamException(ResultEnums resultEnums) throws IllegalParamException {
        throw new IllegalParamException(resultEnums);
    }

    public static void throwNoLoginException(ResultEnums resultEnums) throws NoLoginException {
        throw new NoLoginException(resultEnums);
    }

    public static void throwUnauthorizedException(ResultEnums resultEnums) throws UnauthorizedException {
        throw new UnauthorizedException(resultEnums);
    }

    public static String getTrace(Throwable t) {
        StringWriter stringWriter= new StringWriter();
        PrintWriter writer= new PrintWriter(stringWriter);
        t.printStackTrace(writer);
        StringBuffer buffer= stringWriter.getBuffer();
        return buffer.toString();
    }

}
