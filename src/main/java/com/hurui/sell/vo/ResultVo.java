package com.hurui.sell.vo;

import com.hurui.sell.common.enums.ResultStatusEnum;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @title http清秀返回的最外层对象
 */
@Data
public class ResultVo<T> implements Serializable {
    /**
     * 错误码
     */
    private Integer code;
    /**
     * 提示信息
     */
    private String msg;
    /**
     * 具体内容
     */
    private T data;

    public static ResultVo OK(Object obj){
        ResultVo vo = new ResultVo();
        vo.setCode(ResultStatusEnum.SUCCESS.getCode());
        vo.setMsg(ResultStatusEnum.SUCCESS.getName());
        vo.setData(obj);
        return vo;
    }

    public static ResultVo Error(){
        ResultVo vo = new ResultVo();
        vo.setCode(ResultStatusEnum.FAILED.getCode());
        vo.setMsg(ResultStatusEnum.FAILED.getName());
        return vo;
    }

}
