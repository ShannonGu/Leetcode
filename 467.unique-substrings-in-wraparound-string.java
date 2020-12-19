/*
 * @lc app=leetcode id=467 lang=java
 *
 * [467] Unique Substrings in Wraparound String
 */
class Solution {
    // https://www.cnblogs.com/grandyang/p/6143071.html
    public int findSubstringInWraproundString(String p) {
        // 观察发现s是26个英文字母的无限循环

        // dp[i]表示以i+'a'结尾的在s中出现的最长字符串的长度
        // 比如abcd, bcd, cd, d,如果我们知道abcd存在，说明bcd,cd, d也存在
        // 这样以d结尾的出现在s中的子串的个数就知道了
        int[] dp = new int[26];
        int len = 0;
        for (int i = 0; i < p.length(); ++i) {
            // 判断是否连续的字符串,由于s无限循环的,可能出现'...za..'相连的情况,
            // 所以也要判断p是否出现这样的情况
            if (i > 0 && (p.charAt(i) == p.charAt(i - 1) + 1 || p.charAt(i - 1) - p.charAt(i) == 25)) {
                ++len;
            } else
                // 即是单个字符
                len = 1;
            // 更新以p.charAt(i)结尾的字符串的长度
            dp[p.charAt(i) - 'a'] = Math.max(dp[p.charAt(i) - 'a'], len);
        }

        // 累加每个字符结尾的字符串的长度，即以该字符结尾的子串的个数
        int sum = 0;
        for (int i = 0; i < 26; ++i) {
            sum += dp[i];
        }
        return sum;
    }
}
