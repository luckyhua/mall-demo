package com.luckyhua.demo.global.context.json;

import com.luckyhua.demo.enums.ResultEnums;
import com.luckyhua.demo.global.context.Describle;
import com.luckyhua.demo.global.context.ResultDataManager;
import com.luckyhua.demo.global.context.model.PageInfo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author luckyhua
 * @date 2016/11/24
 * @description
 */
public class ResponseInfo implements Serializable{

    public static final String Page_Info_Key = "pageInfo";

    private Integer code;

    private String msg;

    /**
     * 返回数据
     */
    private Map<String,Object> data = new HashMap<>();

    public ResponseInfo(ResultEnums resultEnums) {
        this.code = resultEnums.getCode();
        this.msg = resultEnums.getMsg();
    }

    public void putPageInfo(PageInfo pageInfo){
        if(pageInfo == null)
            return;
        this.data.put(Page_Info_Key, pageInfo);
    }

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

    public Map<String, Object> getData() {
        return data;
    }

    public void setData(Map<String, Object> data) {
        this.data = data;
    }

    public void putData(String key, Object value){
        if(value instanceof Describle){
            this.data.put(key, ((Describle) value).describle());
            return;
        }
        this.data.put(key, value);
    }

    /**
     * use the bean class simple name to key witch the first work is lower case
     * @param bean the field that return to app
     * @param fields
     */
    public void putBeanData(Object bean, String ... fields){
        String key = ResultDataManager.getBeanName(bean);
        Map<String, Object> values = ResultDataManager.getValues(bean, fields);
        this.data.put(key, values);
    }

    /**
     * use the user key to put bean <p>
     * @see #putBeanData(Object, String...)
     * @param key key in return json
     * @param bean data
     * @param fields fields is bean data
     */
    public void putBeanData(String key,Object bean,String ... fields){
        if(bean instanceof Map){
            data.put(key, bean);
            return;
        }

        if(fields==null||fields.length==0){
            ResultDataManager.initIntValue(bean);
            data.put(key, bean);
            return;
        }
        Map<String, Object> values = ResultDataManager.getValues(bean, fields);
        data.put(key, values);
    }

    /**
     * put list into data use customer key
     * @see #(List, String...)
     * @param key out json key
     * @param listBean out json list bean
     * @param inFields out json bean fields
     */
    @SuppressWarnings("unchecked")
    public void putListData(String key, List<?> listBean, String ... inFields){

        List<Map<String,Object>> retData = new ArrayList<>();

        if(listBean == null){
            data.put(key, new ArrayList<>());
            return;
        }

        for(Object bean:listBean){
            if(bean instanceof Map){
                retData.add((Map<String, Object>) bean);
                continue;
            }

            if(bean instanceof Describle){
                Map<String, Object> values = ((Describle) bean).describle();
                retData.add(values);
                continue;
            }
            Map<String, Object> values = ResultDataManager.getValues(bean, inFields);
            retData.add(values);
        }
        data.put(key, retData);
    }

    public void putBeanDataAll(Object bean){
        String key = ResultDataManager.getBeanName(bean);
        data.put(key, bean);
    }

    @Override
    public String toString() {
        return "ResponseInfo{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }
}
