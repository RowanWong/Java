package cn.im.helper;

import static org.junit.Assert.*;

import org.junit.Test;

import cn.im.util.encrypt.SecurityUtil;

public class CryptoHelperTest {

	@Test
	public void test() {
		String secretKey = "im&cloud";
		String str = "appkey-4401";
		try {
			System.out.println(SecurityUtil.encrypt(str, secretKey));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(CryptoHelper.desEncrypt(secretKey, str));
	}

}
