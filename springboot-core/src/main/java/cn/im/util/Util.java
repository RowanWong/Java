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
	 
	 public static void main(String[] args) {
		Map<String,String> provInfo = new HashMap<String,String>();
		provInfo.put("北京","1100");
		provInfo.put("天津","1200");
		provInfo.put("河北","1300");
		provInfo.put("山西","1400");
		provInfo.put("内蒙古","1500");
		provInfo.put("辽宁","2100");
		provInfo.put("吉林","2200");
		provInfo.put("黑龙江","2300");
		provInfo.put("上海","3100");
		provInfo.put("江苏","3200");
		provInfo.put("浙江","3300");
		provInfo.put("安徽","3400");
		provInfo.put("福建","3500");
		provInfo.put("江西","3600");
		provInfo.put("山东","3700");
		provInfo.put("河南","4100");
		provInfo.put("湖北","4200");
		provInfo.put("湖南","4300");
		provInfo.put("广东","4400");
		provInfo.put("广西","4500");
		provInfo.put("海南","4600");
		provInfo.put("重庆","5000");
		provInfo.put("四川","5100");
		provInfo.put("贵州","5200");
		provInfo.put("云南","5300");
		provInfo.put("西藏","5400");
		provInfo.put("陕西","6100");
		provInfo.put("甘肃","6200");
		provInfo.put("青海","6300");
		provInfo.put("宁夏","6400");
		provInfo.put("新疆","6500");
		
		String[] contents = {"from	5G是什么？ 	4839	【5G是什么？】点击视频http://im.189.cn/t/juiqy.from.4839，中国电信10000视频客服告诉您。",
		"from	5G的三大特点 	4846	【5G有什么特点？】点击视频http://im.189.cn/t/juiqy.from.4846，中国电信10000视频客服告诉您。",
		"from	5G带来的三大好处 	4840	【5G有什么好处？】点击视频http://im.189.cn/t/juiqy.from.4840，中国电信10000视频客服告诉您。",
		"from	一分钟了解5G的五项技术	4891	【5G有什么技术优势？】点击视频http://im.189.cn/t/juiqy.from.4891，中国电信10000视频客服告诉您。",
		"from	5G网速更快，基站辐射更大吗？ 	4890	【5G网速这么快，基站的辐射会更强吗？】担心辐射会变强？点击视频http://im.189.cn/t/juiqy.from.4890，中国电信10000视频客服告诉您真相到底如何。",
		"from	使用5G需要更换手机吗？	4853	【使用5G需要更换手机吗？】点击视频http://im.189.cn/t/juiqy.from.4853，中国电信10000视频客服告诉您。",
		"from	5G来了，用4G上网打电话是否有影响？ 	4842	【5G来了，用4G上网打电话是否有影响？】点击视频http://im.189.cn/t/juiqy.from.4842，中国电信10000视频客服告诉您。",
		"from	5G网络和5G Wi-Fi是一回事儿吗？ 	5309	【5G网络和5G Wi-Fi有什么不同？】点击视频http://im.189.cn/t/juiqy.from.5309，中国电信10000视频客服告诉您。",
		"from	5G这么快，流量消耗会不会变快？ 	4843	【5G这么快，流量消耗会不会变快？】点击视频http://im.189.cn/t/juiqy.from.4843，中国电信10000视频客服告诉您。",
		"from	VoLTE高清通话的特点	3107	【VoLTE高清通话的特点】点击视频http://im.189.cn/t/juiqy.from.3107，中国电信10000视频客服告诉您。",
		"from	VoLTE操作无难题	4204	【VoLTE操作无难题】点击视频http://im.189.cn/t/juiqy.from.4204，中国电信10000视频客服告诉您。",
		"from	VoLTE业务如何开通	4205	【VoLTE业务如何开通】点击视频http://im.189.cn/t/juiqy.from.4205，中国电信10000视频客服告诉您。",
		"from	开通5G业务后是否可以进行VoLTE视频通话？ 	5307	【开通5G业务后是否可以进行VoLTE视频通话？】点击视频http://im.189.cn/t/juiqy.from.5307，中国电信10000视频客服告诉您。",
		"from	什么是VR？ 	4844	【什么是VR？】点击视频http://im.189.cn/t/juiqy.from.4844，中国电信10000视频客服告诉您。",
		"from	VR会给我们带来一个怎样的世界呢？ 	4845	【VR会给我们带来一个怎样的世界呢？】点击视频http://im.189.cn/t/juiqy.from.4845，中国电信10000视频客服告诉您。",
		"from	Hello，5G	5310	【Hello5G】5G究竟是什么？有什么应用行业？点击视频http://im.189.cn/t/juiqy.from.5310，中国电信10000视频客服告诉您。",
		"from	5G速度有多快？	5312	【5G速度有多快？】点击视频http://im.189.cn/t/juiqy.from.5312，中国电信10000视频客服告诉您。"};


				
		
		Set<String> names = provInfo.keySet();
		System.out.println("省份	省份编码	视频名称	视频ID	短视频");
		for (String name : names) {
			for (String content : contents) {
				String temp = content.replace("from", provInfo.get(name));
				System.out.println( name+"	"+temp);
			}
		}
	}
	 
}
