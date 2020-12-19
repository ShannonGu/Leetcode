/*
 * @lc app=leetcode id=198 lang=java
 *
 * [198] House Robber
 */
class Solution {
    public int rob(int[] nums) {
        // int n = nums.length;
        // if(n == 0)
        //     return 0;
        // int dp0 = 0, dp1 = nums[0];
        // for (int i = 1; i < n; ++i) {
        //     int dp2 = Math.max(dp1, dp0 + nums[i]);
        //     dp0 = dp1;
        //     dp1 = dp2;
        // }
        // return dp1;

        int n = nums.length;
        if(n < 0)
            return 0;
        int rob = 0, notRob = 0;
        for (int i = 0; i < n; ++i) {
            int preRob = rob, preNotRob = notRob;
            rob = notRob + nums[i];
            notRob = Math.max(preRob, preNotRob);
        }
        return Math.max(rob, notRob);
    }
}

