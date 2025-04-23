package algorithms.sorting;

import algorithms.sorting.sortUtils.SortUtils;

public class InsertionSort {
	
	/**
	 * 像是整理撲克牌的方式，分成 已整理 跟 未整理
	 * 每次從 未整理 選出一張 key，往左比大小，把比 key 大的牌全部往右移
	 * 騰出空位，再把 key 插入正確位置
	 * */
	public static void insertionSort(int[] arr) {
		int len = arr.length;
		
		// 從第 2 張開始，每次抽出 1 張 key ，插入左邊已排序的部分
		for (int i = 1; i < len; i++) {
			int key = arr[i];
			
			// key 左邊是已排序的部分，從已排序的地方開始往左看
			int j = i - 1; 
			
			// 左邊的元素比 key 大，就把它往右邊移動，空出位子給 key
			while (j >= 0 && arr[j] > key) {
				arr[j + 1] = arr[j]; // 把 arr[j] 往右邊搬一格，空出位置
				j--;  // 繼續往左找
			}
			
			// 把 key 插入空出來的位置
			arr[j + 1] = key;
		}
	}

	public static void main(String[] args) {
		int[] arr = { 5, 1, 4, 2, 8 };

		insertionSort(arr);

		SortUtils.printArray(arr);
	}
}
