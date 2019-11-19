package cn.im.tool;

import org.junit.Test;

import com.alibaba.fastjson.JSON;

/**
 * 实现java bean、json对象、json字符串的相互转化
 * 
 * @author Kevin
 *
 */
public class JsonTool {

	/**
	 * fastjson 使用说明：
	 * <!-- 添加 fastjson 依赖 -->
		<dependency>
			<groupId>com.alibaba</groupId>
			<artifactId>fastjson</artifactId>
			<version>1.2.61</version>
		</dependency>
		
	 * 主要对象 JSON、JSONArray、JSONObject
	 *调用JSON对象中的转化方法
	 */
	
	@Test
	public void fastJsonTest() {
		String obj = "{\"id\":1001,\"age\":12}";
		Person p  = new Person();
		p.setAge(12);
		p.setId(1001);
		p.setName("kevin");
		
		// 对象转化为json字符串
		String jsonObj = JSON.toJSONString(p); 
//		System.out.println(jsonObj);
		
		p = JSON.parseObject(obj, Person.class);
		System.out.println(p);
		System.out.println(p.toString());
	}
	
	
	public static class Person{
		private int id;
		private int age;
		private String name;
		public Person() {};
		public Person(int id, int age, String name) {
			super();
			this.id = id;
			this.age = age;
			this.name = name;
		}
		
		@Override
		public String toString() {
			return "Person [id=" + id + ", age=" + age + ", name=" + name + "]";
		}
		public int getId() {
			return id;
		}
		public void setId(int id) {
			this.id = id;
		}
		public int getAge() {
			return age;
		}
		public void setAge(int age) {
			this.age = age;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		
	}
}


