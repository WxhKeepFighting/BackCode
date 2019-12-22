package com.wxh.musicsystem.exception;

//常规错误
public class CustomException extends RuntimeException {
    private int code;
    private String message;

    private CustomException(){}//不让其他类调用它的时候用一个空的构造方法来new它

    public CustomException(CustomExceptionType exceptionType, String message){
        this.code = exceptionType.getCode();
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
