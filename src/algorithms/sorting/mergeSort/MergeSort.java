package algorithms.sorting.mergeSort;

import java.util.Arrays;

public class MergeSort {
    /*
     * 將陣列拆成兩半
     * 左右兩半再各自繼續拆到剩下一個元素
     * 因為只有一個元素 所以他就是排序好的
     * 返回上一層 merge -> merge 的時候會把兩邊組合並排序
     * 將此段 nums 排序好 並返回上一層 繼續排序組合
     * */
    public static void main(String[] args) {
        int[] nums = {7, 2, 5, 3, 9};
        mergeSort(nums, 0, nums.length - 1);
        System.out.println(Arrays.toString(nums));
    }
    
    private static void mergeSort(int[] nums, int left, int right) {
        if(left >= right) { // 剩一個元素 -> 已排序，直接返回 
            return;
        }
        
        // 左右半拆分是 包含 mid 的左半 和 mid+1 的右半
        int mid = left + (right - left) / 2;
        mergeSort(nums, left, mid); // 遞迴拆分左半
        mergeSort(nums, mid + 1, right); // 遞迴拆分右半
        merge(nums, left, mid, right); // 合併 + 排序 左右兩邊
    }
    
    private static void merge(int[] nums, int left, int mid, int right) {
        int[] temp = new int[right - left + 1];
        int i = left; // 左半起點
        int j = mid + 1; // 右半起點
        int k = 0; // temp 起點
        
        while(i <= mid && j <= right) {
            if(nums[i] <= nums[j]) {
                temp[k] = nums[i];
                k++;
                i++;
            }else {
                temp[k] = nums[j];
                k++;
                j++;
            }
        }
        
        // 如果左半有剩餘元素
        while(i <= mid) {
            temp[k] = nums[i];
            k++;
            i++;
        }
        
        // 如果右半有剩餘元素
        while(j <= right) {
            temp[k] = nums[j];
            k++;
            j++;
        }
        
        // merge 完成後 temp 變成 完整排序好的區段，再覆蓋回原陣列
        for(int p = 0 ; p < temp.length ; p++) {
            nums[left + p] = temp[p]; // 這段起點是從 left 開始
        }
    }
}
