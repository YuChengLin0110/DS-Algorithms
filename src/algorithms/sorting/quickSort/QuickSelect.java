package algorithms.sorting.quickSort;

import java.util.Random;

/*
 * 找 Array nums 第 k 大 的元素
 * */
public class QuickSelect {
    private static final Random rand = new Random();
    
    public static void main(String[] args) {
        int[] nums = new int[] {7, 2, 5, 3, 9};
        int k = 3;
        int target = nums.length - k;
        
        System.out.println(quickSelect(nums, 0, nums.length - 1, target));
    }
    
    private static int quickSelect(int[] nums, int left, int right, int target) {
        if(left == right) {
            return nums[left];
        }
        
        int pivotIndex = partition(nums, left, right);
        
        if(pivotIndex == target) {
            return nums[pivotIndex];
        }else if(pivotIndex < target) {
            return quickSelect(nums, pivotIndex + 1, right, target);
        }else {
            return quickSelect(nums, left, pivotIndex - 1, target);
        }
    }
    
    private static int partition(int[] nums, int left, int right) {
        int randomIndex = left + rand.nextInt(right - left + 1);
        swap(nums, right, randomIndex);
        int pivot = nums[right];
        
        int i = left;
        
        for(int j = left ; j < right ; j++) {
            if(nums[j] < pivot) {
                swap(nums, i, j);
                i++;
            }
        }
        
        swap(nums, i, right);
        
        return i;
    }
    
    private static void swap(int nums[], int i, int j) {
        if(i == j) {
            return;
        }
        
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

}
