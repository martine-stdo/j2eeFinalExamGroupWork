package com.iss.edu.online.common;


import lombok.Data;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: 14776
 * Date: 2023-05-24
 * Time: 20:39
 */
/*
 * 定义一个格式返回的模板类
 * 统一数据格式返回
 * */
@Data
public class ResultResponse implements Serializable {
    //支持序列化接口
    //返回的状态码
    private Integer code;
    //返回的描述信息
    private String msg;
    //返回的数据本体
    private Object data;

    //定义成功返回的方法
    public static ResultResponse success(Object data){
        //请求成功之后 调用这个方法就可以统一格式返回
        //但是呢有的时候希望code msg可以自定义 所以可以用方法重载
        ResultResponse resultResponse = new ResultResponse();
        resultResponse.setCode(200);
        resultResponse.setMsg("");
        resultResponse.setData(data);
        return resultResponse;
    }

    public static ResultResponse success(Integer code, Object data){
        ResultResponse resultResponse = new ResultResponse();
        resultResponse.setCode(code);
        resultResponse.setMsg("");
        resultResponse.setData(data);
        return resultResponse;
    }

    public static ResultResponse success(Integer code, String msg, Object data){
        ResultResponse resultResponse = new ResultResponse();
        resultResponse.setCode(code);
        resultResponse.setMsg(msg);
        resultResponse.setData(data);
        return resultResponse;
    }


    //定义请求失败的返回数据的方法
    public static ResultResponse fail(Integer code, String msg){
        ResultResponse resultResponse = new ResultResponse();
        resultResponse.setCode(code);
        resultResponse.setMsg(msg);
        resultResponse.setData(null);//失败则没有数据返回
        return resultResponse;
    }

    public static ResultResponse fail(Integer code, String msg, Object data){
        ResultResponse resultResponse = new ResultResponse();
        resultResponse.setCode(code);
        resultResponse.setMsg(msg);
        resultResponse.setData(data);
        return resultResponse;
    }

}
