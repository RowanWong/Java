package cn.im.tool;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

import org.apache.commons.lang3.StringUtils;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

/**
 * @Description: 字符串工具类
 * @author p.x.pang
 * @date 2013-4-22 上午11:13:54
 * @version V1.0
 */
public class StringTool {

	/**
	 * 去除字符串中的空格、回车、换行符、制表符
	 * 
	 * @param str
	 * @return
	 */
	public static String replaceBlank(String str) {
        String dest = "";
        if (str!=null) {
            Pattern p = Pattern.compile("\\s*|\t|\r|\n");
            Matcher m = p.matcher(str);
            dest = m.replaceAll("");
        }
        return dest;
    }
	
	/**
	 * 数据转换 (String 转换成 int)
	 */
	public static int String2Int(String str) {

		int reInt = 0;

		if (StringUtils.isNotBlank(str)) {
			if (str.matches("[0-9]+")) {
				try {
					reInt = Integer.valueOf(str);
				} catch (Exception e) {
				}

			}
		}

		return reInt;
	}

	/**
	 * 过滤特殊字符
	 * 
	 * @param str
	 * @return
	 * @throws PatternSyntaxException
	 */
	public static String StringFilter(String str) {
		if (StringUtils.isNotBlank(str)) {
			// 清除掉所有特殊字符
			String regEx = "[`~!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]";
			Pattern p = Pattern.compile(regEx);
			Matcher m = p.matcher(str);
			str = m.replaceAll("").trim();
		}

		return str;
	}

	/**
	 * 隐藏手机号
	 * 
	 * @return
	 */
	public static String nonePhoneNo(String phoneNo) {

		if (StringUtils.isNotBlank(phoneNo) && phoneNo.length() == 11) {
			char[] pChar = phoneNo.toCharArray();
			phoneNo = "";
			for (int index = 0; index < pChar.length; index++) {
				if (index >= 3 && index <= 6) {
					phoneNo += "*";
				} else {
					phoneNo += pChar[index];
				}
			}
		}

		return phoneNo;
	}

	/*
	 * 将字符串编码成16进制数字,适用于所有字符（包括中文）
	 */
	public static String hex2String(String str) {
		String hexString = "0123456789ABCDEF";

		// 根据默认编码获取字节数组
		byte[] bytes = str.getBytes();
		StringBuilder sb = new StringBuilder(bytes.length * 2);
		// 将字节数组中每个字节拆解成2位16进制整数
		for (int i = 0; i < bytes.length; i++) {
			sb.append(hexString.charAt((bytes[i] & 0xf0) >> 4));
			sb.append(hexString.charAt((bytes[i] & 0x0f) >> 0));
		}
		return sb.toString();
	}

	/*
	 * 将16进制数字解码成字符串,适用于所有字符（包括中文）
	 */
	public static String String2Hex(String bytes) {
		String hexString = "0123456789ABCDEF";

		ByteArrayOutputStream baos = new ByteArrayOutputStream(
				bytes.length() / 2);
		// 将每2位16进制整数组装成一个字节
		for (int i = 0; i < bytes.length(); i += 2)
			baos.write((hexString.indexOf(bytes.charAt(i)) << 4 | hexString
					.indexOf(bytes.charAt(i + 1))));
		return new String(baos.toByteArray());
	}

	/**
	 * 16进制字符转字符串(把阴文转为明文方法)
	 * 
	 * @param str
	 * @return
	 */
	public static String hexToString(String str) {
		String value = "";
		if (str != null && !"".equals(str)) {
			value = new String(stringToByte(str));
		}
		return value;
	}

	/**
	 * 字符转16进制字符(明文转阴文)
	 * 
	 * @param str
	 * @return
	 */
	public static String stringToHex(String str) {
		String value = "";
		if (str != null && !"".equals(str)) {
			value = byteToString(str.getBytes());
		}
		return value;
	}

