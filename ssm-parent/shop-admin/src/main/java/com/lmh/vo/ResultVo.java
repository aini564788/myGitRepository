package com.lmh.vo;

public class ResultVo {

    private int code;
    private String msg;
    private Long count;
    private Object data;

    public static ResultVo success() {
        return new ResultVo(0, "success", null, null);
    }

    public static ResultVo success(String msg,Long count,Object data ) {
        return new ResultVo(0, msg, null, null);
    }

    public static ResultVo success(Long count,Object data){
        return new ResultVo(0,"success",count,data);
    }

    @Override
    public String toString() {
        return "ResultVo{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", count=" + count +
                ", data=" + data +
                '}';
    }

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

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public ResultVo() {
    }

    public static ResultVo error(){
        return new ResultVo(500,"error",null,null);
    }
    public static ResultVo error(String msg,Long count,Object data){
        return new ResultVo(500,msg,count,data);
    }
    public static ResultVo error(Long count,Object data){
        return new ResultVo(500,"error",count,data);
    }
    public static ResultVo error(int code){return new ResultVo(code,"error",null,null);}

    public ResultVo(int code, String msg, Long count, Object data) {
        this.code = code;
        this.msg = msg;
        this.count = count;
        this.data = data;
    }
}
