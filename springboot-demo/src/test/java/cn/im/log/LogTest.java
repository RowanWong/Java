package cn.im.log;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LogTest {
	Logger logger = LoggerFactory.getLogger(LogTest.class);
	
	@Test
	public void defaultLog() {
		//日志级别从低到高分为TRACE < DEBUG < INFO < WARN < ERROR < FATAL，如果设置为WARN，则低于WARN的信息都不会输出。
		logger.trace("this is trace level.");
		logger.debug("this is debug level.");
		logger.info("this is info level.");
		logger.warn("this is warn level.");
		logger.error("this is error level");
	}
}
