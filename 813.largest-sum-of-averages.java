/*
 * @lc app=leetcode id=813 lang=java
 *
 * [813] Largest Sum of Averages
 */

// @lc code=start
class Solution {
    // 相似题目
    // https://zxi.mytechroad.com/blog/dynamic-programming/leetcode-1278-palindrome-partitioning-iii/
    public double largestSumOfAverages(int[] A, int K) {
        int n = A.length;
        // 先计算累加和
        double[] sums = new double[n + 1];
        for (int i = 0; i < n; ++i) {
            sums[i + 1] = sums[i] + A[i];
        }
        // cost[i][j]表示[i,j]之间的平均数
        double[][] cost = new double[n][n];
        for (int l = 1; l <= n; ++l) {
            for (int i = 0; i <= n - l; ++i) {
                // 区间内只有一个数
                if (l == 1) {
                    cost[i][i] = A[i];
                    continue;
                }
                int j = i + l - 1;
                cost[i][j] = (sums[j + 1] - sums[i]) / l;
            }
        }
        // dp[i][j]表示将[0,i]内分成j段,各段平均数之和的最大值
        double[][] dp = new double[n][K + 1];
        for (int i = 0; i < n; ++i) {
            // 分成一段的情况
            dp[i][1] = cost[0][i];
            for (int k = 2; k <= K; ++k) {
                // 遍历分割点
                for (int j = 0; j < i; ++j) {
                    // 将[0,j]继续划分成k-1段
                    dp[i][k] = Math.max(dp[i][k], dp[j][k - 1] + cost[j + 1][i]);
                }
            }
        }
        return dp[n - 1][K];
    }
}
// @lc code=end
