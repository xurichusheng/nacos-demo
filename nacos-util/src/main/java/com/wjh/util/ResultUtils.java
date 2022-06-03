package com.wjh.util;

import com.wjh.ResultVO;
import com.wjh.enums.ResultCodeEnums;

/**
 * @author wenjianhai
 * @date 2022/5/29
 * @since JDK 1.8
 */
public class ResultUtils {

    public static <T> ResultVO<T> success(T data){
        ResultVO<T> resultVO = new ResultVO<>();
        resultVO.setCode(ResultCodeEnums.SUCCESS.getCode());
        resultVO.setData(data);
        resultVO.setMsg(ResultCodeEnums.SUCCESS.getMessage());
        return resultVO;
    }

    /**
     * 返回成功
     * @return
     */
    public static <T> ResultVO<T> success() {
        return success(null);
    }

    /**
     * 返回错误
     * @param code
     * @param message
     * @return
     */
    public static <T> ResultVO<T> error(Integer code , String message) {
        ResultVO<T> resultVO = new ResultVO<>();
        resultVO.setMsg(message);
        resultVO.setCode(code);
        return resultVO;
    }

    /**
     * 返回错误
     * @param resultCodeEnums
     * @param message
     * @return
     */
    public static <T> ResultVO<T> error(ResultCodeEnums resultCodeEnums , String message) {
        ResultVO<T> resultVO = new ResultVO<>();
        resultVO.setMsg(message);
        resultVO.setCode(resultCodeEnums.getCode());
        return resultVO;
    }

    /**
     * 返回错误
     * @param resultCodeEnums
     * @return
     */
    public static <T> ResultVO<T> error(ResultCodeEnums resultCodeEnums) {
        ResultVO<T> resultVO = new ResultVO<>();
        resultVO.setMsg(resultCodeEnums.getMessage());
        resultVO.setCode(resultCodeEnums.getCode());
        return resultVO;
    }

    /**
     * 返回自己定义
     * @param code
     * @param msg
     * @return
     */
    public static <T> ResultVO<T> retMsg(Integer code, String msg){
        ResultVO<T> resultVO = new ResultVO<>();
        resultVO.setMsg(msg);
        resultVO.setCode(code);
        return resultVO;
    }
}
