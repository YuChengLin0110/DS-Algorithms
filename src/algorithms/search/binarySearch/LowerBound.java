package algorithms.search.binarySearch;

public class LowerBound {
    /*
     * 找第一個 ≥ target 的位置
     * */
    public static void main(String[] args) {
        int[] nums = new int[] {1, 3, 5, 7, 9};
        int target = 6;
        
        System.out.println(lowerBound(nums, target));
    }
    /*
     * 如果 target 比 nums 裡面的元素都大
     * 有兩種處理法
     * 1. 
     *      一開始設置 int right = nums.length
     *      最後  return left
     *      如果 target 比 nums都大 會 return nums.length -> 插入尾端 ， 沒有元素 ≥ target
     *  
     * 2.
     *      一般處理法
     *      跟平常一樣 int right = nums.length - 1
     *      最後判斷 return nums[left] >= target ? left : -1
     * */
    private static int lowerBound(int[] nums, int target) {
        int left = 0;
        int right = nums.length;
        
        // left == right 就是要找的答案
        while(left < right) {
            int mid = left + (right - left) / 2;
            
            if(nums[mid] < target) {
                left = mid + 1; // mid 太小，答案在右邊
            }else {
                right = mid; // mid 可能是答案，保留
            }
        }
        
        return left;
    }
    
//    private static int lowerBound(int[] nums, int target) {
//        int left = 0;
//        int right = nums.length - 1;
//        
//        // left == right 就是要找的答案
//        while(left < right) {
//            int mid = left + (right - left) / 2;
//            
//            if(nums[mid] < target) {
//                left = mid + 1;
//            }else {
//                right = mid;
//            }
//        }
//        
//        return nums[left] >= target ? left : -1;
//    }

}
