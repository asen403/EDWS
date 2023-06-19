package com.whs.edws.common;

public class ApiResponse<T> {

    public static Integer SUCCESS = 200;

    public static Integer FAIL = -1;


    private int code;

    private String msg;

    private T data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
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

    private ApiResponse(int code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public static <T> ApiResponse<T> success(int code, String msg, T data){
        return new ApiResponse<T>(code, msg, data);
    }

    public static <T> ApiResponse<T> success(String msg, T data){
        return ApiResponse.success(SUCCESS, msg, data);
    }

    public static <T> ApiResponse<T> success(T data){
        return ApiResponse.success("success", data);
    }

    public static <T> ApiResponse<T> success(){
        return ApiResponse.success(null);
    }

    public static <T> ApiResponse<T> fail(int code, String msg, T data){
        return new ApiResponse<T>(code, msg, data);
    }

    public static <T> ApiResponse<T> fail(String msg, T data){
        return ApiResponse.success(FAIL, msg, data);
    }

    public static <T> ApiResponse<T> fail(T data){
        return ApiResponse.success("success", data);
    }

    public static <T> ApiResponse<T> fail(){
        return ApiResponse.success(null);
    }






}
