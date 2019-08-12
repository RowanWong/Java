package cn.im.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.im.tool.ErrCodeEnum;
import cn.im.tool.ResultHelper;

/**
 * @ControllerAdvice结合@ExceptionHandler 全局异常的处理
 * 捕获异常的类，返回信息给请求方，可以自定义返回的code,msg等信息
 */
@ControllerAdvice 
public class GlobalExceptionHandler {

    private final static Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);
    
    /**
     * 处理所有异常
     * @return
     */
    @ExceptionHandler(Exception.class) //注解声明异常处理方法
    @ResponseBody
    String handleException(Exception e){
    	logger.error(e.getMessage(),e);
    	
    	if (e instanceof HttpMessageNotReadableException) {   //解析报文异常
            return  ResultHelper.errCode(ErrCodeEnum.EXCEPTION_REQUESTMSG_41001);
        }
    	
    	return ResultHelper.errCode(ErrCodeEnum.EXCEPTION_UNKNOW);
    }
    
    
}