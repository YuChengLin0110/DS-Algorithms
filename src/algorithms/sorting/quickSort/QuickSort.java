package algorithms.sorting.quickSort;

import java.util.Arrays;
import java.util.Random;

/*
 * QuickSort
 * 每次選一個 pivot
 * 小於 pivot 的元素 放左邊
 * 大於 pivot 的元素 放右邊
 * 再將 pivot 放到正確位置
 * 再繼續從 左右 兩半邊 繼續 選擇 pivot 排序
 * */
public class QuickSort {
    private static final Random rand = new Random();
    
    public static void main(String[] args) {
        int[] nums = new int[] {5, 2, 9, 3, 7};
        quickSort(nums, 0, nums.length - 1);
        
        System.out.println(Arrays.toString(nums));
    }
    
    private static void quickSort(int[] nums, int left, int right) {
        if(left >= right) { // 只有一個元素 或 超出範圍 重複計算 就 return
            return;
        }
        
        int pivotIndex = partition(nums, left, right); // 取得正確位置的 pivotIndex
        quickSort(nums, left, pivotIndex - 1); // 執行左半邊
        quickSort(nums, pivotIndex + 1, right); // 執行右半邊
        
    }
    
    // 選擇 pivot 分成左右兩邊
    // 並返回 排序好的 pivot 的正確位置
    private static int partition(int[] nums, int left, int right) {
        // 隨機取 pivot，避免遇到已排序 或 幾乎排序好的
        // 將 隨機取得的 index　與　right 交換
        // pivot 再取交換後的 right
        int randIndex = left + rand.nextInt(right - left + 1); // 取隨機index
        swap(nums, right, randIndex); // 交換 隨機index 與 right
        int pivot = nums[right]; // 選取交換後的 right 為 pivot
        
        // 暫存一個 i 之後當作 最後一個 <= pivot 的 位置
        // i 最終就是 pivot 的正確位置
        int i = left;
        
        for(int j = left ; j < right ; j++) {
            if(nums[j] < pivot) {
                swap(nums, i, j);
                i++;
            }
        }
        
        // 將 pivot 放到正確位置 : i -> 使 左邊 < pivot < 右邊
        swap(nums, i, right);
        
        return i;
    }
    
    // 交換元素
    private static void swap(int[] nums, int i, int j) {
        if(i == j) {
            return;
        }
        
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

}
