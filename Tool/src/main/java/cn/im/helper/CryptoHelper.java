package cn.im.helper;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import cn.im.util.encrypt.ASEUtil;
import cn.im.util.encrypt.Base64Ext;
import cn.im.util.encrypt.DESUtil;
import cn.im.util.encrypt.Decode;

/**
 * 提供常见的加密方法
 */
public class CryptoHelper {


    /**
     * BASE64解密
     *
     * @param key
     * @return
     * @throws Exception
     */

    public static byte[] decryptBASE64(String key) throws Exception {
        return Base64Ext.decode(key.getBytes(), Base64Ext.NO_WRAP);
    }

    /**
     * BASE64加密
     *
     * @param key
     * @return
     * @throws Exception
     */
    public static String encryptBASE64(byte[] key) throws Exception {
        return new String(Base64Ext.encode(key, Base64Ext.NO_WRAP));
    }


    /**
     * 字符串加密函数MD5实现
     */
    public final static String md5(String password) {
    	byte[] secretBytes = null;
		try {
			secretBytes = MessageDigest.getInstance("md5").digest(password.getBytes());
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException("没有这个md5算法！");
		}
		String md5code = new BigInteger(1, secretBytes).toString(16);
		for (int i = 0; i < 32 - md5code.length(); i++) {
			md5code = "0" + md5code;
		}
		return md5code;
    }
    
    /**
     * 字符串加密函数MD5实现
     */
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

    /**
     * 提供ASE加密算法
     *
     * @param secretKey 秘钥
     * @param str       加密的字符串
     * @return string
     */
    public static String aesEncrypt(String secretKey, String str) {
        return Decode.str2HexStr(ASEUtil.AESEncode(secretKey, str));
    }

    /**
     * 提供ASE解密算法
     *
     * @param secretKey 秘钥
     * @param str       解密的字符串
     * @return decode str or null
     */
    public static String aseDecode(String secretKey, String str) {
        return ASEUtil.AESDncode(secretKey, Decode.hexStr2Str(str));
    }


    /**
     * 提供des加密算法
     *
     * @param secretKey 秘钥
     * @param str       加密的字符串
     * @return string
     */
    public static String desEncrypt(String secretKey, String str) {
        try {
            return DESUtil.encrypt( str,secretKey);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 提供des解密算法
     *
     * @param secretKey 秘钥
     * @param str       解密的字符串
     * @return decode str or null
     */
    public static String dseDecode(String secretKey, String str) {
        try {
            return DESUtil.detrypt(str,secretKey);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
