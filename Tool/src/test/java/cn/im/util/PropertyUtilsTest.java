package cn.im.util;

import org.junit.Assert;
import org.junit.Test;

public class PropertyUtilsTest {

	@Test
	public void testReadProperty() {
		String actual = PropertyUtils.readProperty("conf", "IP");
		Assert.assertEquals("null", actual);
		String username = PropertyUtils.readProperty("conf", "USER_NAME");
		Assert.assertEquals("David", username);
	}

}
