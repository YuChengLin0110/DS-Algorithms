package algorithms.dynamicProgramming;

/**
 * 背包問題
 * 給定一組物品，每個物品有一個重量和價值，並且有一個背包，該背包的最大承重為 w
 * 我們需要決定每個物品是否放入背包中，以使得背包中的物品總價值最大，並且不超過背包的最大承重 w
 * 物品的數量為 n
 * 每個物品有兩個屬性：weight[i] 和 value[i]，分別表示第 i 個物品的重量和價值
 * 背包的最大承重為 w
 * */
public class KnapsackProblem {
    
    public static int knapsackProblem(int[] weights, int[] values, int W) {
        int len = weights.length; // 所有物品的數量
        
        // dp[i][w] 表示前 i 個物品，在背包容量為 w 的情況下的最大價值
        int[][] dp = new int[len + 1][W + 1];
        
        // 依序計算每個物品 i 和每個容量 w
        for(int i = 1; i <= len ; i++) {
            for(int w = 1 ; w <= W ; w++) {
                if(w >= weights[i - 1]) { // // 如果背包容量 w 足夠放第 i 個物品
                    // 容量足夠：這裡有兩種情況：
                    // 1. 不放第 i 個物品，最大價值就等於不放這個物品時的最大價值：dp[i - 1][w]。
                    // 2. 放第 i 個物品，最大價值是放當前 i 這個物品後 w - weights[i-1] ，剩下容量的最大價值 + 當前物品的價值：dp[i - 1][w - weights[i - 1]] + values[i - 1]
                    // i 代表目前考慮的第 i 個物品，而物品的陣列索引是從 0 開始，因此需要使用 i - 1 來訪問物品的重量與價值
                    dp[i][w] = Math.max(dp[i - 1][w], dp[i - 1][w - weights[i - 1]] + values[i - 1]);
                }else { // 容量不夠放第 i 個物品
                    // 只能不放第 i 個物品，最大價值就等於不放這個物品時的最大價值：dp[i - 1][w]
                    dp[i][w] = dp[i - 1][w];
                }
            }
        }
        
        // 所有物品，在背包容量為 W 時的最大價值
        return dp[len][W];
    }
    
    public static void main(String[] args) {
        int[] weights = {1, 2, 3, 8, 4, 5};
        int[] values = {20, 5, 10, 40, 30, 50};
        int W = 10;

        System.out.println(knapsackProblem(weights, values, W));
    }
}
