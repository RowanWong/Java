package cn.im.exception;

/**
 * 自定义异常
 * @author Administrator
 *
 */
public class BizException extends RuntimeException {
	public BizException(String msg){
		super(msg);
	}
}
