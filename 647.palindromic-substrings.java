/*
 * @lc app=leetcode id=647 lang=java
 *
 * [647] Palindromic Substrings
 */
class Solution {
    //https://www.cnblogs.com/grandyang/p/7404777.html
    // public int countSubstrings(String s) {
    //     if (s.length() < 2)
    //         return s.length();
    //     int n = s.length(), res = 0;
    //     boolean[][] dp = new boolean[n][n];
    //     for (int i = 0; i < n; ++i) {
    //         //单个元素总是palindromic
    //         dp[i][i] = true;
    //         ++res;
    //         for (int j = 0; j < i; ++j) {
    //             if (i == j + 1)
    //                 dp[j][i] = s.charAt(j) == s.charAt(i);
    //             else if (i > j + 1)
    //                 dp[j][i] = (s.charAt(j) == s.charAt(i) && dp[j + 1][i - 1]);
    //             if (dp[j][i])
    //                 ++res;
    //         }
    //     }
    // }
    
    public int countSubstrings(String s) {
        int n = s.length();
        boolean[][] dp = new boolean[n][n];
        int res = 0;
        for(int l = 1; l <= n; ++l){
            for(int i = 0; i <= n - l; ++i){
                int j = i + l - 1;
                if(i == j){
                    dp[i][j] = true;
                    ++res;
                    continue;
                }
                if(j == i + 1)
                    dp[i][j] = s.charAt(i) == s.charAt(j);
                else if(j > i + 1)
                    dp[i][j] = s.charAt(i) == s.charAt(j) && dp[i+1][j-1];
                if(dp[i][j])
                    ++res;
            }
        }
        return res;
    }
}

