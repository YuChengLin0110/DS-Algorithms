package algorithms.dynamicProgramming;

import java.util.Arrays;

/**
 * 爬樓梯
 * 一次可以爬 1 階 或 2 階
 * 爬到第 n 階，有幾種方式？
 * 
 * f(0) = 1 （原地不動）
 * f(1) = 1 （一步爬到 1）
 * f(2) = 2 （1+1 或 2）
 * f(n) = f(n - 1) + f(n - 2)
 * */
public class ClimbingStairs {

    public static int climbingStairsRecursive(int n) {
        if(n == 0) return 1;
        if(n == 1) return 1;
        if(n == 2) return 2;
        
        return climbingStairsRecursive(n - 1) + climbingStairsRecursive(n - 2);
    }
    
    // TopDown：從 n 開始往下找，搭配 memo 記憶
    public static int climbingStairsTopDown(int n) {
        if(n == 0) return 1;
        if(n == 1) return 1;
        
        int[] memo = new int[n + 1];
        
        Arrays.fill(memo, -1);
        memo[0] = 1;
        memo[1] = 1;
        
        return climbingStairsTopDownHelper(n, memo);
        
    }
    
    // BottomUp：從小問題往上加到 n
    public static int climbingStairsBottomUp(int n) {
        if(n == 0) return 1;
        if(n == 1) return 1;
        
        int[] dp = new int[n + 1];
        dp[0] = 1;
        dp[1] = 1;
        
        for(int i = 2 ; i <= n ; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        
        return dp[n];
    }
    
    // 空間優化 BottomUp：只保留前兩步
    public static int climbingStairsBottomUpOptimized(int n) {
        if(n == 0) return 1;
        if(n == 1) return 1;
        
        int prev2 = 1;
        int prev1 = 1;
        
        for(int i = 2 ; i <= n ; i++) {
            int curr = prev1 + prev2;
            prev2 = prev1;
            prev1 = curr;
        }
        
        return prev1;
    }
    
    private static int climbingStairsTopDownHelper(int n, int[] memo) {
        if(memo[n] != -1) return memo[n];
        
        memo[n] = climbingStairsTopDownHelper(n - 1, memo) + climbingStairsTopDownHelper(n - 2, memo);
        
        return memo[n];
    }
    
    public static void main(String[] args) {
        int n = 5;
        System.out.println("Recursive: " + climbingStairsRecursive(n));
        System.out.println("TopDown: " + climbingStairsTopDown(n));
        System.out.println("BottomUp: " + climbingStairsBottomUp(n));
        System.out.println("Optimized BottomUp: " + climbingStairsBottomUpOptimized(n));
    }
}
