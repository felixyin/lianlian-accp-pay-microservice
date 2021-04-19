package com.qdqtrj.lianlian.accp.pojo;

/**
 * @author yinbin
 * @version 1.0
 * @date 2021/4/15 8:32 上午
 */
public class ResponseEntity<T> {
    private boolean success = true;
    private String msg = "操作成功";
    private T data = null;

    public ResponseEntity() {
    }

    public ResponseEntity(T data) {
        this.data = data;
    }

    public ResponseEntity(boolean success) {
        this.success = success;
        this.msg = "操作失败!";
    }

    public ResponseEntity(boolean success, String msg) {
        this.success = success;
        this.msg = msg;
    }

    public String getMsg() {
        return this.msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return this.data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public boolean isSuccess() {
        return this.success;
    }

    public void setSuccess(boolean success, String msg) {
        this.success = success;
        this.msg = msg;
    }

    public boolean getSuccess() {
        return this.success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
        if (!success) {
            this.msg = "操作失败!";
        }
    }

}
