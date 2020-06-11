package cn.im.util;

import java.io.IOException;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;

public class JacksonUtil {
	private static ObjectMapper mapper = new ObjectMapper();

	static {
		// 将null值不序列化
		mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);

		// 将null值转换为空串
		mapper.getSerializerProvider().setNullValueSerializer(new JsonSerializer<Object>() {
			@Override
			public void serialize(Object value, JsonGenerator gen, SerializerProvider serializers)
					throws IOException, JsonProcessingException {
				gen.writeString("");
			}
		});
	}

	public static ObjectMapper getObjectMapper() {
		return mapper;
	}

	public static String toJson(Object obj) {
		StringWriter sw = new StringWriter();
		JsonGenerator gen = null;
		try {
			gen = new JsonFactory().createGenerator(sw);
			mapper.writeValue(gen, obj);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (gen != null) {
				try {
					gen.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		return sw.toString();
	}

	public static <T> T toBean(String jsonStr, Class<T> objClass)
			throws JsonParseException, JsonMappingException, IOException {
		return mapper.readValue(jsonStr, objClass);
	}

	public static void main(String[] args) throws IOException {
		String jsonStr = "{\"name\":\"kevin\",\"age\":22,\"birthDate\":\"2019-9-12 12:41:59\",\"cards\":[\"aa\",\"bb\",\"cc\"]}";

		// 直接解析json使用 readTree
		ObjectMapper mapper = new ObjectMapper();
		JsonNode rootNode = mapper.readTree(jsonStr);
		if (rootNode.has("name")) {
			System.out.println("jsonParse: name=" + rootNode.get("name").asText());
		}

		// json至Java对象
//		mapper.readValue(jsonStr, JacksonUtil.class);

		// Java对象至json
//		StringWriter sw = new StringWriter();
//		mapper.writeValue(new JsonFactory().createGenerator(sw), new Object());
//		System.out.println(sw.toString());

		//拼接json串
		Map<String, String> hashMap = new HashMap<String, String>();
		hashMap.put("name", "zhang");
		hashMap.put("sex", "1");
		hashMap.put("login", "Jack");
		hashMap.put("password", "123abc");
		System.out.println(JacksonUtil.toJson(hashMap));

	}

}
