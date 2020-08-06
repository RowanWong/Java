package cn.im.java8;

/**
 * Java 8 新特性之lambda表达式
 * Lambda 表达式，由三个部分组成：
	第一部分：为一个括号内用逗号分隔的形式参数，参数是函数式接口里面方法的参数；
	第二部分：为一个箭头符号：->；
	第三部分：为方法体，可以是表达式和代码块。

	语法：(parameters) -> expression 或  (parameters) -> { statements; } 
 * @author Kevin
 *
 */
public class lambda {
	public static void main(String[] args) {
		//旧版内部类写法
		new Thread(new Runnable() {
			public void run() {
				System.out.println(Thread.currentThread().getName()+" running. ");
			}
		}).start();
		
		//使用labmda表达式
		new Thread(() -> System.out.println(Thread.currentThread().getName()+" running. ")).start();
	}
}
