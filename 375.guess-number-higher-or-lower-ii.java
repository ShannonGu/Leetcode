/*
 * @lc app=leetcode id=375 lang=java
 *
 * [375] Guess Number Higher or Lower II
 */
class Solution {
    // https://www.cnblogs.com/grandyang/p/5677550.html
    // https://www.hrwhisper.me/leetcode-guess-number-higher-lower-ii/
    // public int getMoneyAmount(int n) {
    //     // 题意是在猜测数字y未知的情况下(1~n中的任意一个数)，
    //     // 求在最坏情况下支付最少的钱;
    //     // 也就是说要考虑所有y的情况;

    //     // 假设选择了一个错误的数x,(1<=x<=n && x!=y),那么就知道接下来应该从[1,x-1]或者[x+1,n]
    //     // 中进行查找；假设已经解决了[1,x-1]和[x+1,n]计算问题,将其表示为solve(1, x-1)和solve(x+1, n);
    //     // 那么应该选择max(solve(1, x-1),solve(x+1,n))，这就是求最坏情况下的损失;
    //     // 总的损失就是f(x)=x+max(solve(1,x-1),solve(x+1,n));
    //     // 那么将x从1~n进行遍历，取是的f(x)达到最小，来确定最坏情况下最小的损失;

    //     // 这是一个(自顶向下)top-down的过程,可以用递归解决;

    //     int[][] dp = new int[n + 1][n + 1];
    //     return solve(dp, 1, n);

    // }

    // // 记忆数组memo+递归;
    // private int solve(int[][] dp, int L, int R) {
    //     if (L >= R)
    //         return 0;
    //     // 说明[L,R]的情况已经出现过;
    //     if (dp[L][R] != 0)
    //         return dp[L][R];
    //     dp[L][R] = Integer.MAX_VALUE;
    //     // 求遍历[L,R]每个数，求最坏情况的全局最小值;
    //     for (int i = L; i <= R; ++i) {
    //         int tmp = i + Math.max(solve(dp, L, i - 1), solve(dp, i + 1, R)));
    //         dp[L][R] = Math.min(dp[L][R], tmp);
    //     }
    //     return dp[L][R];
    // }

    //DP;
    // public int getMoneyAmount(int n) {
    //     //dp[i][j]表示[i~j]中最坏情况下的最小需要支付多少钱;
    //     int[][] dp = new int[n + 1][n + 1];

    //     //先确定左右边界;
    //     //[L,R]中至少有两个数,因为只有一个数就不需要猜了;
    //     for (int R = 2; R <= n; ++R) {
    //         //L最小为1;
    //         for (int L = R - 1; L > 0; --L) {
    //             int global_min = Integer.MAX_VALUE;
    //             // k在(L,R)中遍历,取[L,R]中最坏情况下的全局最小值;
    //             // 注意这里k!=L,!=R,
    //             // 例如在[1,4]中,k分别取2,3，所需的cost为2+3=5,3+1=4;
    //             // 取k=1or4,cost>=最小cost;
    //             for (int k = L + 1; k < R; ++k) {
    //                 int local_max = k + Math.max(dp[L][k - 1], dp[k + 1][R]);
    //                 global_min = Math.min(global_min, local_max);
    //             }
    //             //[L,R]中仅有两个数;
    //             //则选择较小的数;
    //             if(L+1 == R)
    //                 dp[L][R] = L;
    //             else
    //                 dp[L][R] = global_min;
    //         }
    //     }
    //     return dp[1][n];
    // }


    public int getMoneyAmount(int n) {
        //dp[i][j]表示区间[i,j]中最坏情况下的最小损失
        //轮流遍历[i, j]中的每一个数,确定了其中的一个数k后, 在此情况下能够保证win的情况是k+max(dp[i, k-1], dp[k+1][j])
        //再对[i,j]中每一个位置保证win的情况的最大损失值取最小,即可得到最坏情况下的最小损失
        int[][] dp = new int[n + 1][n + 1];
        //先确定左边界
        for (int L = n - 1; L > 0; --L) {
            //右边界，区间[L,R]中至少有两个数，否则损失为0，因为只有一个数不需要猜
            for (int R = L + 1; R <= n; ++R) {
                dp[L][R] = Integer.MAX_VALUE;
                //在[L,R]中遍历每次首先猜的数
                for (int k = L; k < R; ++k) {
                    dp[L][R] = Math.min(dp[L][R], k + Math.max(dp[L][k - 1], dp[k + 1][R]));
                }
            }
        }
        return dp[1][n];
    }
}
