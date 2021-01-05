/*
 * @lc app=leetcode id=91 lang=java
 *
 * [91] Decode Ways
 */
class Solution {
    public int numDecodings(String s) {
        int n = s.length();

        // 若s是以 '0'为开头的，则不能编码
        if (n >= 1 && s.charAt(0) == '0')
            return 0;

        // dp[i]表示s[i, n-1]以s[i]为开头的编码方式
        int[] dp = new int[n];
        for (int i = n - 1; i >= 0; --i) {
            // s[i]=='0',不能编码
            if (s.charAt(i) == '0') {
                dp[i] = 0;
                continue;
            }

            // i为最后一个字符时,且不是 '0',为1
            if (i == n - 1) {
                dp[i] = 1;
                continue;
            }

            // 初始情况是s[i]单独编码的情况
            dp[i] = dp[i + 1];
            // 计算两位组合的情况
            int num = (s.charAt(i) - '0') * 10 + (s.charAt(i + 1) - '0');
            // i == n-2时，考虑s[n-2,n-1]是否<=26
            if (i == n - 2) {
                if (s.charAt(i) >= '1' && num <= 26)
                    dp[i] += 1;
            } else if (i < n - 2) {
                // i<n-2，考虑s[i,i+1]是否<=26
                // 若是，加上将s[i,i+1]组合后的情况
                if (s.charAt(i) >= '1' && num <= 26)
                    dp[i] += dp[i + 2];
            }
        }
        // 返回将s[0,n-1]编码的所有情况
        return dp[0];
    }
}
