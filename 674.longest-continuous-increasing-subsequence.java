/*
 * @lc app=leetcode id=674 lang=java
 *
 * [674] Longest Continuous Increasing Subsequence
 */
class Solution {
    // public int findLengthOfLCIS(int[] nums) {
    //     int n = nums.length;
    //     if (n == 0)
    //         return 0;
    //     int[] dp = new int[n];
    //     int res = 1;
    //     dp[0] = 1;
    //     for (int i = 1; i < n; ++i) {
    //         if (nums[i] > nums[i - 1])
    //             dp[i] = dp[i - 1] + 1;
    //         else
    //             dp[i] = 1;
    //         res = Math.max(res, dp[i]);
    //     }
    //     return res;
    // }
    
    public int findLengthOfLCIS(int[] nums) {
        if(nums.length == 0)
            return 0;
        int n = nums.length;
        int dp = 1, res = 1;
        for (int i = 1; i < n; ++i) {
            if (nums[i] > nums[i - 1])
                dp++;
            else
                dp = 1;
            res = Math.max(res, dp);
        }
        return res;
    }
}

