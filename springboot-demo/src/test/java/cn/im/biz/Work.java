package cn.im.biz;

import java.io.File;
import java.io.FileInputStream;
import java.util.UUID;

import org.junit.Test;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import cn.im.util.ByteFormat;
import cn.im.util.encrypt.HmacSha1Util;
import cn.im.util.encrypt.XXTeaUtil;

public class Work {
	
	private static final String appKey = "93b980dc4bea4c8fb4615855f1c5a8ba";
	private static final String appSecret = "47072c3814184e0f910a24554547f7ec";
	
	/**
	 * 	H5页面嵌入型接入链接拼接
	 * @throws Exception
	 */
	@Test
	public void idLivenessTest() throws Exception {
		String appKey = "93b980dc4bea4c8fb4615855f1c5a8ba";
		String version = "v1.0";
		String format = "redirect";
		String authType = "2";
		String timeStamp = String.valueOf(System.currentTimeMillis());
		String requestId = UUID.randomUUID().toString();
		String callbackURL = "https://im.189.cn/faceDetect/hello";
		String paras = XXTeaUtil.encrypt((authType+callbackURL+requestId+timeStamp), "UTF-8", ByteFormat.toHex(appSecret.getBytes()));
		String sign = HmacSha1Util.getSignature(appKey+format+version+paras,ByteFormat.toHex(appSecret.getBytes())).toUpperCase();

		String url = "https://api-s.21cn.com:9083/id/idLiveness.action"
				+"?appKey="+appKey
				+"&version="+version
				+"&format="+format
				+"&authType="+authType
				+"&timeStamp="+timeStamp
				+"&requestId="+requestId
				+"&callbackURL="+callbackURL
				+"&sign="+sign
				;
		System.out.println(url);
	}
	
	/**
	 *  1.4-身份证OCR
	 * @throws Exception
	 */
//	@Test
	public void doIdCardOCRTest() throws Exception {
		RestTemplate restTemplate = new RestTemplate();
		String url = "https://api-s.21cn.com:9083/id/doIdCardOCR.action";

		// header
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.MULTIPART_FORM_DATA);

		// body
		MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();

		body.add("appKey", "93b980dc4bea4c8fb4615855f1c5a8ba");
		body.add("accessToken", "549024.oHSvNPID766gbo25tNGQGseKFbHSNius.1450315687062042429.dF3xWf.331584540-708701");
		body.add("cardType", "0");
		body.add("requestId", UUID.randomUUID().toString());

		File file = new File("D:\\2.jpg");
		byte[] bytesArray = new byte[(int) file.length()];
		FileInputStream fis = new FileInputStream(file);
		fis.read(bytesArray);
		fis.close();
		ByteArrayResource contentsAsResource = new ByteArrayResource(bytesArray) {
			@Override
			public String getFilename() {
				return "file";
			}
		};
		body.add("file", contentsAsResource);

		HttpEntity<MultiValueMap> entity = new HttpEntity<MultiValueMap>(body, headers);
		ResponseEntity<String> exchange = restTemplate.exchange(url, HttpMethod.POST, entity, String.class);
		System.out.println("status:" + exchange.getStatusCodeValue());
		System.out.println(exchange.getBody());
	}
	
	/**
	 * 	1.8-公安二要素
	 * @throws Exception
	 */
//	@Test
	public void postTest() throws Exception {
		RestTemplate restTemplate = new RestTemplate();
		String url = "https://api-s.21cn.com:9083/id/nameAndIdNumCheck.action";

		// header
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

		// body
		MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
		body.add("appKey", "93b980dc4bea4c8fb4615855f1c5a8ba");
		body.add("accessToken", "549024.oHSvNPID766gbo25tNGQGseKFbHSNius.1450315687062042429.dF3xWf.331584540-708701");
		body.add("idNumber", "371322199202143148");
		body.add("userName", "黄荣文");
		body.add("requestId", UUID.randomUUID().toString());

		//post请求
		HttpEntity<MultiValueMap> entity = new HttpEntity<MultiValueMap>(body, headers);
		ResponseEntity<String> exchange = restTemplate.exchange(url, HttpMethod.POST, entity, String.class);
		System.out.println("status:" + exchange.getStatusCodeValue());
		System.out.println(exchange.getBody());
	}
}
