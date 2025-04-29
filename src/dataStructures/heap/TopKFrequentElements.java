package dataStructures.heap;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.PriorityQueue;

/**
 * Top K Frequent Elements
 * 給一個整數陣列 nums 和一個整數 k，找出出現頻率最高的 k 個元素
 * */
public class TopKFrequentElements {
    
    public static int[] topKFrequentElements(int[] nums, int k) {
        PriorityQueue<Entry<Integer, Integer>> heap = new PriorityQueue<>(Comparator.comparingInt(entry -> entry.getValue()));
        Map<Integer, Integer> map = new HashMap<>();
        int[] result = new int[k];
        
        // 計算每個數字出現的頻率
        for(int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        
        for(Entry<Integer, Integer> entry : map.entrySet()) {
            if(heap.size() < k) {
                heap.offer(entry);
             
                // 堆中已經有 k 個元素，若新的元素頻率大於堆頂，則替換堆頂
            }else if (entry.getValue() > heap.peek().getValue()) {
                heap.poll();
                heap.offer(entry);
            }
        }
        
        for(int i = 0 ; i < k ; i++) {
            result[i] = heap.poll().getKey();
        }
        
        return result;
    }
    
    public static void main(String[] args) {
        int[] nums = {1,1,1,2,2,3};
        int k = 2;
        
        int[] result = topKFrequentElements(nums, k);
        System.out.println(Arrays.toString(result));
    }
}
