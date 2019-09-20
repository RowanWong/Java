package cn.im.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import cn.im.domain.User;
import cn.im.view.JsonResult;

public class JacksonUtilTest {

	@Test
	public void test() {
		Map<String,Object> map = new HashMap<String,Object>();
		User user = new User();
		user.setAge(12);
		user.setId("SSS");
		user.setUserName("");
		map.put("A1", user);
		map.put("", new ArrayList<String>());
		System.out.println(JacksonUtil.toJson(JsonResult.success()));
		System.out.println(JacksonUtil.toJson(JsonResult.fail()));
		System.out.println(JacksonUtil.toJson(JsonResult.fail("40001", "token error.")));
		System.out.println(JacksonUtil.toJson(JsonResult.success(map)));
		System.out.println(JacksonUtil.toJson(JsonResult.fail("-1", "error", map)));
	}

}
