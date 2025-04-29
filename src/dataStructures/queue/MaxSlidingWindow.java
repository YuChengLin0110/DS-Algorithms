package dataStructures.queue;

import java.util.ArrayDeque;
import java.util.Deque;

public class MaxSlidingWindow {

	/**
	 * 題目：找出 nums 陣列中，每個大小為 k 的滑動視窗的最大值 這個不只需要 Queue 的 FIFO 特性，還需要應用到 Deque
	 * 的雙端特性，判斷最後一個值
	 *
	 * 視窗總數 = len - k + 1，因為視窗起點可以從 index 0 到 index (len - k) 例如：長度是 8 ， k = 3 ，則起點為
	 * 0~5，共有 6 個視窗（8 - 3 + 1 = 6）
	 * 
	 * 當前視窗起點 = i - k + 1 ，因為當 i 指到視窗末端時，起點就是往前 k - 1 格（包含自己） 例如： i = 4，k = 3 → 視窗是
	 * [2, 3, 4] → 起點為 2
	 */
	public static int[] maxSlidingWindow(int[] nums, int k) {
		if (nums == null || k <= 0) {
			return new int[0];
		}

		int len = nums.length;
		int[] result = new int[len - k + 1];
		Deque<Integer> deque = new ArrayDeque<>(); // 存 index

		for (int i = 0; i < len; i++) {

			// 如果 最左邊的 index 已經不在視窗範圍內，就移除
			if (!deque.isEmpty() && deque.peekFirst() < i - k + 1) {
				deque.pollFirst();
			}

			// 移除所有比目前 nums[i] 小的值
			while (!deque.isEmpty() && nums[deque.peekLast()] < nums[i]) {
				deque.pollLast();
			}

			// 把目前的 index 加入候選
			deque.offerLast(i);

			// 滿足一個視窗大小， i 從 k-1 開始
			if (i >= k - 1) {
				// deque 的頭是目前視窗中的最大值的index
				result[i - k + 1] = nums[deque.peekFirst()];
			}
		}

		return result;
	}

	public static void main(String[] args) {
		int[] nums = { 1, 3, -1, -3, 5, 3, 6, 7 };
		int k = 3;

		int[] result = MaxSlidingWindow.maxSlidingWindow(nums, k);

		for (int num : result) {
			System.out.print(num + " ");
		}
	}
}
