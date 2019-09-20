package cn.im.view;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import cn.im.tool.ErrCodeEnum;


/**
 * json格式返回结果包装类
 * 2018年8月27日 下午3:03:02
 */
public class JsonResult implements Serializable {

	private static final long serialVersionUID = 2059386534108147790L;
	
	private String errcode;		//错误编号，0表示成功
	
	private String  errmsg;		//错误详细信息
	
	private Map<String ,Object> resData ;//数据封装

	public JsonResult() {}

	public JsonResult(String code, String msg, Map<String, Object> resData) {
		super();
		this.errcode = code.indexOf("exception.")>-1?code.substring(10):code;
		this.errmsg = msg;
		this.resData = resData;
	}
	
	/**
	 * 直接返回{"errcode":"0","errmsg":"ok"}
	 * @return
	 */
	public static Object success(){
		return success(null);
	}
	
	/**
	 * 适配枚举类
	 * @param e
	 * @return
	 */
	public static Object errCode(ErrCodeEnum e) {
		return fail(String.valueOf(e.getCode()), e.getMsg());
	}
	
	/**
	 * 自定义成功返回：{"errcode":"0","errmsg":"ok","resData":{data}}
	 * @param data
	 * @return
	 */
	public static Object success(Map<String ,Object> data) {
		return new JsonResult("0","ok",data);
	}

	/**
	 * 失败返回{"errcode":"-1","errmsg":"request failed"}
	 * @return
	 */
	public static Object fail(){
		return fail("-1","request failed");
	}

	/**
	 * 自定义失败返回内容
	 * @param code
	 * @param err
	 * @return
	 */
	public static Object fail(String code, String err) {
		return new JsonResult(code,err,null);
	}
	
	/**
	 * 自定义失败返回内容
	 * @param code
	 * @param err
	 * @return
	 */
	public static Object fail(String code, String err,Map<String ,Object> data ) {
		return new JsonResult(code,err,data);
	}

	public Map<String, Object> getResData() {
		return resData;
	}

	public void setResData(Map<String, Object> resData) {
		this.resData = resData;
	}

	public String getErrcode() {
		return errcode;
	}

	public void setErrcode(String errcode) {
		this.errcode = errcode;
	}

	public String getErrmsg() {
		return errmsg;
	}

	public void setErrmsg(String errmsg) {
		this.errmsg = errmsg;
	}

}
