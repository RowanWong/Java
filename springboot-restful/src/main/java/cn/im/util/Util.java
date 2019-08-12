package cn.im.util;

import java.beans.FeatureDescriptor;
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
	 
}
