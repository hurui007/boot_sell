package com.hurui.sell.exception;

import com.hurui.sell.common.enums.ErrorEnum;
import org.springframework.util.StringUtils;

/**
 * @title 自定义异常
 * @author hurui
 */
public class SellException extends RuntimeException{

    private Integer code;

    public SellException(ErrorEnum resultEnum){
        super(resultEnum.getMessage());
        this.code = resultEnum.getCode();
    }

    public SellException(ErrorEnum resultEnum, String message){
        super(StringUtils.isEmpty(message)?resultEnum.getMessage():message);
        this.code = resultEnum.getCode();
    }
}
