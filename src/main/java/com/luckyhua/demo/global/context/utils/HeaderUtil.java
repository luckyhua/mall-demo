package com.luckyhua.demo.global.context.utils;

import org.springframework.http.HttpHeaders;

/**
 * Utility class for HTTP headers creation.
 */
public class HeaderUtil {

    public static HttpHeaders createAlert(String message, String param) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("X-luckyhua-alert", message);
        headers.add("X-luckyhua-params", param);
        return headers;
    }

    public static HttpHeaders createEntityCreateAlert(String entityName, String param) {
        return createAlert("luckyhua." + entityName + ".created", param);
    }

    public static HttpHeaders createEntityUpdateAlert(String entityName, String param) {
        return createAlert("luckyhua." + entityName + ".updated", param);
    }

    public static HttpHeaders createEntityDeleteAlert(String entityName, String param) {
        return createAlert("luckyhua." + entityName + ".deleted", param);
    }

    public static HttpHeaders createEntityFailAlert(String entityName, String errorKey, String defaultMessage) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("X-luckyhua-error", "error." + errorKey);
        headers.add("X-luckyhua-params", entityName);
        return headers;
    }

}
