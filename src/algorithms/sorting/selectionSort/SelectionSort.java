package algorithms.sorting.selectionSort;

import java.util.Arrays;

public class SelectionSort {
    /* 
     * i 的 左邊是 已排序區 ， 右邊是 未排序區
     * 每一輪從 未排序區 選出最小值，交換正確的位置（當前的 i）
     * */
    public static void main(String[] args) {
        int[] nums = {7, 2, 5, 3, 9};
        
        // 最後一個元素可不比較，因為剩最後一個就已經是排序好的
        for(int i = 0 ; i < nums.length - 1 ; i++) {
            int minIndex = i; // 假設 i 是最小值
            
            // 在未排序區找到真正的最小值
            for(int j = i + 1 ; j < nums.length ; j++) {
                if(nums[j] < nums[minIndex]) {
                    minIndex = j;
                }
            }
            
            // 如果 i 就是最小的 就不用換了
            if(minIndex != i) {
                // 將最小值交換到已排序區末端
                int temp = nums[i];
                nums[i] = nums[minIndex];
                nums[minIndex] = temp;
            }
            
        }
        
        System.out.println(Arrays.toString(nums));

    }

}
