package cn.im.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.ResourceBundle;

public class PropertyUtils {
	
    /**
     * 从系统属性文件中获取相应的值
     *
     * @param key key
     * @return 返回value
     */
    public final static String key(String key) {
        return System.getProperty(key);
    }
	
	/***
	 * 通过ResourceBundle读取配置文件中指定key的值
	 * @param name_path 配置文件的 文件名和路径
	 * @param key 配置文件中的值 key不存在，则返回“null”
	 */
	public final static String readProperty(String name_path,String key){
		ResourceBundle resources = ResourceBundle.getBundle(name_path);
		if(resources.containsKey(key)){			
			return resources.getString(key);
		}else{
			return "null";
		}
	}
	
	/**
	 * @Description: 通过Properties对象读取配置文件中指定key值
	 * @param namePath 配置文件的相对路径+文件名（无后缀）
	 * @param key 配置文件中的值
	 */
	public final static String readPropertyByKey(String namePath,String key){
		String target = null;
		Properties pro = new Properties();
		try {
			String path = Thread.currentThread().getContextClassLoader().getResource("").getPath()+namePath;
			pro.load(new FileInputStream(new File(path)));
			target = (String) pro.get(key);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return target;
	}
	
	/**
	 * @Description: 读取配置文件至map中
	 * @param pathName 文件路径
	 */
	public final static Map ReadAllProperties(String pathName) {
		Map<String, String> pro = null;
		Properties properties = new Properties();

		try {
			pathName = Thread.currentThread().getContextClassLoader().getResource("").getPath()+pathName;
			FileInputStream in = new FileInputStream(pathName);
			properties.load(in);
			pro = new HashMap<String, String>((Map) properties);
			in.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return pro;
	}
	
	/**
	 * @Description: 消息追加写入配置文件中
	 * @param pathName 配置文件路径地址
	 * @param result 待保存的键值对
	 * @param note  对保存内容的注释
	 */
	public final static void writeToProperties(String pathName, Map<String, String> result,String note) {
		Properties properties = new Properties();
		try {
			pathName = Thread.currentThread().getContextClassLoader().getResource("").getPath()+pathName;
			FileOutputStream out = new FileOutputStream(pathName,true);
			for (Entry<String, String> obj : result.entrySet()) {
				properties.put(obj.getKey(), obj.getValue());
			}
			properties.store(out, "你好");
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		/*System.out.println(PropertyUtils.readProperty("conf/SystemParams", "secretid"));
		System.out.println(PropertyUtils.readPropertyByKey("conf/SystemParams.properties", "secretid"));
		Map<String,String> paramMap =  PropertyUtils.ReadAllProperties("/conf/SystemParams.properties");
		System.getProperty("user.dir");//java项目中获取的项目路径；web项目中获取的是tomcat\bin路径
		Thread.currentThread().getContextClassLoader().getResource("").getPath(); //获取classes路径地址
*/		
		System.out.println(PropertyUtils.readProperty("SystemParams", "secretid"));
		System.out.println(PropertyUtils.readProperty("SystemParams", "USER_NAME"));
	}
}
