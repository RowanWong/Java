package cn.im.util.encrypt;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.lang3.StringUtils;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

@SuppressWarnings("restriction")
public class SecurityUtil {
	public static String DES = "DES"; // optional value AES/DES/DESede
	public static String CIPHER_ALGORITHM = "DES/ECB/PKCS5Padding"; // optional value AES/DES/DESede

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
	
	public static void main(String[] args)throws Exception{
		String key="im&cloud";//秘钥 需要八位
		String appid="appid-10000-4401";
		String appkey="appkey-4401";
		System.out.println("appid = "+appid);
		String eappid = encrypt(appid,key);
		String eappkey = encrypt(appkey,key);
		System.out.println("encrypted eappid is below :");
		System.out.println("appid:"+eappid);
		System.out.println("appkey:"+eappkey);
		String dappid = detrypt(eappid,key);
		
		System.out.println("decrypted message is below :");
		System.out.println(dappid);
	}
}
