/*
 * @lc app=leetcode id=1143 lang=java
 *
 * [1143] Longest Common Subsequence
 */
class Solution {
    public int longestCommonSubsequence(String text1, String text2) {
        int n = text1.length(), m = text2.length();
        // dp[i][j]表示text1[0, i)与text2[0, j)中的最长子序列的长度
        int[][] dp = new int[n + 1][m + 1];

        for (int i = 1; i <= n; ++i) {
            for (int j = 1; j <= m; ++j) {
                if (text1.charAt(i - 1) == text2.charAt(j - 1))
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        return dp[n][m];
    }
}
