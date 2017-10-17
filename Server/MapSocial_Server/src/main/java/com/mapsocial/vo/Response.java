package com.mapsocial.vo;

/**
 * Created by yue.gan on 2017/10/16.
 */
public class Response {

    private boolean success;    //处理是否成功
    private String message;      //处理后的消息
    private Object body;         //返回数据

    public Response() {
    }

    public Response(boolean success, String message, Object body) {
        this.success = success;
        this.message = message;
        this.body = body;
    }

    @Override
    public String toString() {
        return "Response{" +
                "success=" + success +
                ", message='" + message + '\'' +
                ", body=" + body +
                '}';
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getBody() {
        return body;
    }

    public void setBody(Object body) {
        this.body = body;
    }
}
