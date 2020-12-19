import java.util.Comparator;
import java.util.stream.IntStream;

/*
 * @lc app=leetcode id=518 lang=java
 *
 * [518] Coin Change 2
 */
class Solution {
    //完全背包问题
    // public int change(int amount, int[] coins) {
    //     int n = coins.length;
    //     //dp[i][j]表示前i个硬币组成钱数为j的不同组合方法
    //     int[][] dp = new int[n + 1][amount + 1];
    //     dp[0][0] = 1;
    //     for (int i =1; i <= n; ++i) {
    //         for (int j = 0; j <= amount; ++j) {
    //             dp[i][j] = dp[i - 1][j];
    //             if (j >= coins[i - 1])
    //                 //完全背包问题
    //                 dp[i][j] += dp[i][j - coins[i - 1]];
    //         }
    //     }
    //     return dp[n][amount];
    // }


    
    //完全背包优化
    //f(i, j)      =sum(f(i-1, j - coin*k)) = f(i-1, j) + f(i-1, j-coin) + f(i-1, j-2*coin) + f(i-1, j-3*coin) + ...+
    //f(i, j-coin) =                        =             f(i-1, j-coin) + f(i-1, j-2*coin) + ... +
    //f(i, j) = f(i-1,j) + f(i,j-coin);
    public int change(int amount, int[] coins) {
        int[] dp = new int[amount + 1];
        dp[0] = 1;
        for (int coin : coins) {
            for (int i = coin; i <= amount; ++i) {
                dp[i] += dp[i - coin];
            }
        }
        return dp[amount];
    }
}
