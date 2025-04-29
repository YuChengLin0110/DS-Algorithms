package algorithms.dynamicProgramming;

/**
 * 給定一個字串 s 和一個字串 t，請你計算 t 在 s 中出現的所有子序列的個數
 * s 和 t 可能有重複的字符，並且 t 是 s 的一個子序列，且子序列的順序是固定的
 * */
public class DistinctSubsequences {
    
    public static int distinctSubsequences(String s, String t) {
        int m = s.length();
        int n = t.length();
        int[][] dp = new int[m + 1][n + 1];
        
        // 空字串與空字串有 1 種匹配方式
        dp[0][0] = 1; 
        
        // 填充 dp[i][0]，任意的 s 和空的 t，只有 1 種方法，即選擇空子序列
        for(int i = 1 ; i <= m ; i++) {
            dp[i][0] = 1;
        }
        
        // 填充 dp[0][j]，空的 s 和任意的 t，不可能匹配
        for(int j = 1 ; j <=n ; j++) {
            dp[0][j] = 0;
        }
        
        for(int i = 1 ; i <= m ; i++) {
            for(int j = 1 ; j <= n ; j++) {
                if(s.charAt(i - 1) == t.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + dp[i - 1][j];
                }else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }
        
        return dp[m][n];
    }
    
    public static void main(String[] args) {
        String s = "rabbbit";
        String t = "rabbit";
        System.out.println(distinctSubsequences(s, t));
    }
}
