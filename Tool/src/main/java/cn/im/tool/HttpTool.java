package cn.im.tool;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.Proxy;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;

import cn.im.helper.CryptoHelper;
import net.sf.json.JSONObject;


/**
 * http请求工具类
 * @author David
 *
 */
public class HttpTool {

	/**
	 * 发送post请求
	 * @param urlString 请求地址
	 * @param param 请求参数
	 * @param code 编码格式
	 * @param timeout 超时时间（秒）
	 * @return
	 */
	public static String HttpConnectPost(String urlString, String param, String code, int timeout) {
		String result = null;
		URL url;
		HttpURLConnection httpConn;
		OutputStream out;
		InputStreamReader isr;
		BufferedReader br;
		String lineStr;
		
		try {
			url = new URL(urlString);
			httpConn = (HttpURLConnection) url.openConnection();

			timeout = timeout<=0 ? 4000:timeout*1000;
			

			//超时时间
			httpConn.setConnectTimeout(timeout);
			httpConn.setReadTimeout(timeout);

			httpConn.setRequestMethod("POST");
			httpConn.setDoOutput(true);
			httpConn.setDoInput(true);

			if (StringUtils.isBlank(code)) {
				code = "GB2312";
			}

			out = httpConn.getOutputStream();
			byte[] buf = param.getBytes(code);
			out.write(buf);

			isr = new InputStreamReader(httpConn.getInputStream(), code);

			br = new BufferedReader(isr);

			lineStr = "";
			result = "";
			while ((lineStr = br.readLine()) != null) {
				result += lineStr;
			}

			br.close();
			out.close();
			httpConn.disconnect();

		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			System.out.println( "调用超时" );
		} catch (ProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println( "调用超时" );
		}

		return result;
	}

	
	/**
	 * 发送get请求
	 * @param url 请求地址
	 * @param timeout 超时时间（秒） 
	 * @return
	 */
	public static String httpContentGet(String url, int timeout) throws IOException {
        // 拼凑get请求的URL字串，使用URLEncoder.encode对特殊和不可见字符进行编码
        URL getUrl = new URL(url);
        

//        Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress("10.121.2.5",3128));  
        
        // 根据拼凑的URL，打开连接，URL.openConnection函数会根据URL的类型，
        // 返回不同的URLConnection子类的对象，这里URL是一个http，因此实际返回的是HttpURLConnection
        HttpURLConnection connection = (HttpURLConnection) getUrl.openConnection();
        
        //超时时间
		timeout= timeout<=0?4000:timeout*1000;
		connection.setConnectTimeout(timeout);
		connection.setReadTimeout(timeout);
		
        // 进行连接，但是实际上get request要在下一句的connection.getInputStream()函数中才会真正发到
        // 服务器
        connection.connect();
        // 取得输入流，并使用Reader读取
        BufferedReader reader = new BufferedReader(new InputStreamReader(
                connection.getInputStream(),"utf-8"));
        String lines;
        String result = "";
        while ((lines = reader.readLine()) != null) {
        	result += lines;
        }
        reader.close();
        // 断开连接
        connection.disconnect();
        
        return result;
    }
	
	/**
	 * 使用代理发送get请求
	 * @param url 请求地址
	 * @param host 代理IP
	 * @param port 代理端口
	 * @param timeout 超时时间（秒） 
	 * @return
	 */
	public static String httpContentGet(String url,String host ,int port,int timeout) throws IOException {
        URL getUrl = new URL(url);
        
        Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress(host,port));  
		
        HttpURLConnection connection = (HttpURLConnection) getUrl.openConnection(proxy);
        
        //设置超时时间
        timeout= timeout<=0?4000:timeout*1000;
        connection.setConnectTimeout(timeout);
        connection.setReadTimeout(timeout);
        // 进行连接，但是实际上get request要在下一句的connection.getInputStream()函数中才会真正发到
        // 服务器
        connection.connect();
        // 取得输入流，并使用Reader读取
        BufferedReader reader = new BufferedReader(new InputStreamReader(
                connection.getInputStream(),"utf-8"));
        String lines;
        String result = "";
        while ((lines = reader.readLine()) != null) {
        	result += lines;
        }
        reader.close();
        // 断开连接
        connection.disconnect();
        
        return result;
    }
	
	
	public static void main(String[] args) {
		String appId="im";
		SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMddHHmmss");
		String time=sdf.format(new Date());
		String pwd=" d7yht2hT";
		pwd=appId+time+pwd;
		pwd=CryptoHelper.md5(pwd);
		JSONObject body=new JSONObject();
		body.put("appId", appId);
		body.put("sign", pwd);
		body.put("time", time);
		body.put("tableName", "IM_SM_COPY_SOURCE");
		JSONObject cols=new JSONObject();
		cols.put("RECORD_TIME", "2019-11-13 00:00:00");
		cols.put("SESSION_ID", "1");
		cols.put("LOGIN_ID", "tableName");
		cols.put("SRC_URL", "tableName");
		cols.put("KNLG_ID", "234");
		cols.put("COPY_CONTENT", "tableName");
		cols.put("IS_EDITED", "1");
		cols.put("SEND_CONTENT", "tableName");
		cols.put("LAST_USER_MSG", "tableName");
		cols.put("CENTER_ID", "123");
		cols.put("WND_TITLE", "tableName");
		cols.put("PROC_PATH", "tableName");
		cols.put("IS_CAPTURE", "1");
		body.put("cols", cols);
		String url="http://10.121.23.17:9090/imDataCenter/insert";
		String result = HttpTool.HttpConnectPost(url, body.toString(), "utf-8", 8);
		System.out.println(result);
	}
	

}