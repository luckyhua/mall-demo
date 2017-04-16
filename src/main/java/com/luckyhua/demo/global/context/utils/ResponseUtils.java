package com.luckyhua.demo.global.context.utils;

import com.luckyhua.demo.enums.PublicEnums;
import com.luckyhua.demo.enums.ResultEnums;
import com.luckyhua.demo.global.context.easyui.DataGridResult;
import com.luckyhua.demo.global.context.json.ResponseInfo;
import com.luckyhua.demo.global.context.model.PageInfo;

import java.util.List;

/**
 * @author luckyhua
 * @date 2016/11/24
 * @description result utils class
 */
public abstract class ResponseUtils {

    public static ResponseInfo buildResponseInfo() {
        return buildResponseInfo(PublicEnums.SUCCESS);
    }

    public static ResponseInfo buildResponseInfo(ResultEnums resultEnums) {
        return new ResponseInfo(resultEnums);
    }

    public static DataGridResult buildDataGridResult(PageInfo pageInfo, List<?> data){
        return new DataGridResult(pageInfo.getTotalCount(), data);
    }

}
