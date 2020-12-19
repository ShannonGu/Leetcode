/*
 * @lc app=leetcode id=115 lang=java
 *
 * [115] Distinct Subsequences
 *
 * https://leetcode.com/problems/distinct-subsequences/description/
 *
 * algorithms
 * Hard (35.07%)
 * Total Accepted:    106.5K
 * Total Submissions: 303.6K
 * Testcase Example:  '"rabbbit"\n"rabbit"'
 *
 * Given a string S and a string T, count the number of distinct subsequences
 * of S which equals T.
 * 
 * A subsequence of a string is a new string which is formed from the original
 * string by deleting some (can be none) of the characters without disturbing
 * the relative positions of the remaining characters. (ie, "ACE" is a
 * subsequence of "ABCDE" while "AEC" is not).
 * 
 * Example 1:
 * 
 * 
 * Input: S = "rabbbit", T = "rabbit"
 * Output: 3
 * Explanation:
 * 
 * As shown below, there are 3 ways you can generate "rabbit" from S.
 * (The caret symbol ^ means the chosen letters)
 * 
 * rabbbit
 * ^^^^ ^^
 * rabbbit
 * ^^ ^^^^
 * rabbbit
 * ^^^ ^^^
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: S = "babgbag", T = "bag"
 * Output: 5
 * Explanation:
 * 
 * As shown below, there are 5 ways you can generate "bag" from S.
 * (The caret symbol ^ means the chosen letters)
 * 
 * babgbag
 * ^^ ^
 * babgbag
 * ^^    ^
 * babgbag
 * ^    ^^
 * babgbag
 * ⁠  ^  ^^
 * babgbag
 * ⁠    ^^^
 * 
 * 
 */
class Solution {
    // https://www.youtube.com/watch?v=mPqqXh8XvWY
    // O(n^2) time, O(n^2)space;
    public int numDistinct(String s, String t) {
        int ls = s.length(), lt = t.length();

        // dp[i][j]表示t[0, i)与s[0, j)之间相等的subseq的个数;
        // 因为t是被match的，所以t的维度在前;
        int dp[][] = new int[lt + 1][ls + 1];

        // 初始化;
        // t中subseq长度为0,均可match;
        for (int i = 0; i <= ls; ++i)
            dp[0][i] = 1;

        // s中subseq长度为0，均不能match,除去t的subseq的长度为0的情况;
        for (int i = 1; i <= lt; ++i)
            dp[i][0] = 0;

        //注意这里t是被match的,所以要放在外层循环;
        for (int i = 1; i <= lt; ++i) {
            for (int j = 1; j <= ls; ++j) {
                if (t.charAt(i - 1) == s.charAt(j - 1))
                    // 若t[i-1]==s[j-1]
                    // dp[i][j]等于t[0， i-1)与s[0， j-1)之间的subseq的情况;
                    // 加上delete掉s[j-1], t[0, i)与s[0, j-1)之间的subseq的情况
                    dp[i][j] = dp[i - 1][j - 1] + dp[i][j - 1];
                else
                    //若t[i-1]!=s[j-1];
                    dp[i][j] = dp[i][j - 1];
            }
        }
        return dp[lt][ls];
    }
}
