/*
 * @lc app=leetcode id=10 lang=java
 *
 * [10] Regular Expression Matching
 */
class Solution {
    // https://www.cnblogs.com/grandyang/p/4461713.html
    // public boolean isMatch(String s, String p) {
    //     if (p.isEmpty())
    //         return s.isEmpty();
    //     if (p.length() == 1)
    //         return (s.length() == 1 && (s.charAt(0) == p.charAt(0) || p.charAt(0) == '.'));

    //     if (p.charAt(1) != '*') {
    //         if (s.isEmpty())
    //             return false;
    //         return (s.charAt(0) == p.charAt(0) || p.charAt(0) == '.') && isMatch(s.substring(1), p.substring(1));
    //     }

    //     // p.charAt(1) == '*' && !s.isEmpty()
    //     while (!s.isEmpty() && (s.charAt(0) == p.charAt(0) || p.charAt(0) == '.')) {
    //         // 两者第一个字符可以匹配
    //         // '*'复制0次前面的字符
    //         if (isMatch(s, p.substring(2)))
    //             return true;

    //         // 复制1次或者多次,那么第一个字符可以匹配
    //         s = s.substring(1);

    //     }

    //     // p.charAt(1) == '*'， 且两者第一个字符不能匹配
    //     // '*'复制0次, 所以只能从p的第2个字符开始匹配
    //     return isMatch(s, p.substring(2));
    // }

    public boolean isMatch(String s, String p) {
        int m = s.length(), n = p.length();
        //dp[i][j]表示s[0, i)与p[0, j)是否匹配
        boolean[][] dp = new boolean[m + 1][n + 1];
        
        dp[0][0] = true;
        for (int i = 0; i <= m; ++i) {
            for (int j = 1; j <= n; ++j) {
                if (p.charAt(j - 1) != '*') {
                    //i - 1不能下标越界
                    dp[i][j] = i > 0 && dp[i - 1][j - 1] && (s.charAt(i - 1) == p.charAt(j - 1) || p.charAt(j - 1) == '.');
                } else if (j > 1) {
                    //注意j-2不能下标越界
                    //dp[i][j - 2]表示'*'匹配0次,只需s前i个与p的前i-2个字符匹配
                    //dp[i-1][j]表示'*'至少匹配1次,这样s.charAt(i - 1)在'*'的作用下总是能得到匹配的
                    //所以只需验证s.charAt(i - 1)与p.charAt(j - 2)是否相等，或者p.charAt(j - 2)是否=='.';
                    dp[i][j] = dp[i][j - 2]
                            || (i > 0 && dp[i - 1][j] && (s.charAt(i - 1) == p.charAt(j - 2) || p.charAt(j - 2) == '.'));
                }
            }
        }
        return dp[m][n];
    }
}
