package cn.im.interview;

import org.junit.Test;

public class StringTest {

	/**
	 * 1. String类使用final修饰，为不可变类，不能被继承；内部通过声明为final的字符数组保存字符串
	 * 
	 * 
	 * 
	 * String 直接赋值:  String str="abc";  --编译期创建 ； 可能创建一个或不创建对象 ； 存储在常量池中
	 *    	当目标字符串在Java String池中不存在，会创建一个String对象，然后a指向这个内存地址；当目标字符串已存在，则直接指向其内存地址
	 * String new: String str= new String("ABC");  -- 运行期创建； 至少创建一个对象，也可能两个 ； 存储在堆中 
	 * 		首先会在heap中创建一个str的String对象；如果ABC对象不存在，则会创建ABC的String对象
	 * 
	  *  在JVM中字符串常量池位于永生代（heap被划分为新生代、旧生代、永生代），生命周期长，解决字符串重复问题
	  *  
	  *  参考：
	  *  https://www.cnblogs.com/xiaowangbangzhu/p/10366200.html
	  *  https://blog.csdn.net/chj97/article/details/6899598
	 */
//	@Test
	public void test1() {	//编译  常量池
		String a = "a1";
		String b = "a" + 1;
		String c = new String("a1");
		System.out.println(a == b);
		System.out.println(a == c);
	}
	
//	@Test
	public void test2() {	//编译、运行    常量池、堆
		String str1 = "ab";
		String str2 = "a";
		String str3 = str2 + "b";
		System.out.println(str1 == str3);
	}
	
//	@Test
	public void test3() {	//编译、运行   常量池
		String a = "ab";   
        final String bb = "b";   
        String b = "a" + bb;   
        System.out.println(a == b); 
	}
	
	
//	@Test
	public void test4() {	//编译、运行	常量池、堆
		String a = "ab";
		final String bb = getBB();
		String b = "a" + bb;
		System.out.println(a == b);
	}
 
	private static String getBB() {
		return "b";
	}
	
	
	
	private static String a = "ab";   
//	@Test
	public void test5() {   //编译、运行	常量池、堆
	    String s1 = "a";   
	    String s2 = "b";   
	    String s = s1 + s2;   
	    System.out.println(s == a);   
	    System.out.println(s.intern() == a);   
	}  
	
	
	
	private static String aa = new String("ab");   
	@Test
	public void test6() {    //编译、运行	常量池、堆
        String s1 = "a";   
        String s2 = "b";   
        String s = s1 + s2;   
        System.out.println(s == aa);   
        System.out.println(s.intern() == aa);   
        System.out.println(s.intern() == aa.intern());   
    }  
}
