/*
 * @lc app=leetcode id=10 lang=java
 *
 * [10] Regular Expression Matching
 */
class Solution {
    public boolean isMatch(String s, String p) {
        int m = s.length(), n = p.length();
        // dp[i][j]表示s[0, i)与p[0, j)是否匹配
        boolean[][] dp = new boolean[m + 1][n + 1];
        dp[0][0] = true;
        for (int i = 1; i <= n; i++) {
            if (p.charAt(i - 1) == '*')
                dp[0][i] = i > 1 && dp[0][i - 2];
        }

        for (int i = 1; i <= m; ++i) {
            for (int j = 1; j <= n; ++j) {
                if (p.charAt(j - 1) == '.')
                    dp[i][j] = dp[i - 1][j - 1];
                else if (p.charAt(j - 1) != '*')
                    // p[j-1]!='.'
                    dp[i][j] = dp[i - 1][j - 1] && s.charAt(i - 1) == p.charAt(j - 1);
                else if (s.charAt(i - 1) != p.charAt(j - 2) && p.charAt(j - 2) != '.')
                    // p[j-1]=='*' && p[j-1]!='.'
                    dp[i][j] = dp[i][j - 2];
                else
                    // p[j-1]=='*' && (s[i-1]==p[j-2] || p[j-2]=='.' )
                    // dp[i][j-2]表示'*'使得p[j-2]出现0次
                    // dp[i][j-1]表示'*'使得p[j-2]重复0次(即p[j-2]出现1次)
                    // dp[i-1][j]表示'*'使得p[j-2]重复多次即(使得p[j]都与s[i-1]相等，那么需考察s[0,i-1)与p[0,j)的情况)
                    dp[i][j] = dp[i][j - 2] || dp[i][j - 1] || dp[i - 1][j];
            }
        }
        return dp[m][n];
    }
}
