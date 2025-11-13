package algorithms.sorting.insertionSort;

import java.util.Arrays;

public class InsertionSort {
    /*
     * 選一個 key 通常是 index 1 位置 的值
     * key 左邊為 已排序區 ， key 右邊為未排序區
     * j 只會在 已排序區移動
     * */
    public static void main(String[] args) {
        int[] nums = {7, 2, 5, 3, 9};
        
        // 選定 key 從 index = 1 開始
        for(int i = 1 ; i < nums.length ; i++) {
            int key = nums[i];
            int j = i - 1; // j 只會在已排序區的範圍內 -> 0 ~ i - 1
            
            // 如果 nums[j] > key -> 將 nums[j] 向右移
            while(j >= 0 && nums[j] > key) {
                nums[j + 1] = nums[j];
                j--; // 繼續檢查已排序區的前一個數
            }
            
            // 最後將 key 放到正確的位置
            nums[j + 1] = key;
        }
        
        System.out.println(Arrays.toString(nums));
    }
}
