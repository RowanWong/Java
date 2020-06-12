package cn.im.jdk;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.Test;

public class JavaUtilTest {

//	@Test
	public void CollectionsTest() {
		ArrayList<Integer> arrayList = new ArrayList<Integer>();
		arrayList.add(-1);
		arrayList.add(3);
		arrayList.add(3);
		arrayList.add(-5);
		arrayList.add(7);
		arrayList.add(4);
		arrayList.add(-9);
		arrayList.add(-7);
		System.out.println("原始数组：" + arrayList);
		// void reverse(List list)：反转
		Collections.reverse(arrayList);
		System.out.println("Collections.reverse(arrayList):" + arrayList);

		// void shuffle(List list),随机排序
		Collections.shuffle(arrayList);
		System.out.println("Collections.shuffle(arrayList):" + arrayList);

		// void swap(List list, int i , int j),交换两个索引位置的元素
		Collections.swap(arrayList, 2, 5);
		System.out.println("Collections.swap(arrayList, 2, 5):" + arrayList);

		// void sort(List list),按自然排序的升序排序
		Collections.sort(arrayList);
		System.out.println("Collections.sort(arrayList):" + arrayList);

		// int binarySearch(List list, Object key)//对List进行二分查找，返回索引，注意List必须是有序的
		int index = Collections.binarySearch(arrayList, -1);
		System.out.println("Collections.binarySearch(arrayList, 7): " + index);

		// boolean replaceAll(List list, Object oldVal, Object newVal), 用新元素替换旧元素
		Collections.replaceAll(arrayList, 4, 12);
		System.out.println("Collections.replaceAll(arrayList, 4, 12): " + arrayList);
	}

	@Test
	public void ArraysTest() {
		int a[] = { 1, 3, 2, 7, 6, 5, 4, 9 };

		// *************排序 sort()****************
		// sort(int[] a)方法按照数字顺序排列指定的数组。
		// sort(int[] a,int fromIndex,int toIndex)按升序排列数组的指定范围
		// parallelSort(int[] a) 按照数字顺序排列指定的数组(并行的)。同sort方法一样也有按范围的排序
		// parallelSort给字符数组排序，sort也可以
		Arrays.sort(a);
		System.out.print("Arrays.sort(a): ");
		for (int i : a) {
			System.out.print(i + " ");
		}
		System.out.println();

		// *************查找 binarySearch()****************
		char[] e = { 'a', 'f', 'b', 'c', 'e', 'A', 'C', 'B' };
		// 排序后再进行二分查找，否则找不到
		Arrays.sort(e);
		System.out.println("Arrays.sort(e)" + Arrays.toString(e));
		System.out.println("Arrays.binarySearch(e, 'c')：");
		int s = Arrays.binarySearch(e, 'c');
		System.out.println("字符c在数组的位置：" + s);

		
		// *************转列表 asList()****************
		/*
		 * 返回由指定数组支持的固定大小的列表。
		 * （将返回的列表更改为“写入数组”。）该方法作为基于数组和基于集合的API之间的桥梁，与Collection.toArray()相结合 。
		 * 返回的列表是可序列化的，并实现RandomAccess 。 此方法还提供了一种方便的方式来创建一个初始化为包含几个元素的固定大小的列表如下：
		 */
		List<String> stooges = Arrays.asList("Larry", "Moe", "Curly");
		System.out.println(stooges);

		
		// *************转字符串 toString()****************
		/*
		 * 返回指定数组的内容的字符串表示形式。
		 */
		char[] k = { 'a', 'f', 'b', 'c', 'e', 'A', 'C', 'B' };
		System.out.println(Arrays.toString(k));// [a, f, b, c, e, A, C, B]

		// *************复制 copy****************
		// copyOf 方法实现数组复制,h为数组，6为复制的长度
		int[] h = { 1, 2, 3, 3, 3, 3, 6, 6, 6, };
		int i[] = Arrays.copyOf(h, 6);
		System.out.println("Arrays.copyOf(h, 6);：");
		for (int j : i) {
			System.out.print(j);
		}
		System.out.println();

		// copyOfRange将指定数组的指定范围复制到新数组中
		int j[] = Arrays.copyOfRange(h, 6, 11);
		System.out.println("Arrays.copyOfRange(h, 6, 11)：");
		// 输出结果66600(h数组只有9个元素这里是从索引6到索引11复制所以不足的就为0)
		for (int j2 : j) {
			System.out.print(j2);
		}
	}

}
