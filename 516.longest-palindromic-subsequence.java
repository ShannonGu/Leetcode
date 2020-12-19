/*
 * @lc app=leetcode id=516 lang=java
 *
 * [516] Longest Palindromic Subsequence
 */
class Solution {
    // https://www.cnblogs.com/grandyang/p/6493182.html
    // https://www.youtube.com/watch?v=OZX1nqaQ_9M
    public int longestPalindromeSubseq(String s) {
        if (s.length() < 2)
            return s.length();
        int n = s.length();
        //dp[i][j]表示[i,j]之间最长回文子序列的长度
        //            dp[i+1][j-1] + 2              if s[i] == s[j];
        //dp[i][j] = 
        //            max(dp[i+1][j], dp[i][j-1])   if s[i] != s[j] 

        int[][] dp = new int[n][n];
        //s[i,j]之间的长度
        for (int l = 1; l <= n; ++l) {
            for (int i = 0; i <= n - l; ++i) {
                int j = i + l - 1;
                if (i == j) {
                    dp[i][j] = 1;
                    continue;
                }
                if (s.charAt(i) == s.charAt(j))
                    dp[i][j] = dp[i + 1][j - 1] + 2;
                else
                    dp[i][j] = Math.max(dp[i + 1][j], dp[i][j - 1]);
            }
        }
        return dp[0][n - 1];
    }
    

    // public int longestPalindromeSubseq(String s) {
    //     if (s.length() < 2)
    //         return s.length();
    //     int n = s.length();
    //     //dp[i]表示从i位置开始的长度为l的字符串的最长palindrome subseq的长度
    //     int[] dp0 = new int[n];
    //     //i位置开始的长度为l-1的subseq的长度
    //     int[] dp1 = new int[n];
    //     //i位置开始的长度为l-2的subseq的长度
    //     int[] dp2 = new int[n];

    //     //字符串长度
    //     for (int l = 1; l <= n; ++l) {
    //         //要计算的当前长度的字符串的起点
    //         for (int i = 0; i <= n - l; ++i) {
    //             //j为终点
    //             int j = i + l - 1;
    //             if (i == j) {
    //                 dp0[i] = 1;
    //                 continue;
    //             }
    //             if (s.charAt(i) == s.charAt(j))
    //                 dp0[i] = dp2[i + 1] + 2;
    //             else
    //                 dp0[i] = Math.max(dp1[i + 1], dp1[i]);
    //         }
    //         //下一轮长度增加
    //         //这一轮的l在下一轮变成l-1, l-1变成l-2;
    //         int[] tmp = dp2;
    //         dp2 = dp1;
    //         dp1 = dp0;
    //         dp0 = tmp;
    //     }

    //     //因为最后dp0赋给了dp1;
    //     //答案是存在dp1中的
    //     //从0开始的长度为n的字符串的最长的palindrome subseq的长度
    //     return dp1[0];
    // }




    
    // int[][] mem;
    // public int longestPalindromeSubseq(String s) {
    //     if (s.length() < 2)
    //         return s.length();
    //     int len = s.length();
    //     mem = new int[len][len];
    //     helper(s, 0, len - 1);
    //     return mem[0][len - 1];
    // }

    // private int helper(String s, int st, int ed) {
    //     if(mem[st][ed] != 0)
    //         return mem[st][ed];
    //     if(st > ed)
    //         return 0;
    //     if(st == ed)
    //         return 1;
    
    //     int res = 0;
    //     if(s.charAt(st) == s.charAt(ed))
    //         res += 2 + helper(s, st + 1, ed - 1);
    //     else
    //         res = Math.max(helper(s, st + 1, ed), helper(s, st, ed - 1));
    //     mem[st][ed] = res;
    //     return res;
    // }

}

