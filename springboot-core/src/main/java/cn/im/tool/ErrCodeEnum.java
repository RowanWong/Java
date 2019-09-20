package cn.im.tool;

public enum ErrCodeEnum {


	/**
	 * 请求成功
	 */
	SUCCESS_200(0, "请求成功"),
	/**
	 * 对象不存在
	 */
	NOT_FOUND_40001(40001, "ok"),
	/**
	 * 对象已存在
	 */
	ALREAD_EXIST_40002(40002, "对象已存在"),
	/**
	 * 请求参数错误
	 */
	PARAM_ERROR_40003(40003, "请求参数错误"),


	/**
	 * 未知异常
	 */
	EXCEPTION_UNKNOW(41000, "unknow exception"),
	
	/**
	 * 请求报文异常
	 */
	EXCEPTION_REQUESTMSG_41001(41001, "请求报文异常"),

	/**
	 * 自定义未知异常
	 */
	EXCEPTION_BIZ(-1, "biz exception")
	
	;
	
	private int code;
	private String msg;

	public int getCode() {
			return code;
		}

	public String getMsg() {
		return msg;
	}

	ErrCodeEnum(int code, String msg) {
		this.code = code;
		this.msg = msg;
	}
}