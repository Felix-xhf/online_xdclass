package net.xdclass.online_xdclass.exception;

import net.xdclass.online_xdclass.utils.JsonData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/*
 * @description:异常处理类
 * @author: Felix_XHF
 * @create:2021-07-29 21:58
 */

@ControllerAdvice
public class CustomExceptionHandler {
    private final static Logger logger = LoggerFactory.getLogger(CustomExceptionHandler.class);


    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public JsonData handle(Exception e){
        logger.error("[ 系统异常 ]{}",e);
        if (e instanceof XDException){
            XDException xdException = (XDException) e;
            return JsonData.buildError(xdException.getCode(),xdException.getMsg());
        }else{
            return JsonData.buildError("全局异常，未知错误");
        }
    }
}
