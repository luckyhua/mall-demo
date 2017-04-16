package com.luckyhua.demo.enums;

/**
 * @author luckyhua
 * @date 2016/11/23
 * @description 全局码
 */
public enum PublicEnums implements ResultEnums {

    SUCCESS(200, "请求成功!"),
    USER_NO_LOGIN(401, "请先登录后再操作!"),
    NOT_AUTHORIZED(403, "请求的资源没有权限!"),
    SERVER_EXCEPTION(500, "服务器异常!"),
    PARAMS_IS_NULL(1001, "参数不能为空!"),
    PARAMS_EXCEPTION(1002, "参数异常!"),
    ;

    private final int code;
    private final String msg;

    PublicEnums(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    @Override
    public int getCode() {
        return code;
    }

    @Override
    public String getMsg() {
        return msg;
    }

    public String toString() {
        return Integer.toString(this.code);
    }

    public static PublicEnums valueOf(int code) {
        PublicEnums[] var1 = values();
        int var2 = var1.length;

        for (int var3 = 0; var3 < var2; ++var3) {
            PublicEnums publicEnums = var1[var3];
            if (publicEnums.code == code) {
                return publicEnums;
            }
        }

        throw new IllegalArgumentException("No matching constant for [" + code + "]");
    }

}
