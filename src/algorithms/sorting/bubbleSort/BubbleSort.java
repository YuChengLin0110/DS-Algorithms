package algorithms.sorting.bubbleSort;

import java.util.Arrays;

public class BubbleSort {
    /*
     * 比較相鄰元素
     * 每次從左到右，兩兩比較
     * 如果順序錯了就交換
     * 把最大（或最小）「冒泡」到末端
     *      第一輪：最大值會被交換到最後
     *      第二輪：次大值會被冒到倒數第二位
     *      重複，直到整個陣列有序
     * */
    public static void main(String[] args) {
        int[] nums = {7, 2, 5, 3, 9};
        
        for(int i = 0 ; i < nums.length ; i++) { // 總共要比較 n-1 輪
            boolean swapped = false; // 判斷此輪有沒有元素被交換
            for(int j = 0 ; j < nums.length - 1 - i ; j++) { // 每輪比較到還沒排序好的部分 -> nums.length - 1 - i
                if(nums[j] > nums[j + 1]) {
                    int temp = nums[j];
                    nums[j] = nums[j + 1];
                    nums[j + 1] = temp;
                    
                    swapped = true;
                }
            }
            
            if(!swapped) {
                break; // 此輪沒有元素被交換 -> 都排序好了，提早結束
            }
        }
        
        System.out.println(Arrays.toString(nums));
    }

}
