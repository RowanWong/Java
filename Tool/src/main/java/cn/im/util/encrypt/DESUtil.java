package cn.im.util.encrypt;

import java.security.Key;

import javax.crypto.Cipher;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.lang3.StringUtils;

import cn.im.bean.Opslab;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

/**
 * Created by 0opslab
 */
public class DESUtil {

	public static String DES = "DES"; // optional value AES/DES/DESede
	public static String CIPHER_ALGORITHM = "DES/ECB/PKCS5Padding"; // optional value AES/DES/DESede

	/**
	 * DES加密
	 * @param data
	 * @param key  需要8位长度
	 * @return
	 * @throws Exception
	 */
	public static String encrypt(String data,String key) throws Exception {
		String strs =null;
		if(StringUtils.isNotBlank(data)){
			SecretKeySpec securekey = new SecretKeySpec(key.getBytes("UTF-8"), "DES");
			Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
			cipher.init(Cipher.ENCRYPT_MODE, securekey);
			byte[] bt = cipher.doFinal(data.getBytes());
			strs = new BASE64Encoder().encode(bt);
			strs = strs.replaceAll("[+]","-");
			strs = strs.replaceAll("/","_");
			strs = strs.replaceAll("==","zSrh");//特殊处理
		}
        return strs;
    }
    
	/**
	 * DES解密
	 * @param message
	 * @param key 需要8位长度
	 * @return
	 * @throws Exception
	 */
	public static String detrypt(String message,String key) throws Exception{
		if(StringUtils.isNotBlank(message)){
			message = message .replaceAll("-","+");
			message = message .replaceAll("_","/");
			message = message .replaceAll("zSrh","==");
			SecretKeySpec securekey = new SecretKeySpec(key.getBytes("UTF-8"), "DES");
			Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
			cipher.init(Cipher.DECRYPT_MODE, securekey);
			byte[] res = new BASE64Decoder().decodeBuffer(message);
			res = cipher.doFinal(res);
			return new String(res);
		}
		return null;
	}
}
