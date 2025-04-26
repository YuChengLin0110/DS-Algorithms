package dataStructures.heap;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 找出數組中第 K 大的元素 Find Kth Largest Element in an Array 
 * 給定一個未排序的整數數組 nums，以及一個整數 k，請你找出這個數組中第 k 大的元素 
 * 注意：你需要找的是「排序後的第 k個最大元素」，而不是第 k 個不同的元素。
 */
public class FindKthLargest {

    public static int findKthLargest(int[] nums, int k) {
        PriorityQueue<Integer> heap = new PriorityQueue<>(Comparator.comparingInt(a -> a));

        for (int num : nums) {
            // 堆中元素數量不足 k 個，直接加入
            if (heap.size() < k) {
                heap.offer(num);
            // 堆中元素已經滿 k 個，如果新的元素比堆頂元素大，替換堆頂
            } else if (num > heap.peek()) {
                heap.poll();
                heap.offer(num);
            }
        }

        return heap.peek();
    }
    
    public static void main(String[] args) {
        int[] nums = {3, 2, 1, 5, 6, 4};
        int k = 2;
        int result = findKthLargest(nums, k);
        System.out.println("第 " + k + " 大的元素是: " + result);
    }
}
