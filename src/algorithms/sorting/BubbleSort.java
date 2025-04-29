package algorithms.sorting;

import java.util.Arrays;

import algorithms.sorting.sortUtils.SortUtils;

public class BubbleSort {
	
	/**
	 * 透過不斷比較相鄰元素，將大的數字往右邊冒泡
	 * 內層迴圈：一次從左到右比相鄰元素，把大的數字往右
	 * 外層迴圈：因為只跑一輪只會把一個最大值冒到最後，所以需要多輪才能把所有數字排好
	 * */
	public static void bubbleSort(int[] arr) {

		int len = arr.length;
		boolean swapped;
		
		// 外層迴圈控制 總共要冒泡幾輪
		for (int i = 0; i < len - 1; i++) {
			swapped = false;
			
			// 內層迴圈進行 一輪冒泡，將當前最大值推到右邊
			// 因為每一輪結束後，最大的數字都會被冒到最右邊
			// 所以右邊的元素已經是排好序的，不需要再比較
			// 每輪比較範圍就可以少一個 ， j 只要跑到 n - 1 - i
			for (int j = 0; j < len - 1 - i; j++) {
				if (arr[j] > arr[j + 1]) {
					
					SortUtils.swap(arr, j, j + 1);
					swapped = true;
				}
			}
			
			// 如果這一輪完全沒有交換，代表已經排序完成，可以提前結束
			if (!swapped) {
				break;
			}
		}
	}

	public static void main(String[] args) {
		int[] nums = { 5, 1, 4, 2, 8 };
		bubbleSort(nums);
		System.out.println("Sorted: " + Arrays.toString(nums));
	}
}
