package com.luckyhua.demo.global.exception;

import com.luckyhua.demo.global.context.json.ResponseInfo;
import com.luckyhua.demo.global.exception.utils.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @author luckyhua
 * @date 2016/11/24
 * @description 全局异常处理
 */
@RestController
@ControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public void defaultExceptionHandler(HttpServletRequest req, Exception e) throws Exception {
        log.error("ZGH10060: DefaultException Handler---Host {} invokes url {} ERROR: {}",
                req.getRemoteHost(), req.getRequestURL(), e.getMessage());
        log.error("ZGH10210: exception = {} ", ExceptionUtils.getTrace(e));
    }

    @ExceptionHandler(value = NoLoginException.class)
    @ResponseBody
    public Object noLoginExceptionHandler(HttpServletRequest req, NoLoginException e) throws Exception {
        log.error("ZGH10060: NoLoginException Handler---Host {} invokes url {} ERROR: {}",
                req.getRemoteHost(), req.getRequestURL(), e.getMessage());
        return new ResponseInfo(e.getResultEnums());
    }

    @ExceptionHandler(value = IllegalParamException.class)
    @ResponseBody
    public Object illegalParamExceptionHandler(HttpServletRequest req, IllegalParamException e) throws Exception {
        log.error("ZGH10060: IllegalParamException Handler---Host {} invokes url {} ERROR: {}",
                req.getRemoteHost(), req.getRequestURL(), e.getMessage());
        return new ResponseInfo(e.getResultEnums());
    }

    @ExceptionHandler(value = ResponseException.class)
    @ResponseBody
    public Object responseExceptionHandler(HttpServletRequest req, ResponseException e) throws Exception {
        log.error("ZGH10060: ResponseException Handler---Host {} invokes url {} ERROR: {}",
                req.getRemoteHost(), req.getRequestURL(), e.getMessage());
        return new ResponseInfo(e.getResultEnums());
    }

    @ExceptionHandler(value = UnauthorizedException.class)
    @ResponseBody
    public Object unauthorizedExceptionHandler(HttpServletRequest req, UnauthorizedException e) throws Exception {
        log.error("ZGH10060: UnauthorizedException Handler---Host {} invokes url {} ERROR: {}",
                req.getRemoteHost(), req.getRequestURL(), e.getMessage());
        return new ResponseInfo(e.getResultEnums());
    }


}
