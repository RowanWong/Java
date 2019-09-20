package cn.im.collection;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.junit.Test;

public class MapDemo {
	
	
	//测试HashMap是否是"fast-fail"机制
	@Test
	public void test() {
		Map<String,String> table2 = new HashMap<String,String>();

		table2.put("a", "gao");
		table2.put("b", "zhu");
		table2.put("c", "zhang");
		table2.put("d", "sun");
		
		Iterator<String> keys = table2.keySet().iterator();
		while (keys.hasNext()) {
			String s = keys.next();
			if("c".equals(s)) {
				table2.remove(s);
			}else {
				System.out.println(s);
			}
		}
	}
}
