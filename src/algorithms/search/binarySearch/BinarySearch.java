package algorithms.search.binarySearch;

/*
 * BinarySearch 找目標值的 index
 * */
public class BinarySearch {

    public static void main(String[] args) {
        int[] nums = new int[] {1, 3, 5, 7, 9};
        int target = 5;
        System.out.println(binarySearch(nums, target));

    }
    
    private static int binarySearch(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        
        while(left <= right) {
            int mid = left + (right - left) / 2;
            
            if(nums[mid] == target) {
                return mid; // 找到 target
            }else if(nums[mid] < target) {
                left = mid + 1; // nums[mid] 太小 ， 往又找
            }else {
                right = mid - 1; // nums[mid] 太大 ， 往左找
            }
        }
        
        return -1;
    }
}
