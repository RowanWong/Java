package cn.im.tool;

/**
 * 系统级别的工具方法
 * @author SYSTEM
 *
 */
public class SystemTool {

	/**
	 * 设置代理
	 * @param ip
	 * @param port
	 */
	public static final void proxySet(String ip,String port){
//		System.setProperty("http.proxyHost", "10.121.2.5");  
//		System.setProperty("http.proxyPort", "3128");
		System.setProperty("http.proxyHost", ip);  
		System.setProperty("http.proxyPort", port);
	}
}
