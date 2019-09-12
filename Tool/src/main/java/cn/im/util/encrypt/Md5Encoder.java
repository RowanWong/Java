package cn.im.util.encrypt;

import java.security.MessageDigest;

public class Md5Encoder
{

	 /**
	  * @Description: 获取密钥
	  * @param privateKey 私钥
	  * @param timestamp 时间戳
	  * @return  String 密钥
	  */
	 public final static String getUak(String privateKey,String timestamp){
		 return Md5Encoder.getMd5Msg(Md5Encoder.getMd5Msg(privateKey+timestamp+"*&Hgfr_|.e/+_)(*&-e856*R)")).substring(5, 23);
	 }
		
	  public final static String getMd5Msg(String s)
	  {
	    char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
	        'a', 'b', 'c', 'd', 'e', 'f' };
	    try
	    {
	      byte[] strTemp = s.getBytes();
	      MessageDigest mdTemp = MessageDigest.getInstance("MD5");
	      mdTemp.update(strTemp);
	      byte[] md = mdTemp.digest();
	      int j = md.length;
	      char str[] = new char[j * 2];
	      int k = 0;
	      for (int i = 0; i < j; i++)
	      {
	        byte byte0 = md[i];
	        str[k++] = hexDigits[byte0 >>> 4 & 0xf];
	        str[k++] = hexDigits[byte0 & 0xf];
	      }
	      return new String(str).toUpperCase();
	    }
	    catch (Exception e)
	    {
	      return null;
	    }
	  }
	  
}
