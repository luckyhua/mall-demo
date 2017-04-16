package com.luckyhua.demo.enums;

/**
 * @author luckyhua
 * @date 2016/11/23
 * @description 全局码
 */
public enum UserEnums implements ResultEnums {

    USER_NOT_EXISTS(10001, "用户不存在!"),
    PASSWORD_ERROR(10002, "密码错误!"),
    USER_IS_EXISTS(10003, "用户名已存在!"),
    USER_IS_NULL(10004, "用户名不能为空!"),
    PASSWORD_IS_NULL(10005, "密码不能为空!"),
    PASSWORD_LENGTH_ERROR(10006, "密码长度必须为6到18位!"),
    ;

    private final int code;
    private final String msg;

    UserEnums(int code, String msg) {
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

    public static UserEnums valueOf(int code) {
        UserEnums[] var1 = values();
        int var2 = var1.length;

        for (int var3 = 0; var3 < var2; ++var3) {
            UserEnums userEnums = var1[var3];
            if (userEnums.code == code) {
                return userEnums;
            }
        }

        throw new IllegalArgumentException("No matching constant for [" + code + "]");
    }

}
