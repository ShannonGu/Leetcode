/*
 * @lc app=leetcode id=72 lang=java
 *
 * [72] Edit Distance
 */
class Solution {
    // https://www.cnblogs.com/grandyang/p/4344107.html
    public int minDistance(String word1, String word2) {
        int l1 = word1.length(), l2 = word2.length();

        // dp[i][j]表示将word1[0, i)转换为word2[0, j)所需要的最少次数;
        int dp[][] = new int[l1 + 1][l2 + 1];

        // 初始化;
        for (int i = 0; i <= l1; ++i)
            dp[i][0] = i;
            
        for (int i = 0; i <= l2; ++i)
            dp[0][i] = i;

        for (int i = 1; i <= l1; ++i) {
            for (int j = 1; j <= l2; ++j) {
                if (word1.charAt(i - 1) == word2.charAt(j - 1))
                    // 若word1[i-1] == word[j-1]
                    // 直接取word1[0, i-1)与word2[0, j-1)的情况即可;
                    dp[i][j] = dp[i - 1][j - 1];
                else
                    // 若不等;
                    // dp[i-1][j-1]表示将word1[i-1]replace为word2[j-1];
                    // dp[i-1][j]表示将word1[i-1]delete;
                    // dp[i][j-1]表示在word1[i-1]后面insert word2[j-1]，
                    // 再取word1[0, i)与word2[0, j-1)的情况;
                    dp[i][j] = Math.min(dp[i - 1][j - 1], Math.min(dp[i - 1][j], dp[i][j - 1])) + 1;
            }
        }
        return dp[l1][l2];
    }
}
