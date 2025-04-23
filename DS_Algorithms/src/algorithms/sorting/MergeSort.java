package algorithms.sorting;

import algorithms.sorting.sortUtils.SortUtils;

public class MergeSort {

	/**
	 * 先對半切，切成單一元素後，再兩兩合併成排序好的
	 */
	public static void mergeSort(int[] arr) {
		if (arr == null || arr.length < 2) {
			return;
		}

		mergeSortRecursive(arr, 0, arr.length - 1);
	}

	// 遞迴對切
	private static void mergeSortRecursive(int[] arr, int left, int right) {
		if (left >= right)
			return; // 剩一個元素

		int mid = left + (right - left) / 2;

		System.out.println("Split: [" + left + ", " + mid + "] and [" + (mid + 1) + ", " + right + "]");

		// 左半邊
		mergeSortRecursive(arr, left, mid);

		// 右半邊
		mergeSortRecursive(arr, mid + 1, right);

		merge(arr, left, mid, right);

		System.out.print("After merge [" + left + ", " + right + "]: ");
		for (int i = left; i <= right; i++) {
			System.out.print(arr[i] + " ");
		}
		System.out.println();
	}

	// 合併排序
	private static void merge(int[] arr, int left, int mid, int right) {
		int[] temp = new int[right - left + 1];

		int i = left; // 左半邊起點
		int j = mid + 1; // 右半邊起點
		int k = 0; // temp 的 index

		// 比較左右兩邊，把比較小的依序放入 temp
		while (i <= mid && j <= right) {
			if (arr[i] <= arr[j]) {
				temp[k++] = arr[i++];
			} else {
				temp[k++] = arr[j++];
			}
		}

		// 如果最後左邊剩下，就補進 temp
		while (i <= mid) {
			temp[k++] = arr[i++];
		}

		// 如果最後右邊剩下，就補進 temp
		while (j <= right) {
			temp[k++] = arr[j++];
		}

		// 把temp 複製回原本的 arr
		// System.arraycopy(來源陣列, 來源起始位置, 目標陣列, 目標貼上起始位置, 複製的元素數量);
		System.arraycopy(temp, 0, arr, left, temp.length);
	}

	public static void main(String[] args) {
		int[] arr = { 6, 3, 8, 2 };

		mergeSort(arr);

		SortUtils.printArray(arr);
	}
}
