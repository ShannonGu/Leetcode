/*
 * @lc app=leetcode id=87 lang=java
 *
 * [87] Scramble String
 */
class Solution {
    //https://www.cnblogs.com/grandyang/p/4318500.html
    // public boolean isScramble(String s1, String s2) {
    //     if(s1.length() != s2.length())
    //         return false;
    //     if(s1.equals(s2))
    //         return true;
    //     char[] str1 = s1.toCharArray(), str2 = s2.toCharArray();
    //     Arrays.sort(str1);
    //     Arrays.sort(str2);
    //     if (!String.valueOf(str1).equals(String.valueOf(str2)))
    //         return false;

    //     for (int i = 1; i < s1.length(); ++i) {
    //         String s11 = s1.substring(0, i);
    //         String s12 = s1.substring(i);
    //         String s21 = s2.substring(0, i);
    //         String s22 = s2.substring(i);
    //         if(isScramble(s11, s21) && isScramble(s12, s22))
    //             return true;

    //         s21 = s2.substring(s1.length() - i);
    //         s22 = s2.substring(0, s1.length() - i);
    //         if(isScramble(s11, s21) && isScramble(s12, s22))
    //             return true;
    //     }
    //     return false;
    // }

    public boolean isScramble(String s1, String s2) {
        if(s1.length() != s2.length())
            return false;
        if(s1.equals(s2))
            return true;
        int n = s1.length();
        //dp[i][j][len]表示s1[i, i + len - 1]和s2[j, j + len - 1]是否Scramble
        boolean[][][] dp = new boolean[n][n][n + 1];

        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                //长度为1是否Scramble取决于两个字符是否相同
                dp[i][j][1] = s1.charAt(i) == s2.charAt(j);
            }
        }

        for (int len = 2; len <= n; ++len) {
            for (int i = 0; i <= n - len; ++i) {
                for (int j = 0; j <= n - len; ++j) {
                    //k为前半部分长度
                    for (int k = 1; k < len; ++k) {
                        //两者前半部分长度为k的字符串是Scramble且后半部分len-k也是Scramble
                        //或者s1前半部分长度为k与s2后半部分长度为k是Scramble，s1后半部分与s2前半部分是Scramble
                        if ((dp[i][j][k] && dp[i + k][j + k][len - k])
                                || (dp[i][j + len - k][k] && dp[i + k][j][len - k]))
                            dp[i][j][len] = true;
                    }
                }
            }
        }
        return dp[0][0][n];
    }
}
