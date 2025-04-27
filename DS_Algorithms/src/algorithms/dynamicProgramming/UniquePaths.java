package algorithms.dynamicProgramming;

/**
 * 給定一個 m x n 的網格，從左上角 (0, 0) 開始，只有兩個方向可以移動：向右或向下，計算從左上角到右下角的不同走法數量
 * 
 * */
public class UniquePaths {
    
    public static int uniquePaths(int m, int n) {
        int[][] dp = new int[m][n];
        dp[0][0] = 1;
        
        // 初始條件：第一列和第一行的路徑數都是 1
        for(int i = 0 ; i < m ; i++) {
            dp[i][0] = 1; // 第一列只能往下走  
        }
        
        for(int j = 0 ; j < n ; j++) {
            dp[0][j] = 1; // 第一行只能往右走
        }
        
        // 計算剩下的格子
        for(int i = 1 ; i < m ; i++) {
            for(int j = 1 ; j < n ; j++) {
                
                dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
            }
        }
        
        // 返回右下角的路徑數
        return dp[m - 1][n - 1];
    }
    
    public static void main(String[] args) {
        int m = 3, n = 7;
        System.out.println(uniquePaths(m, n));
    }
}
