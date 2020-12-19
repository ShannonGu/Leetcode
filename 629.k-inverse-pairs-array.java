/*
 * @lc app=leetcode id=629 lang=java
 *
 * [629] K Inverse Pairs Array
 */
class Solution {
    // https://leetcode.com/problems/k-inverse-pairs-array/solution/
    // mem[i][j]表示[1, i]内有j个inverse paris的arrays的个数
    // 那么如何求mem[i + 1][j]?
    // mem[i + 1][j]表示[1, i+1]中内有j个inverse paris的arrays的个数
    // 那么就是在1~i的数字中的某个位置加上i+1这个数字
    // 那么以i=5为例,就是在1~4中的加上5, 即如下情况
    // xxxx5, xxx5x, xx5xx, x5xxx, 5xxxx
    // 这里xxxx表示1~4的任意排列,那么第一种情况下xxxx5不会增加任何新的inverse pairs, 因为xxxx没有比5大的数字
    // xxx5x增加1个, xx5xx增加2个,x5xxx增加3个,5xxxx增加4个
    // 那么要求mem[5][j]就是要求mem[4][j],mem[4][j-1],mem[4][j-2],mem[4][j-3],mem[4][j-4]的情况总和
    // 其中mem[4][j]即相当于xxxx5,在xxxx的j个inverse pairs的基础上不增加inverse pairs

    // mem[4][j-1]相当于在xxxx有j-1个inverse pairs的情况中将5从最右边位置向左移动一个放置,
    // 即xxx5x，在j-1个inverse pairs的基础上增加一个,变成1~5有j个inverse pairs的情况

    // 剩下情况依次类推

    // 需要注意的是这里要比较j和i-1的大小,因为最多只能向左移动i-1个位置, 
    // 而且还要保证最终5向左移动的距离p满足j-p>=0,避免下标越界
    // 故0<=p<=Math.min(j, i - 1);

    // private int[][] mem;
    // private final int MOD = 1000000007;
    // public int kInversePairs(int n, int k) {
    //     mem = new int[n + 1][k + 1];
    //     for(int[] m : mem)
    //         Arrays.fill(m, -1);
    //     return helper(n, k);
    // }

    // private int helper(int n, int k) {
    //     // 0个数,只有0个inverse pairs
    //     if (n == 0)
    //         return 0;
    //     // 0个inverse pairs， 数组只能是递增的情况
    //     if (k == 0)
    //         return 1;
    //     if(mem[n][k] != -1)
    //         return mem[n][k];
    //     int res = 0;
    //     for (int i = 0; i <= Math.min(k, n - 1); ++i) {
    //         res = (res + helper(n - 1, k - i)) % MOD;
    //     }
    //     mem[n][k] = res;
    //     return res;
    // }

    // private final int MOD = 1000000007;

    // public int kInversePairs(int n, int k) {
    //     int[][] dp = new int[n + 1][k + 1];

    //     //dp[0][j]==0;
    //     for (int i = 1; i <= n; ++i) {
    //         for (int j = 0; j <= k; ++j) {
    //             if (j == 0)
    //                 dp[i][j] = 1;
    //             else {
    //                 for (int p = 0; p <= Math.min(j, i - 1); ++p) {
    //                     dp[i][j] = (dp[i][j] + dp[i - 1][j - p]) % MOD;
    //                 }
    //             }
    //         }
    //     }
    //     return dp[n][k];
    // }


    //优化
    //由上dp[n][k] =                  dp[n-1][k] + dp[n-1][k-1] + dp[n-1][k-2] + ... + dp[n-1][k-(n-1)];
    //则dp[n][k+1] = dp[n-1][k+1] +   dp[n-1][k+1-1] + dp[n-1][k+1-2] + dp[n-1][k+1-3] + ... + dp[n-1][k+1-(n-1)];
    //             = dp[n-1][k+1] +  dp[n-1][k] + dp[n-1][k-1] + dp[n-1][k-2] + ... + dp[n-1][k-(n-2)]; 
    //将两者相减的dp[n][k+1] - dp[n][k] = dp[n-1][k+1] - dp[n-1][k-(n-1)];
    //即dp[n][k+1] = dp[n][k] + dp[n-1][k+1] - dp[n-1][k+1-n];
    //将k代替k+1,得dp[n][k] = dp[n][k-1] + dp[n-1][k] - dp[n-1][k-n];
    //只有当k>=n时，dp[n-1][k-n]才需要计算
    //于是可以对上面的dp进行优化，省去第三重循环
    public int kInversePairs(int n, int k) {
        //dp[i][j] = dp[i][j-1] + dp[i-1][j] - dp[i-1][j-i]
        int[][] dp = new int[n + 1][k + 1];

        dp[0][0] = 1;

        //其他情况dp[0][j]==0;
        for (int i = 1; i <= n; ++i) {
            //0个inverse pairs只有array递增一种情况
            dp[i][0] = 1;
            for (int j = 1; j <= k; ++j) {
                //0个inverse pairs只有array递增一种情况
                //初始情况
                dp[i][j] = (dp[i][j - 1] + dp[i - 1][j]) % MOD;
                //只有当j>=i是才需要更新第三部分
                if (j >= i)
                    dp[i][j] = (dp[i][j] - dp[i - 1][j - i] + MOD) % MOD;

            }
        }
        return dp[n][k];
    }

    private final int MOD = 1000000007;
}
