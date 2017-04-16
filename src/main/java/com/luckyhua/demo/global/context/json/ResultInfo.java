package com.luckyhua.demo.global.context.json;

import com.luckyhua.demo.utils.GsonUtils;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * @author luckyhua
 * @date 2016/12/1
 * @description 统一数据接收
 */
public class ResultInfo<T> implements Serializable {

    private Integer code;

    private String msg;

    private T data;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public static ResultInfo fromJson(String jsonStr, Class clazz) {
        Type jsonType = type(ResultInfo.class, clazz);
        return GsonUtils.gson.fromJson(jsonStr, jsonType);
    }

    public String toJson(Class<T> clazz) {
        Type jsonType = type(ResultInfo.class, clazz);
        return GsonUtils.gson.toJson(this, jsonType);
    }

    static ParameterizedType type(final Class raw, final Type... args) {
        return new ParameterizedType() {
            public Type getRawType() {
                return raw;
            }

            public Type[] getActualTypeArguments() {
                return args;
            }

            public Type getOwnerType() {
                return null;
            }
        };
    }
}
