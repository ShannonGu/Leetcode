/*
 * @lc app=leetcode id=639 lang=java
 *
 * [639] Decode Ways II
 */
class Solution {
    private final int MOD = 1000000007;

    public int numDecodings(String s) {
        int n = s.length();
        long[] dp = new long[n];

        // 判断首字符
        if (n >= 1 && s.charAt(0) == '0')
            return 0;

        for (int i = n - 1; i >= 0; --i) {
            // 当前字符为'0'
            if (s.charAt(i) == '0') {
                dp[i] = 0;
                continue;
            }
            // 考虑最后一个字符
            if (i == n - 1) {
                if (s.charAt(i) == '*')
                    dp[i] = 9;
                else
                    dp[i] = 1;
                continue;
            }

            // 将s[n-2]与其他字符区分开来
            if (i == n - 2) {
                // 若后一个字符是 '*'
                if (s.charAt(i + 1) == '*') {
                    // 初始情况是9
                    dp[i] = 9;

                    if (s.charAt(i) == '1')
                        // 当前字符为 '1'
                        // 可以组成9个<=26的两位数
                        dp[i] += 9;
                    else if (s.charAt(i) == '2')
                        // 当前字符为 '2'
                        // 可以组成6个<=26的两位数
                        dp[i] += 6;
                    else if (s.charAt(i) == '*')
                        // 当前字符也为 '*',即考虑'**'的情况
                        // 单独编码有9种情况和s[n-1]搭配
                        // 再考虑s[n-2, n-1]组合的情况
                        // 计算s[n-2]的'*'为 '1'和'2'的情况
                        dp[i] = 9 * dp[i + 1] + 9 + 6;
                    else
                        // s[n-1]不是 '1', '2'和'*'的情况
                        dp[i] = dp[i + 1];
                } else {
                    // 后一个字符不是 '*'
                    if (s.charAt(i) != '*') {
                        // 当前字符不是'*'
                        // 初始情况，当前字符单独编码
                        dp[i] = dp[i + 1];
                        // 考虑s[n-2, n-1]组合的情况
                        int num = (s.charAt(i) - '0') * 10 + (s.charAt(i + 1) - '0');
                        if (s.charAt(i) >= '1' && num <= 26)
                            dp[i] += 1;
                    } else {
                        // 当前字符为'*'
                        // 初始情况，单独编码有9种情况和后面字符搭配
                        dp[i] = 9 * dp[i + 1];
                        // 再考虑两个字符组合的情况
                        // '*'分别作为 '1'和 '2'的情况
                        if (s.charAt(i + 1) <= '6')
                            dp[i] += 2;
                        else
                            dp[i] += 1;
                    }
                }
            } else {
                // 不是s[n-2]的情况

                // 后一个字符是 '*'
                if (s.charAt(i + 1) == '*') {
                    if (s.charAt(i) == '1')
                        // 当前字符为 '1'
                        // 单独编码加上s[i, i+1]组合编码的情况
                        dp[i] = dp[i + 1] + 9 * dp[i + 2];
                    else if (s.charAt(i) == '2')
                        // 同理
                        dp[i] = dp[i + 1] + 6 * dp[i + 2];
                    else if (s.charAt(i) == '*')
                        //'**'的情况
                        //s[i]单独编码有9种和后面字符搭配
                        //再加上s[i,i+1]组合的情况
                        dp[i] = 9 * dp[i + 1] + (9 + 6) * dp[i + 2];
                    else
                        //当前不是 '1','2','*'
                        dp[i] = dp[i + 1];
                } else {
                    //后一个字符不是 '*'
                    if (s.charAt(i) != '*') {
                        //当前字符也不是 '*'
                        //初始情况，单独编码
                        dp[i] = dp[i + 1];

                        //考虑s[i,i+1]组合
                        int num = (s.charAt(i) - '0') * 10 + (s.charAt(i + 1) - '0');
                        if (s.charAt(i) >= '1' && num <= 26)
                            dp[i] += dp[i + 2];
                    } else {
                        //当前字符是 '*'
                        //初始情况，单独编码
                        dp[i] = 9 * dp[i + 1];
                        //再考虑s[i,i+1]组合,即'*'分别 '1','2'
                        if (s.charAt(i + 1) <= '6')
                            dp[i] += 2 * dp[i + 2];
                        else
                            dp[i] += dp[i + 2];
                    }
                }
            }
            dp[i] = (dp[i] + MOD) % MOD;
        }
        return (int) dp[0];
    }
}
