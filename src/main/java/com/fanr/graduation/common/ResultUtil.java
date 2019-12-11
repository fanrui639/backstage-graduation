package com.fanr.graduation.common;

/**
 * @author: fanr639
 * @create: 2019-11-27 15:28:55
 * @description:
 */
public class ResultUtil {

    public static Result success() {
        Result result = new Result();
        result.setCode(0);
        result.setSuccess(true);
        result.setMsg("成功");
        return result;
    }

    public static Result success(String msg) {
        Result result = new Result();
        result.setCode(0);
        result.setSuccess(true);
        result.setMsg(msg);
        return result;
    }

    public static Result success(Object object) {
        Result result = new Result();
        result.setCode(0);
        result.setSuccess(true);
        result.setMsg("成功");
        result.setData(object);
        return result;
    }

    public static Result success(long total, Object object) {
        Result result = new Result();
        result.setTotal(total);
        result.setCount(total);
        result.setCode(0);
        result.setSuccess(true);
        result.setMsg("成功");
        result.setData(object);
        return result;
    }

    public static Result success(long total, Object object, Object rows) {
        Result result = new Result();
        result.setTotal(total);
        result.setCount(total);
        result.setCode(0);
        result.setSuccess(true);
        result.setMsg("成功");
        result.setData(object);
        result.setRows(rows);
        return result;
    }

    public static Result error(Integer code, String msg) {
        Result result = new Result();
        result.setCode(code);
        result.setSuccess(false);
        result.setMsg(msg);
        return result;
    }
}