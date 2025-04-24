package algorithms.dynamicProgramming;

/**
 * 給定一個整數數組 nums，找到一個具有最大和的連續子數組（至少包含一個元素），並返回其最大和
 * 輸入：nums = [-2,1,-3,4,-1,2,1,-5,4]
 * 輸出：6
 * 解釋：子數組 [4,-1,2,1] 的和為 6，是最大的
 * */
public class MaximumSubarray {
    
    public static int maximumSubarray(int[] nums) {
        if(nums == null || nums.length == 0) return 0;
        
        // 這也可以用一個 dp[] 來記錄 到 每一個 nums[i] 的最大和
        int maxSum = nums[0]; // 初始最大和就是第一個數字
        int currSum = nums[0]; // 初始最大和就是第一個數字
        
        for(int i = 1 ; i < nums.length ; i++) {
            
            // 判斷當前數字 + 之前子數組的和 與 當前數字開始的新組合，哪個比較大
            currSum = Math.max(nums[i], currSum + nums[i]);
            
            maxSum = Math.max(maxSum, currSum);
        }
        
        return maxSum;
    }
    
    // 變化題；求最大子數組和，以及該子數組的起始和結束位置
    public static int[] maximumSubarrayWithRange(int[] nums) {
        if(nums == null || nums.length == 0) return new int[] {0, -1, -1};
        
        int len = nums.length;
        int[] dp = new int[len];
        dp[0] = nums[0];
        int maxSum = dp[0];
        int start = 0; // 最大和 起始位置
        int end = 0; // 最大和 結束位置
        int tempStart = 0;
        
        for(int i = 1 ; i < len ; i++) {
            if(dp[i - 1] + nums[i] > nums[i]) {
                dp[i] = dp[i - 1] + nums[i];
            }else {
                dp[i] = nums[i];
                tempStart = i; // 新子數組的起點
            }
            
            if(dp[i] > maxSum) {
                maxSum = dp[i];
                start = tempStart;
                end = i;
            }
        }
        
        return new int[] {maxSum, start, end};
    }
    
    public static void main(String[] args) {
        
        int[] nums = {-2, 1, -3, 4, -1, 2, 1, -5, 4};
        
        System.out.println(maximumSubarray(nums));
        
        // 變化題 maximumSubarrayWithRange
        int[] result = maximumSubarrayWithRange(nums);
        
        System.out.println("MaxSum: " + result[0]);
        System.out.println("Start: " + result[1]);
        System.out.println("End: " + result[2]);
    }
}
