package cn.im.tool;

import cn.im.util.JacksonUtil;

/**
 * 返回报文拼接处理
 * @author Administrator
 *
 */
public class ResultHelper {

	/**
	 * 返回错误码报文
	 * @param bean 错误码枚举类
	 * @return json字符串
	 */
	public static String errCode(ErrCodeEnum bean){
		return "{\"errcode\":"+bean.getCode()+",\"errmsg\":\""+bean.getMsg()+"\"}";
	}
	
	/**
	 * 返回拼接报文
	 * @param obj
	 * @return json字符串
	 */
	public static String model(Object obj){
		return JacksonUtil.toJson(obj);
		
	}

	
}


