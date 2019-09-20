package cn.im.util.encrypt;

import static org.junit.Assert.*;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import org.junit.Test;

import cn.im.util.BlowFish;

public class BlowFishTest {

	@Test
	public void test() throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, UnsupportedEncodingException {
		String key="qqRoomMobile";
		String mingwen = "Aa_123456";
		
		String jiami = BlowFish.encryption(mingwen, key); 
		System.out.println("--jiami=="+jiami);
		
		String jiemi = BlowFish.decryption(jiami, key);
		System.out.println("--jiemi=="+jiemi);
		
		String bytetostring = BlowFish.stringToHex(key);
		System.out.println(bytetostring);
	}

}
