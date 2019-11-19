package cn.im.util;

import java.beans.FeatureDescriptor;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Stream;

import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

/**
 * 通用工具类
 * @author Administrator
 *
 */
public class Util {
	
	/**
	 * 获取对象的空值属性
	 * @param source
	 * @return
	 */
	 public static String[] getNullPropertyNames(Object source) {
	        final BeanWrapper wrappedSource = new BeanWrapperImpl(source);
	        return Stream.of(wrappedSource.getPropertyDescriptors())
	                .map(FeatureDescriptor::getName)
	                .filter(propertyName -> wrappedSource.getPropertyValue(propertyName) == null)
	                .toArray(String[]::new);
	    }
	 
	 //转换符对应：  %s 字符串；%d 整数类型  ； %c 字符
	 private static String resopnseStr = "{\"code\":%d,\"msg\":\"%s\"}"; // 格式化输出接口    
	 
	 public static String getResponseStr(int code,String msg) {
		 return String.format(resopnseStr, code,msg);
	 }
	 
	public static void main(String[] args) {
		System.out.println(getResponseStr(200, "success"));
	}
	 
}
