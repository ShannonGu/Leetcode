/*
 * @lc app=leetcode id=132 lang=java
 *
 * [132] Palindrome Partitioning II
 */
class Solution {
    // https://www.cnblogs.com/grandyang/p/4271456.html
    public int minCut(String s) {
        if (s.length() < 2)
            return 0;
        int n = s.length();
        // dp[i][j]表示[i,j]之间是否是palindrome
        boolean[][] dp = new boolean[n][n];
        // p[i]表示[i, n-1]之间的最小分割数
        int[] p = new int[n];

        for (int i = n - 1; i >= 0; --i) {
            // [i,n-1]最多分割n - i - 1次
            p[i] = n - 1 - i;
            dp[i][i] = true;
            for (int j = i; j < n; ++j) {
                if (j == i + 1)
                    dp[i][j] = s.charAt(i) == s.charAt(j);
                else if (j > i + 1)
                    dp[i][j] = s.charAt(i) == s.charAt(j) && dp[i + 1][j - 1];

                //[i, j]是palindrome, 再加上[j+1, n-1]的分割数
                if (dp[i][j]) {
                    if (j == n - 1)
                        //若j==n-1,则[i, n-1]都是palindrome,不需要分割
                        p[i] = 0;
                    else
                        //在j处分割一次，考虑p[j+1]即[j+1, n-1]中分割的情况，与p[i]作比较
                        p[i] = Math.min(p[i], p[j + 1] + 1);
                }
            }
        }
        return p[0];
    }

}
