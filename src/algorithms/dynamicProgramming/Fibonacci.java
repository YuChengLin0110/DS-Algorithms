package algorithms.dynamicProgramming;

import java.util.Arrays;

/**
 * 費波那契 Fibonacci 
 * f(0) = 0
 * f(1) = 1 
 * f(2) = 1 -> f(1) + f(0) 
 * f(3) = 2 -> f(2) + f(1)
 * 
 * n >= 2 就可以套用
 * f(n) = f(n - 1) + f(n - 2)
 */
public class Fibonacci {
    
    // 遞迴
    public static int fibRecursive(int n) {
        if (n == 0) return 0;
        if (n == 1) return 1;

        return fibRecursive(n - 1) + fibRecursive(n - 2);
    }
    
    /**
     * 使用 memoization 儲存已計算過的值，避免重複計算
     * 
     * TopDown：從大問題開始，一層一層往下查
     * 想查第 n 階，需要先解出 n - 1 和 n - 2 的答案，再組出大的
     * */
    public static int fibTopDown(int n) {
        if(n == 0) return 0;
        if(n == 1) return 1;
        
        int[] memo = new int[n + 1];
        
        // 初始化為 -1 ，表示還沒計算
        Arrays.fill(memo, -1);
        
        memo[0] = 0;
        memo[1] = 1;

        return fibTopDownHelper(n, memo);
    }
    
    /**
     * BottomUp：從小問題開始，往上組出大問題的答案
     * 先解出第 0 階和第 1 階，再依序推到第 n 階
     * */
    public static int fibBottomUp(int n) {
        if (n == 0) return 0;
        if (n == 1) return 1;

        int[] dp = new int[n + 1];
        dp[0] = 0;
        dp[1] = 1;

        for (int i = 2; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }

        return dp[n];
    }
    
    /**
     * 空間優化 fibBottomUp 實作
     * 只保存前兩個計算結果，節省空間
     * */
    public static int fibBottomUpOptimized(int n) {
        if (n == 0) return 0;
        if (n == 1) return 1;

        int prev2 = 0; // dp[0]
        int prev1 = 1; // dp[1]

        for (int i = 2; i <= n; i++) {
            int curr = prev2 + prev1;
            prev2 = prev1;
            prev1 = curr;
        }

        return prev1;
    }

    private static int fibTopDownHelper(int n, int[] memo) {
        if(memo[n] != -1) return memo[n]; // 如果已經計算過，直接 return

        memo[n] = fibTopDownHelper(n - 1, memo) + fibTopDownHelper(n - 2, memo);

        return memo[n];
    }
    
    public static void main(String[] args) {
        int n = 10;
        System.out.println("Recursive: " + fibRecursive(n));
        System.out.println("TopDown: " + fibTopDown(n));
        System.out.println("BottomUp: " + fibBottomUp(n));
        System.out.println("Optimized BottomUp: " + fibBottomUpOptimized(n));
    }
}
