package cn.im.system;

/** 
 * @Description: 全局标识符 
 * @author p.x.pang
 * @date 2013-4-12 下午05:09:43 
 * @version V1.0   
 */
public interface Mark {


	String G_WRAP = "\n";//换行标识符

	String imageEnd = ".jpg";//图片后缀

	/*参数间隔符*/
	String MARK_PCSPLIT = "###";//间隔符 （省市、链接和跳转参数间隔符）
	String MARK_SPLIT_LINK = "----";//间隔符 （链接和跳转参数间隔符）
	String MARK_SPLIT_LINK_PARAMS = ";";//间隔符 （跳转参数间隔符）
	String MSLP = MARK_SPLIT_LINK_PARAMS;//简写

	/*返回结果标识符*/
	String MARK_RE_OK = "OK";//成功
	String MARK_RE_NODATA = "NODATA";//没有查到数据
	String MARK_RE_FAIL_ME = "FAIL_ME";//程序本身调用接口失败
	String MARK_RE_FAIL = "FAIL";//失败
	String MARK_RE_TIMEOUT = "TIMEOUT";//超时

	String MARK_PARAMS_RENGONG = "RG";//人工参数
	String MARK_PARAMS_XIAOI = "XI";//小I参数
	String MARK_PARAMS_OPERAT = "OP";//营业厅参数
	String MARK_PARAMS_BILL = "BL";//账单

	//图片处理占位符
	String MARK_IMAGE_TOKEN = "###";
	String MARK_IMAGE_TYPE = "***";
	String MARK_IMAGE_MEDIAID = "#*";

}
