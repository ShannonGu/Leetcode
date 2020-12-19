import java.util.Collections;

/*
 * @lc app=leetcode id=312 lang=java
 *
 * [312] Burst Balloons
 */
class Solution {
    // http://zxi.mytechroad.com/blog/dynamic-programming/leetcode-312-burst-balloons/
    // https://www.hrwhisper.me/leetcode-burst-balloons/
    // public int maxCoins(int[] nums) {
    //     if (nums.length == 0)
    //         return 0;
    //     int len = nums.length;
    //     int[] newNums = new int[len + 2];
    //     //dp[i][j]表示nums[i, j]戳破所有气球所能得到的最大奖励
    //     int[][] dp = new int[len + 2][len + 2];
    //     for (int i = 0; i < len; ++i)
    //         newNums[i + 1] = nums[i];
    //     newNums[0] = newNums[len + 1] = 1;

    //     //在[i, j]之间选择一个位置k保留不戳破，然后戳破[i, k - 1], [k+1, j]之间的气球
    //     //最后戳破气球k

    //     //区间[i,j]的长度
    //     for (int l = 1; l <= len; ++l) {
    //         //区间起点
    //         for (int i = 1; i <= len - l + 1; ++i) {
    //             //区间终点
    //             int j = i + l - 1;
    //             //遍历区间中的每个位置k
    //             for (int k = i; k <= j; ++k) {
    //                 //dp[i][k-1]为[i,k-1]的最大奖励
    //                 //dp[k+1][j]为[k+1, j]的最大奖励
    //                 //最后戳破k,得到的奖励为nums[i-1]*nums[k]*nums[j+1];
    //                 dp[i][j] = Math.max(dp[i][j],
    //                         dp[i][k - 1] + newNums[i - 1] * newNums[k] * newNums[j + 1] + dp[k + 1][j]);
    //             }
    //         }
    //     }
    //     return dp[1][len];
    // }



    //memo[i][j]表示戳破[i,j]之间的气球所能获得最大奖励
    private int[][] memo;
    private int[] vals;

    public int maxCoins(int[] nums) {
        int n = nums.length;
        vals = new int[n + 2];
        for (int i = 0; i < n; ++i)
            vals[i + 1] = nums[i];
        vals[0] = vals[n + 1] = 1;
        memo = new int[n + 2][n + 2];
        return helper(1, n);
    }
    
    private int helper(int i, int j) {
        if(i > j)
            return 0;
        if(i == j)
            return vals[i - 1] * vals[i] * vals[i + 1];
        if(memo[i][j] != 0)
            return memo[i][j];
        int res = 0;
        //保留位置k的气球不戳破，然后戳破[i,k-1]和[k+1, j]之间的气球
        for (int k = i; k <= j; ++k) {
            res = Math.max(res, helper(i, k - 1) + vals[i - 1] * vals[k] * vals[j + 1] + helper(k + 1, j));
        }
        memo[i][j] = res;
        return res;
    }
}
