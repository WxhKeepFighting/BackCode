package com.wxh.musicsystem.exception;

import lombok.Data;

//请求响应
@Data
public class AjaxResponse {
    private boolean isok;
    private int code;
    private String message;
    private Object data;

    private AjaxResponse(){}

    //请求出现异常时的响应数据封装
    public static AjaxResponse error(CustomException e){
        AjaxResponse resultBean = new AjaxResponse();
        resultBean.setIsok(false);
        resultBean.setCode(e.getCode());
        if (e.getCode() == CustomExceptionType.USER_INPUT_ERROR.getCode()){
            resultBean.setMessage(e.getMessage());
        }else if (e.getCode() == CustomExceptionType.SYSTEM_ERROR.getCode()){
            resultBean.setMessage(e.getMessage()+"，系统出现异常，请联系管理员进行处理");
        }else {
            resultBean.setMessage(e.getMessage()+"，系统出现未知异常，请联系管理员进行处理");
        }
        return resultBean;
    }

    //需要给前端传值时的操作
    public static AjaxResponse success(Object data){
        AjaxResponse response = new AjaxResponse();
        response.setIsok(true);
        response.setCode(200);
        response.setData(data);
        response.setMessage("success");
        return response;
    }

    //不需要给前端传值的操作
    public static AjaxResponse success(){
        AjaxResponse response = new AjaxResponse();
        response.setIsok(true);
        response.setCode(200);
        response.setMessage("success");
        return response;
    }
}
