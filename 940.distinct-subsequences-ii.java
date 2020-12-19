/*
 * @lc app=leetcode id=940 lang=java
 *
 * [940] Distinct Subsequences II
 */
class Solution {
    //https://leetcode.com/problems/distinct-subsequences-ii/solution/
    private final int MOD = 1000000007;
    public int distinctSubseqII(String S) {
        int n = S.length();
        //dp[i]表示以s[i-1]为结尾的distinct subsequence个数
        int[] dp = new int[n + 1];
        //""也算一个
        dp[0] = 1;

        //last[i]表示字符('a' + i)上一次出现的位置
        int[] last = new int[26];
        //初始化last;
        Arrays.fill(last, -1);
        for (int i = 0; i < n; ++i) {
            int x = S.charAt(i) - 'a';
            //首先是以上个字符为结尾的个数的两倍
            dp[i + 1] = (2 * dp[i]) % MOD;
            //若该字符曾经出现过
            //说存在重复情况
            //需要减去重复情况,就是上一次该字符出现时的位置的情况
            if (last[x] >= 0)
                dp[i + 1] -= dp[last[x]];
            dp[i + 1] %= MOD;
            last[x] = i;
        }
        
        //最后还要减去""的情况
        dp[n]--;
        //情况太多，超过了整型数最大值，需要加上MOD
        if(dp[n] < 0 )
            return dp[n] + MOD;
        return dp[n];

    }
}

