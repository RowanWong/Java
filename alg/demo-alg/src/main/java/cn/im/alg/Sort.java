package cn.im.alg;

/**
 * 排序算法
 * 
 * @author Kevin
 *
 */
public class Sort {

	public static void main(String[] args) {
		int[] arr = { 12, 3, 6, 34, 44, 3, 67, 2 };
		selectionSort(arr);
		for (int i : arr) {
			System.out.print(i + " ");
		}
		
		System.out.println(10/2+";"+11/2);
	}

	/**
	 * 选择排序:从第一位开始，从右侧选择最小的数值放到左侧
	 */
	public static void selectionSort(int[] arr) {
		for (int i = 0; i < arr.length - 1; i++) {
			for (int j = i + 1; j < arr.length; j++) {
				if (arr[i] > arr[j]) {
					swap(arr, j, i);
				}
			}
		}
	}

	/**
	 * 根据下标，交换数组中的两个数值
	 * 
	 * @param arr
	 * @param a
	 * @param b
	 */
	public static void swap(int[] arr, int a, int b) {
		int temp = arr[a];
		arr[a] = arr[b];
		arr[b] = temp;
	}
}