	/**
	 * 把byte数组变换为16進数的字符串。
	 * 
	 * @param bytes
	 *            byte数组
	 * @return 16進数的字符串
	 */
	public static String byteToString(byte[] bytes) {

		StringBuffer buf = new StringBuffer();

		for (int i = 0; i < bytes.length; i++) {
			int d = bytes[i];
			if (d < 0) {
				d += 256;
			}
			if (d < 16) {
				buf.append("0");
			}
			buf.append(Integer.toString(d, 16));
		}
		return buf.toString();
	}

	/**
	 * 把16進数的字符串变换为byte数组。
	 * 
	 * @param string
	 *            16進数的字符串
	 * @return byte 数组
	 */
	public static byte[] stringToByte(String string) {

		byte[] bytes = new byte[string.length() / 2];
		String b;

		for (int i = 0; i < string.length() / 2; i++) {
			b = string.substring(i * 2, i * 2 + 2);
			bytes[i] = (byte) Integer.parseInt(b, 16);
		}
		return bytes;
	}

	// byte类型转为16进制字符串
	public static String bytesToHexString(byte[] src) {
		StringBuilder stringBuilder = new StringBuilder("");
		if (src == null || src.length <= 0) {
			return null;
		}
		for (int i = 0; i < src.length; i++) {
			int v = src[i] & 0xFF;
			String hv = Integer.toHexString(v);
			if (hv.length() < 2) {
				stringBuilder.append(0);
			}
			stringBuilder.append(hv);
		}
		return stringBuilder.toString();
	}

	/**
	 * 简单的字符串加密解密 Decryption
	 */
	public static String simpleEncry(String s) {
		String src = "abcdefghijklmnopqrstuvwxyz_-;012345+6789ABCDEFGHIJKLMNOPQRSTUVWXYZ@.=";
		String dest ="v-Wkn8UFzf@IJ6KHw;PM34VrX5NiljOx=dS bc1Y2up7CtaqD.EG_e0LsQmABygRTohZ9";

		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < s.length(); i++) {
			sb.append(dest.charAt(src.indexOf(s.charAt(i))));
		}
		return sb.toString();
	}

	public static String simpleDecry(String s) {
		String src = "v-Wkn8UFzf@IJ6KHw;PM34VrX5NiljOx=dS bc1Y2up7CtaqD.EG_e0LsQmABygRTohZ9";
		String dest ="abcdefghijklmnopqrstuvwxyz_-;012345+6789ABCDEFGHIJKLMNOPQRSTUVWXYZ@.=";

		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < s.length(); i++) {
			sb.append(dest.charAt(src.indexOf(s.charAt(i))));
		}
		return sb.toString();

	}
	
	/**
	 * 加密处理 对每个参数 base64 加密  
	 * @param param
	 * @return
	 */
	public static String paramsEncry(String param){
		if(StringUtils.isBlank(param)){
			return "";
		}
		String str = "";
		try {
			str = new BASE64Encoder().encode(param.getBytes());
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("参数加密错误！！paramsEncry--->"+param);
		}
		
		return str;
	}

	/**
	 * 是否是微信自带浏览器打开
	 * 
	 * @param User_Agent
	 *            用户http请求头
	 * @return
	 */
	public static boolean isWeiXinOpen(String User_Agent) {

		if (StringUtils.isNotBlank(User_Agent)) {
			User_Agent = User_Agent.toUpperCase();

			if (User_Agent.indexOf("MICROMESSENGER") != -1
					|| User_Agent.indexOf("WINDOWS PHONE") != -1) {
				return true;
			}
		}

		return false;
	}


	/**
	 * base64编码
	 * @param s1
	 * @return
	 */
	public static String base64Encry(String s1){
		String str = new BASE64Encoder().encode(s1.getBytes());
		return replaceBlank(str);
		 
	}
	
	/**
	 * base64 解码
	 * @param s1
	 * @return
	 */
	public static String base64Decry(String s1){
		try {
			String str = new String(  new BASE64Decoder().decodeBuffer(s1));
			return replaceBlank(str);
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

}
