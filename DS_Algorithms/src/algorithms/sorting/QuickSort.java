package algorithms.sorting;

import algorithms.sorting.sortUtils.SortUtils;

public class QuickSort {
	
	/**
	 * 選擇一個 pivot 元素，將陣列分為兩個區域
	 * 小於 pivot 的元素放左邊，大於 pivot 的元素放右邊
	 * 最後將 pivot 放到正確的位置，並對兩邊繼續進行排序
	 * 
	 * partition：
	 * i = left - 1，因為一開始沒有找到比 pivot 小的元素，i 代表比 pivot 小的區域的最後一個位置
	 * 當遍歷完所有元素後，pivot 應該放在 i + 1 的位置，因為 i 是比 pivot 小的最後一個位置，所以此位置右邊的元素都比 pivot 大，左邊的都比 pivot 小
	 * */
	public static void quickSort(int[] arr) {
		quickSortHelper(arr, 0, arr.length - 1);
	}
	
	private static void quickSortHelper(int[] arr, int left, int right) {
		// 左 小於 右 ，表示有 2 個以上元素，需要排序
		if(left < right) {
			
			int pivotIndex = partition(arr, left, right);
			
			// 處理 pivot 左邊部分，不含 pivot
			quickSortHelper(arr, left, pivotIndex - 1);
			
			// 處理 pivot 右邊部分，不含 pivot
			quickSortHelper(arr, pivotIndex + 1, right);
		}
	}
	
	/**
	 * 分割，將比 pivot 小的放左邊，大的放右邊
	 * 最後將 pivot 放到正確位置
	 * */ 
	private static int partition(int[] arr, int left, int right) {
		// 以最右邊做為pivot
		int pivot = arr[right];
		// i 表示比 pivot 小的區域的最後一個 index
		int i = left - 1;
		
		// 將小於等於 pivot的，移到左邊
		for(int j = left ; j < right ; j++) {
			if(arr[j] <= pivot) {
				i++;
				
				SortUtils.swap(arr, j, i);
			}
		}
		
		// 最後將 pivot 放到正確位置 (i + 1)
		SortUtils.swap(arr, i + 1,right);
		
		// 回傳 pivot 位置
		return i + 1;
	}
	
	public static void main(String[] args) {
        int[] arr = { 12, 7, 9, 5, 6, 3, 15, 8 };
        
        quickSort(arr);
        
        SortUtils.printArray(arr);
    }
}
