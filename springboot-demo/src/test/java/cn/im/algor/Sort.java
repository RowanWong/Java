package cn.im.algor;

/**
 * 排序算法
 * @author Kevin
 *
 */
public class Sort {
	
	
	public static void main(String[] args) {
		int[] arr = new int[] {5,3,7,2,41,8,13};
//		bubbleSort(arr);
//		insertSort(arr);
		selectSort(arr);
		for (int i : arr) {
			System.out.print(i+" ");
		}
	}
	
	/**
	 * 冒泡排序   	时间复杂度O(n^2)	  空间复杂度O(1)
	 * 相邻元素对比互换，最大的排在最右侧
	 * @param arr
	 */
	public static void bubbleSort(int[] arr) {
		int temp;
		for (int i = 0; i < arr.length - 1; i++) {
			for (int j = i; j < arr.length - 1 - i; j++) {
				if(arr[j] > arr[j++]) {
					temp = arr[j];
					arr[j] = arr[j++];
					arr[j++] = temp;
				}
			}
		}
	}
	
	/**
	 * 直接插入排序		时间复杂度O(n^2)		空间复杂度O(1)
	 * 左侧为有序队列，右侧元素不断插入至左侧
	 * @param arr
	 */
	public static void insertSort(int[] arr) {
		int index = 0;
		for (int i = 1; i < arr.length ; i++) {
			for (int j = i; j > 0; j--) {
				if( arr[j] < arr[j-1]) {
					index = arr[j-1];
					arr[j-1] = arr[j];
					arr[j] = index;
				}
			}
		}
	}
	
	
	/**
	 * 简单选择排序		时间复杂度O(n^2)		空间复杂度O(1)
	 * 左侧为有序队列，不断从右侧选择最小的元素放在有序队列的末尾
	 * @param arr
	 */
	public static void selectSort(int[] arr) {
		for (int i = 0; i < arr.length -1 ; i++) {
			int min = i;
			for (int j = i; j < arr.length; j++) {
				if( arr[min] > arr[j]) {
					min = j;
				}
			}
		}
	}
	
	
}
