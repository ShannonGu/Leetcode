/*
 * @lc app=leetcode id=5 lang=java
 *
 * [5] Longest Palindromic Substring
 */
class Solution {
    // https://www.cnblogs.com/grandyang/p/4464476.html
    public String longestPalindrome(String s) {
        if (s.length() < 2)
            return "";
        int len = s.length();
        // dp[i][j]表示[i, j]是否是回文串
        // dp[i, j] = true , if i == j
        // dp[i, j] = s[i] == s[j] , if i == j + 1
        // dp[i, j] = s[i] == s[j] && dp[i+1][j-1], if i > j + 1
        boolean[][] dp = new boolean[len][len];
        dp[0][0] = true;
        int st = 0, maxLen = 1;
        for (int i = 0; i < len; ++i) {
            // i == j;
            dp[i][i] = true;
            for (int j = 0; j < i; ++j) {
                if (i == j + 1)
                    dp[j][i] = (s.charAt(j) == s.charAt(i));
                else if (i > j + 1)
                    dp[j][i] = (s.charAt(j) == s.charAt(i) && dp[j + 1][i - 1]);
                if (dp[j][i] && i - j + 1 > maxLen) {
                    maxLen = i - j + 1;
                    st = j;
                }
            }
        }
        return s.substring(st, st + maxLen);
    }

    // public String longestPalindrome(String s) {
    // if (s.length() < 2)
    // return s;
    // int len = s.length(), maxLen = 0, st = 0;
    // for (int i = 0; i < len; ++i) {
    // //回文串长度为奇数
    // int len1 = searchPalindrome(s, i, i);
    // if (len1 > maxLen) {
    // maxLen = len1;
    // st = i - maxLen / 2;
    // }
    // //回文串长度为偶数
    // int len2 = searchPalindrome(s, i, i + 1);
    // if (len2 > maxLen) {
    // maxLen = len2;
    // st = i - maxLen / 2 + 1;
    // }
    // }
    // return s.substring(st, st + maxLen);
    // }

    // //计算回文串长度
    // private int searchPalindrome(String str, int left, int right) {
    // while (left >= 0 && right < str.length() && str.charAt(left) ==
    // str.charAt(right)) {
    // --left;
    // ++right;
    // }
    // return right - left - 1;
    // }

    // 马拉车算法
    // https://www.cnblogs.com/grandyang/p/4475985.html
}
