import java.util.Deque;

/*
 * @lc app=leetcode id=1092 lang=java
 *
 * [1092] Shortest Common Supersequence 
 */

// @lc code=start
class Solution {
    // https://zxi.mytechroad.com/blog/dynamic-programming/leetcode-1092-shortest-common-supersequence/
    public String shortestCommonSupersequence(String str1, String str2) {
        // 基于LCS，先求出最长公共子序列，然后在其基础上根据不同的情况插入剩下的字符
        // 因为要形成superseq的str,首先str肯定要包含两个str1和str2中的每个字符,然后还要考虑顺序问题
        int l1 = str1.length(), l2 = str2.length();
        int[][] dp = new int[l1 + 1][l2 + 1];
        // 先得出LCS各位置对应的DP值
        for (int i = 1; i <= l1; ++i) {
            for (int j = 1; j <= l2; ++j) {
                if (str1.charAt(i - 1) == str2.charAt(j - 1))
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                else
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
            }
        }

        StringBuilder res = new StringBuilder("");
        // 同时从后往前遍历str1和str2
        while (l1 > 0 || l2 > 0) {
            char c = ' ';

            if (l1 == 0)
                // 如果str1遍历完成,str2还有剩余字符
                // 需要将其插入到LCS中
                c = str2.charAt(--l2);
            else if (l2 == 0)
                // 同理
                c = str1.charAt(--l1);
            else if (str1.charAt(l1 - 1) == str2.charAt(l2 - 1)) {
                // 如果二者当前字符相同
                c = str1.charAt(l1 - 1);
                --l1;
                --l2;
            } else if (dp[l1 - 1][l2] == dp[l1][l2]) {
                // 表示str1[l1-1]这个字符是没有match的
                c = str1.charAt(l1 - 1);
                --l1;
            } else if (dp[l1][l2 - 1] == dp[l1][l2]) {
                // 同理
                c = str2.charAt(l2 - 1);
                --l2;
            }
            // 在res的头部插入字符
            res.insert(0, c);
        }
        return res.toString();
    }
}
// @lc code=end
