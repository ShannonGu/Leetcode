/*
 * @lc app=leetcode id=1000 lang=java
 *
 * [1000] Minimum Cost to Merge Stones
 */
class Solution {
    // https://zxi.mytechroad.com/blog/dynamic-programming/leetcode-1000-minimum-cost-to-merge-stones/
    private final int INF = (int) 1e9;

    public int mergeStones(int[] stones, int K) {
        int len = stones.length;
        // 1堆与k-1堆可以合并成1堆, 所以可以划分成1堆， 以及(len-1)/(K-1)堆,
        // 如果(len-1)/(K-1)整除，说明最终可以合并
        if ((len - 1) % (K - 1) != 0)
            return -1;

        int[] sums = new int[len + 1];
        for (int i = 0; i < len; ++i)
            sums[i + 1] = sums[i] + stones[i];

        // dp[i][j][k]表示将子数组[i,j]合并成k堆的最小代价
        int[][][] dp = new int[len][len][K + 1];

        for (int[][] a : dp) {
            for (int[] b : a) {
                Arrays.fill(b, INF);
            }
        }

        // 只有1堆不需要搬动
        for (int i = 0; i < len; ++i)
            dp[i][i][1] = 0;

        // 子数组最少有两个数
        for (int l = 2; l <= len; ++l) {
            // 子数组的起点
            for (int i = 0; i <= len - l; ++i) {
                int j = i + l - 1;
                // 将子数组划分成k堆
                // 求[i,j]分成K堆是否是可行的
                for (int k = 2; k <= K; ++k) {
                    // 分割点,将[i,m]划分成1堆，[m+1, j]划分成k-1堆;
                    // for (int m = i; m < j; ++m) {
                    // 对上面一句的优化,为了使[i, m]总能合并成一堆,所以,所以左边每部分的个数是1,K-1,K-1....
                    // 事实上对于很多m是无解的，这里可以优化提高效率
                    for (int m = i; m < j; m += K - 1) {
                        dp[i][j][k] = Math.min(dp[i][j][k], dp[i][m][1] + dp[m + 1][j][k - 1]);
                    }
                }
                // 如果将[i, j]划分成K堆是可行的
                // 则将这K堆再合并成1堆
                if (dp[i][j][K] < INF) {
                    dp[i][j][1] = dp[i][j][K] + sums[j + 1] - sums[i];
                }
            }
        }
        return dp[0][len - 1][1];
    }
}
