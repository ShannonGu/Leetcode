/*
 * @lc app=leetcode id=474 lang=java
 *
 * [474] Ones and Zeroes
 */
class Solution {
    //https://www.cnblogs.com/grandyang/p/6188893.html
    // 二维0-1背包问题
    // public int findMaxForm(String[] strs, int m, int n) {
    //     int len = strs.length;
    //     // dp[k][i][j]表示在k个0-1字符串能够满足i个0和j个1的组合的最大字符串个数
    //     int[][][] dp = new int[len + 1][m + 1][n + 1];
    //     for (int k = 1; k <= len; ++k) {
    //         int[] cnt = count(strs[k - 1].toCharArray());
    //         for (int zero = 0; zero <= m; ++zero) {
    //             for (int one = 0; one <= n; ++one) {
    //                 dp[k][zero][one] = dp[k - 1][zero][one];
    //                 if(zero >= cnt[0] && one >= cnt[1])
    //                     dp[k][zero][one] = Math.max(dp[k - 1][zero][one], dp[k - 1][zero - cnt[0]][one - cnt[1]] + 1);
    //             }
    //         }
    //     }
    //     return dp[len][m][n];
    // }

    //优化
    public int findMaxForm(String[] strs, int m, int n) {
        int len = strs.length;
        int[][] dp = new int[m + 1][n + 1];
        for (int k = 1; k <= len; ++k) {
            int[] cnt = count(strs[k - 1].toCharArray());
            for (int zero = m; zero >= cnt[0]; --zero) {
                for (int one = n; one >= cnt[1]; --one) {
                    // if (zero >= cnt[0] && one >= cnt[1])
                        dp[zero][one] = Math.max(dp[zero][one], dp[zero - cnt[0]][one - cnt[1]] + 1);
                }
            }
        }
        return dp[m][n];
    }
    
    private int[] count(char[] s) {
        int[] cnt = new int[2];
        for (int i = 0; i < s.length; ++i) {
            if (s[i] == '0')
                cnt[0]++;
            else
                cnt[1]++;
        }
        return cnt;
    }
}
