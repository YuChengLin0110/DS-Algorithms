package algorithms.dynamicProgramming;

/**
 * 給定兩個字串 text1 和 text2，返回它們的最長公共子序列的長度
 * 
 * dp[i][j] 代表 text1[0...i-1] 和 text2[0...j-1] 的最長公共子序列長度
 * 
 * 當 text1[i-1] 和 text2[j-1] 相等時，最長公共子序列長度為上一層 dp[i - 1][j - 1] + 1
 * 當 text1[i-1] 和 text2[j-1] 不相等時，取上一層 dp 值中較大的長度
 */
public class LongestCommonSubsequence {
    
    public static int longestCommonSubsequence (String text1, String text2) {
        int m = text1.length();
        int n = text2.length();
        int[][] dp = new int[m + 1][n + 1]; // dp[0][0] = 0 ，沒有取任何字元
        
        // dp[1][1] 代表 text1 和 text2 的第一個字元
        // 因為 String 的索引從 0 開始，所以要使用 i-1 和 j-1 來對應字元
        for(int i = 1 ; i <= m ; i++) {
            for(int j = 1 ; j <= n ; j++) {
                if(text1.charAt(i - 1) == text2.charAt(j - 1)) { 
                    dp[i][j] = dp[i - 1][j - 1] + 1; // 相等時長度 +1
                }else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]); // 不相等時取較長的子序列長度
                }
            }
        }
        
        return dp[m][n];
    }
    
    public static void main(String[] args) {
        String text1 = "abcde";
        String text2 = "ace";
        System.out.println(longestCommonSubsequence(text1, text2));
    }
}
