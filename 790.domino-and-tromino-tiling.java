/*
 * @lc app=leetcode id=790 lang=java
 *
 * [790] Domino and Tromino Tiling
 */
class Solution {
    //http://zxi.mytechroad.com/blog/dynamic-programming/leetcode-790-domino-and-tromino-tiling/
    public int numTilings(int N) {
        long kMod = 1000000007;
        if (N == 0)
            return 0; 

        //dp[i][0]表示当cover i列时，第i列的两行都被cover了
        //dp[i][1]表示当cover i列时，第i列的上面一行被cover了
        //dp[i][2]表示当cover i列时，第i列的下面一行被cover了
        long[][] dp = new long[N + 1][3];

        
        dp[0][0] = dp[1][0] = 1;
        for (int i = 2; i <= N; ++i) {
            //dp[i][0]由四种情况组成
            //1是前i-1列完全被cover，再在第i列用一个domino竖着cover
            //2是前i-2列完全被cover, 再在第i-1, 第i列用两个domino横着cover
            //3是前i-1列以dp[i-1][1] cover结尾,再用一个tromino补满i列
            //4是前i-1列以dp[i-1][2] cover结尾,再用一个tromino补满i列
            //可以得出dp[i-1][1]和dp[i-1][2]是对称的两种情况;
            dp[i][0] = (dp[i - 1][0] + dp[i - 2][0] + dp[i - 1][1] + dp[i - 1][2]) % kMod;

            //dp[i][1]由两种情况组成
            //1是前i-2列完全被cover, 再用一个tromino cover
            //2是前i-1列以dp[i-1][2] cover结尾, 再用一个domino在上面一行横着cover 
            dp[i][1] = (dp[i - 2][0] + dp[i - 1][2]) % kMod;

            //同dp[i][1]对称,dp[i][2]也由两种情况组成
            //1是前i-2列完全被cover, 再用一个tromino cover
            //2是前i-1列以dp[i-1][1] cover结尾， 再用一个domino在下面一行横着cover
            dp[i][2] = (dp[i - 2][0] + dp[i - 1][1]) % kMod;
        }
        return (int) dp[N][0];
    }
    
    // //对上面解法的优化
    // public int numTilings(int N) {
    //     long kMod = 1000000007;
    //     long[][] dp = new long[N + 1][2];
    //     dp[0][0] = dp[1][0] = 1;
    //     //由于dp[i][1]总是==dp[i][2]
    //     //于是上面解法的循环中dp[i - 1][1] + dp[i-1][2] == 2 * dp[i-1][1];
    //     //dp[i][1]和dp[i][2]可以只保留一个;
    //     for (int i = 2; i <= N; ++i) {
    //         dp[i][0] = (dp[i - 1][0] + dp[i - 2][0] + 2 * dp[i - 1][1]) % kMod;
    //         dp[i][1] = (dp[i - 2][0] + dp[i - 1][1]) % kMod;
    //     }
    //     return (int) dp[N][0];
    // }
}

