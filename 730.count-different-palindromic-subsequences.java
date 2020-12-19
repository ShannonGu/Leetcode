import java.util.Set;

/*
 * @lc app=leetcode id=730 lang=java
 *
 * [730] Count Different Palindromic Subsequences
 */
class Solution {
    //http://zxi.mytechroad.com/blog/dynamic-programming/leetcode-730-count-different-palindromic-subsequences/
    // public int countPalindromicSubsequences(String S) {
    //     int n = S.length();
    //     //mem_[i][j]表示[i,j]之间palindromic subseq的个数
    //     mem_ = new int[n][n];
    //     return count(S.toCharArray(), 0, n - 1);
    // }

    // private int count(char[] s, int i, int j) {
    //     if (i > j)
    //         return 0;
    //     if (i == j)
    //         return 1;
    //     if (mem_[i][j] > 0)
    //         return mem_[i][j];

    //     long ans = 0;
    //     //两边相等
    //     if (s[i] == s[j]) {
    //         // base case
    //         //如"bccb", 2 * count("cc");
    //         //子问题"cc", 有 "c", "cc"两种情况，求"bccb"时，可以在子问题 "cc"的两边套上b
    //         //"bcb", "bccb"，于是有 "c", "cc", "bcb", "bccb"共4种,这是基本情况
    //         ans += count(s, i + 1, j - 1) * 2;

    //         //接着寻找子问题内部是否有与边界相同的字母
    //         int l = i + 1, r = j - 1;
    //         while (l <= r && s[l] != s[i])
    //             ++l;
    //         while (l <= r && s[r] != s[i])
    //             --r;
            
    //         if (l > r)
    //             //没找到
    //             //再加上"b","bb"
            
    //             ans += 2;
    //         else if (l == r)
    //             //找到一个
    //             //有重复情况,如"bcbcb", 子问题"cbc"的解有, "b","cbc","c","cc"
    //             //于是再加上的两边字母的情况时，会有重复的"b", 所以只能加上"bb" 
    //             ans += 1;
    //         else
    //             //有多个
    //             //"bbcabb", 子问题"bcab", 有"c","a","bcb","bab","b","bb"
    //             //往两边套上边界字母时，对"c","a"套上边界字母，与子问题产生重复
    //             //所以要减去"ca"的情况
    //             ans -= count(s, l + 1, r - 1);
    //     } else {
    //         //两边字母不一样
    //         //于是计算count[i, j-1] + count[i+1, j] - count[i+1,j-1];
    //         ans = count(s, i, j - 1) + count(s, i + 1, j) - count(s, i + 1, j - 1);
    //     }

    //     mem_[i][j] = (int) ((ans + kMod) % kMod);
    //     return mem_[i][j];
    // }

    // private int[][] mem_;
    // private static final int kMod = 1000000007;

    

    //DP
    private static final int kMod = 1000000007;
    public int countPalindromicSubsequences(String S) {
        int n = S.length();
        long[][] dp = new long[n][n];
        for(int i = 0; i < n; ++i)
            dp[i][i] = 1;
        
        for (int len = 1; len <= n; ++len) {
            //i < n - len, 保证至少两个字符
            for (int i = 0; i < n - len; ++i) {
                //至少两个字符
                int j = i + len;
                if (S.charAt(i) == S.charAt(j)) {
                    dp[i][j] = 2 * dp[i + 1][j - 1];
                    int l = i + 1, r = j - 1;
                    while (l <= r && S.charAt(l) != S.charAt(i))
                        ++l;
                    while (l <= r && S.charAt(r) != S.charAt(i))
                        --r;
                    if (l > r)
                        dp[i][j] += 2;
                    else if (l == r)
                        dp[i][j] += 1;
                    else
                        dp[i][j] -= dp[l + 1][r - 1];
                } else {
                    dp[i][j] = dp[i + 1][j] + dp[i][j - 1] - dp[i + 1][j - 1];
                }
                dp[i][j] = (int)((dp[i][j] + kMod) % kMod);
            }
        }
        return (int)dp[0][n - 1];
    }
}

