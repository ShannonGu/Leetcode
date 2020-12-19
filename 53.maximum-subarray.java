/*
 * @lc app=leetcode id=53 lang=java
 *
 * [53] Maximum Subarray
 */
class Solution {
    // public int maxSubArray(int[] nums) {
    //     int res = Integer.MIN_VALUE, sum = 0;
    //     for (int i = 0; i < nums.length; ++i) {
    //         if (sum < 0)
    //             sum = nums[i];
    //         else
    //             sum += nums[i];
    //         res = Math.max(res, sum);
    //     }
    //     return res;
    // }




    public int maxSubArray(int[] nums) {
        if(nums.length == 0)
            return 0;
        
        return helper(nums, 0, nums.length - 1);
    }
    
    private int helper(int[] nums, int l, int r) {

        if(l >= r)
            return nums[l];
        
        int m = l + r >> 1;
        int lmax = helper(nums, l, m - 1);
        int rmax = helper(nums, m + 1, r);
        int mmax = nums[m], sum = mmax;

        for (int i = m - 1; i >= l; --i) {
            sum += nums[i];
            mmax = Math.max(mmax, sum);
        }
        
        sum = mmax;
        for (int i = m + 1; i <= r; ++i) {
            sum += nums[i];
            mmax = Math.max(mmax, sum);
        }
        
        return Math.max(mmax, Math.max(lmax, rmax));
    }
}

