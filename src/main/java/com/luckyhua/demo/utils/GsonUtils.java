package com.luckyhua.demo.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.lang.reflect.Type;

/**
 * @author luckyhua
 * @date 2016/12/1
 * @description Gson请求帮助类
 */
public class GsonUtils {

    public static Gson gson = null; // 声明gson对象

    static {
        gson = new GsonBuilder()
                .setDateFormat(DateUtils.DEFAULT_DATETIME_FORMAT_PATTERN)
                .create(); // 创建gson对象，并设置日期格式
    }

    /**
     *  将对象转为JSON串，此方法能够满足大部分需求
     *
     * @param bean 对象
     * @return String
     */
    public static String toJson(Object bean){
        return gson.toJson(bean);
    }

    /**
     * 将泛型对象转为JSON串，如T estGeneric<String> t = new TestGeneric<String>();
     * Type type = new TypeToken<TestGeneric<String>>(){}.getType();
     *
     * @param bean
     * @param type
     * @return
     */
    public static String toJson(Object bean, Type type){
        return gson.toJson(bean, type);
    }

    /**
     * 用来将JSON串转为对象，此方法可用来转带泛型的集合，如：Type为
     *              new TypeToken<List<T>>(){}.getType()
     *              ，其它类也可以用此方法调用，就是将List<T>替换为你想要转成的类
     *
     * @param json json字符串
     * @param type 反射类型
     * @return Object
     */
    public static Object fromJson(String json, Type type){
        return gson.fromJson(json, type);
    }

    /**
     * 用来将JSON串转为对象，但此方法不可用来转带泛型的集合
     *
     * @param json json字符串
     * @param classOfT classOfT
     * @param <T> T
     * @return T
     */
    public static <T> T fromJson(String json, Class<T> classOfT){
        return gson.fromJson(json, classOfT);
    }

}
