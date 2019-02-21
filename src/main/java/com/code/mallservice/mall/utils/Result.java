package com.code.mallservice.mall.utils;

public class Result {

    /**
     * 请求成功
     */
    public static final int STATUS_SUCCESS = 200;

    /**
     * 用户名或者密码错误
     */
    public static final int STATUS_USERNAME_OR_PASS_ERROR = 401;

    /**
     * 无授权
     */
    public static final int STATUS_NO_AUTHORIZATION = 403;

    /**
     * 内部错误
     */
    public static final int STATUS_ERROR = 500;
    /**
     * 上传运单文件已经存在
     */
    public static final int STATUS_FILE_EXIST = 501;
    /**
     * 登录超商或者登出
     */
    public static final int STATUS_LOGIN_OUT =203;

    private int status;

    private String msg;

    private Object data;


    public static Result ok() {
        Result result = new Result();
        result.setStatus(STATUS_SUCCESS);
        return result;
    }

    public static Result ok(Object object) {
        Result result = new Result();
        result.setStatus(STATUS_SUCCESS);
        result.setData(object);
        return result;
    }

    public static Result status(int status){
        Result result = new Result();
        result.setStatus(status);
        return result;
    }

    /**
     * 无授权
     *
     * @return
     */
    public static Result noAuthorization() {
        Result result = new Result();
        result.setStatus(STATUS_NO_AUTHORIZATION);
        return result;
    }
    public static Result loginOut(){
        Result result = new Result();
        result.setStatus(STATUS_LOGIN_OUT);
        return result;
    }
    /**
     * 用户名获取密码错误
     *
     * @return
     */
    public static Result userorpasserror() {
        Result result = new Result();
        result.setStatus(STATUS_USERNAME_OR_PASS_ERROR);
        return result;
    }

    /**
     * 错误
     *
     * @return
     */
    public static Result error() {
        Result result = new Result();
        result.setStatus(STATUS_ERROR);
        return result;
    }


    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
