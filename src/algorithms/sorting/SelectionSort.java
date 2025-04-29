package algorithms.sorting;

import algorithms.sorting.sortUtils.SortUtils;

public class SelectionSort {
	
	/**
	 * 每一輪從 未排序區 選出最小值，放到正確的位置（當前的 i）
	 * 每一輪都選出最小的，排到前面
	 * i 的左邊是已排序 右邊是未排序
	 * */
	public static void selectionSort(int[] arr) {
		int len = arr.length;
		
		// 外層控制每一輪要放最小值的位置
		for(int i = 0 ; i < len -1 ; i++) {
			int minIndex = i; // 假設目前 i 是最小值的 index
			
			// 在剩下未排序中，找出最小值的 index
			for(int j = i + 1 ; j < len ; j++) {
				if(arr[j] < arr[minIndex]) {
					minIndex = j;
				}
			}
			
			// 最小值換到 i 位置
			if(minIndex != i) {
				SortUtils.swap(arr, i, minIndex);
			}
		}
	}
	
	public static void main(String[] args) {
        int[] nums = {5, 3, 6, 2, 4};
        selectionSort(nums);

        for (int n : nums) {
            System.out.print(n + " ");
        }
    }
}
