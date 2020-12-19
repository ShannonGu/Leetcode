/*
 * @lc app=leetcode id=486 lang=java
 *
 * [486] Predict the Winner
 */
class Solution {
    // http://zxi.mytechroad.com/blog/leetcode/leetcode-486-predict-the-winner/
    // mem[i][j]表示[i, j]范围内player1与player2取得的数的和最大的差值
    // private int[][] mem;

    // public boolean PredictTheWinner(int[] nums) {
    //     int n = nums.length;
    //     mem = new int[n][n];
    //     for (int[] m : mem) {
    //         Arrays.fill(m, Integer.MIN_VALUE);
    //     }
    //     // 判断player1取得数的总和是否比player2的大
    //     return getDiff(nums, 0, n - 1) >= 0;
    // }

    // // 用来计算[l, r]范围内player1和player2的取得数的和最大差值
    // private int getDiff(int[] nums, int l, int r) {
    //     if (l == r)
    //         return nums[l];
    //     if (mem[l][r] != Integer.MIN_VALUE)
    //         return mem[l][r];
    //     // player1取左右两个端点的情况的比较
    //     mem[l][r] = Math.max(nums[l] - getDiff(nums, l + 1, r), nums[r] - getDiff(nums, l, r - 1));
    //     return mem[l][r];
    // }




    public boolean PredictTheWinner(int[] nums) {
        int n = nums.length;
        //dp[i][j]表示[i, j]范围内p1与p2取得数的和的最大差值
        int[][] dp = new int[n][n];
        for(int[] e : dp)
            Arrays.fill(e, Integer.MIN_VALUE);
        for(int i = 0; i < n; ++i)
            dp[i][i] = nums[i];
            
        for (int len = 2; len <= n; ++len) {
            for (int i = 0; i <= n - len; ++i) {
                int j = i + len - 1;
                dp[i][j] = Math.max(nums[i] - dp[i + 1][j], nums[j] - dp[i][j - 1]);
            }
        }
        return dp[0][n - 1] >= 0;
    }
}
