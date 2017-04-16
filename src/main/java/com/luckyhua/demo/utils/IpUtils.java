package com.luckyhua.demo.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;

/**
 * IP地址相关方法
 *
 * @author zhangzheng
 * @date 2016/11/21
 * @version 1.0.0
 */
public class IpUtils {
    private static final Logger log = LoggerFactory.getLogger(IpUtils.class);

    /**
     * 获取请求主机IP地址,如果通过代理进来，则透过防火墙获取真实IP地址
     * @return String
     */
    public static String getIpAddress(HttpServletRequest request) {
        String ip = request.getHeader("X-Forwarded-For");
        if (!StringUtils.isEmpty(ip) && !"unknown".equalsIgnoreCase(ip)) {
            if (log.isInfoEnabled()) {
                log.info("X-Forwarded-For --- ip = " + ip);
            }

            int index = ip.indexOf(",");
            if (index != -1) {
                return ip.substring(0, index);
            } else {
                return ip;
            }
        }

        ip = request.getHeader("WL-Proxy-Client-IP");
        if (!StringUtils.isEmpty(ip) && !"unknown".equalsIgnoreCase(ip)) {
            if (log.isInfoEnabled()) {
                log.info("Proxy-Client-IP --- ip = " + ip);
            }
            return ip;
        }

        ip = request.getHeader("Proxy-Client-IP");
        if (!StringUtils.isEmpty(ip) && !"unknown".equalsIgnoreCase(ip)) {
            if (log.isInfoEnabled()) {
                log.info("Proxy-Client-IP --- ip = " + ip);
            }
            return ip;
        }

        ip = request.getHeader("HTTP_CLIENT_IP");
        if (!StringUtils.isEmpty(ip) && !"unknown".equalsIgnoreCase(ip)) {
            if (log.isInfoEnabled()) {
                log.info("HTTP_CLIENT_IP --- ip = " + ip);
            }
            return ip;
        }

        ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        if (!StringUtils.isEmpty(ip) && !"unknown".equalsIgnoreCase(ip)) {
            if (log.isInfoEnabled()) {
                log.info("HTTP_X_FORWARDED_FOR --- ip = " + ip);
            }
            return ip;
        }

        ip = request.getRemoteAddr();
        if (log.isInfoEnabled()) {
            log.info("get remote addr --- ip = " + ip);
        }
        return ip;
    }
}
