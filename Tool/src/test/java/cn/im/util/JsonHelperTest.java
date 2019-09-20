package cn.im.util;

import java.io.IOException;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;

import net.sf.json.JSONObject;

/**
 * json工具类示例
 * 主要包括  gson、jackson、fastjson、JSONObject
 * springboot 使用默认的jackson最好
 * @author Kevin
 *
 */
public class JsonHelperTest {
	
	
	public static String str = "{\"name\":\"kevin\",\"age\":22,\"birthDate\":\"Sep 12, 2019 12:41:59 PM\",\"cards\":[\"aa\",\"bb\",\"cc\"]}";
	public static User user = new User("kevin",22,new Date(),Arrays.asList("aa","bb","cc"));

	/**
	 *添加Gson依赖，对象与json串转化 
	 */
	@Test
	@Ignore
	public void gsonDemo() {
		Gson gson = new Gson();
		
		//对象转化为json串
		System.out.println(gson.toJson(user));  
		System.out.println(gson.fromJson(str, User.class));
	}
	
	
	/**
	 * 引入json-lib
	 */
	@Test
	@Ignore
	public void jsonDemo() {
		//解析json
		JSONObject obj = JSONObject.fromObject(str);
		System.out.println(obj.getJSONArray("cards"));
		System.out.println(obj.getString("name"));
		System.out.println(obj.getInt("birthDate"));
		
		//拼接json
		JSONObject json = new JSONObject();
		json.put("errcode", 0);
		json.put("errmsg", "ok");
		System.out.println(json.toString());
	}
	
	/**
	 * jackson-core
	 * @throws IOException 
	 */
	@Test
	public void jacksonDemo() throws IOException {
		//解析json
		ObjectMapper mapper = new ObjectMapper();
		JsonNode rootNode = mapper.readTree(str);
		System.out.println(rootNode.get("name").asText());
		System.out.println(rootNode.get("age").asInt());
		System.out.println(rootNode.get("birthDate"));
		System.out.println(rootNode.get("cards").size());
		if(rootNode.get("cards").size() != 0) {
			JsonNode cardNode = rootNode.get("cards");
			System.out.println(cardNode.get(0).asText());
			System.out.println(cardNode.get(1).asText());
			System.out.println(cardNode.get(2).asText());
		}
		
		//解析json串至对象    此处注意date格式
		ObjectMapper mapper1 = new ObjectMapper();
		mapper1.disable(DeserializationFeature.UNWRAP_ROOT_VALUE);
		User u = mapper1.readValue(str, User.class);
		System.out.println("u:"+u.toString());
		
	}

}
/**
 * {"name":"kevin","age":12,"birthDate":"Sep 12, 2019 12:36:25 PM","cards":["aa","vv"]}
 * @author Kevin
 *
 */
class User{
	private String name;
	private int age;
	private Date birthDate;
	private List<String> cards;
	public User() {}
	public User(String name, int age, Date birthDate, List<String> cards) {
		super();
		this.name = name;
		this.age = age;
		this.birthDate = birthDate;
		this.cards = cards;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public Date getBirthDate() {
		return birthDate;
	}
	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}
	public List<String> getCards() {
		return cards;
	}
	public void setCards(List<String> cards) {
		this.cards = cards;
	}
	@Override
	public String toString() {
		return "User [name=" + name + ", age=" + age + ", birthDate=" + birthDate + ", cards=" + cards + "]";
	}
}
