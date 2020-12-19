/*
 * @lc app=leetcode id=152 lang=java
 *
 * [152] Maximum Product Subarray
 */
class Solution {
    //http://www.cnblogs.com/grandyang/p/4028713.html
    // public int maxProduct(int[] nums) {
    //     int n = nums.length;
    //     // mx[i]表示[0,i]范围内以nums[i]为结尾的子数组的最大乘积;
    //     // mn[i]表示[0,i]范围内以nums[i]为结尾的子数组的最小乘积;
    //     int[] mx = new int[n], mn = new int[n];

    //     mx[0] = mn[0] = nums[0];
    //     int res = nums[0];
    //     for (int i = 1; i < n; ++i) {
    //         // 因为负数的存在,最大值，最小值只会在
    //         // mx[i-1]*nums[i], mn[i-1]*nums[i]和nums[i]中产生;
    //         mx[i] = Math.max(nums[i], Math.max(mx[i - 1] * nums[i], mn[i - 1] * nums[i]));
    //         mn[i] = Math.min(nums[i], Math.min(mx[i - 1] * nums[i], mn[i - 1] * nums[i]));
    //         res = Math.max(res, mx[i]);
    //     }
    //     return res;
    // }

    public int maxProduct(int[] nums) {
        if(nums.length == 0)
            return 0;
        int n = nums.length;
        int mxVal = nums[0], mnVal = nums[0];
        int res = nums[0];
        for (int i = 1; i < n; ++i) {
            int tmp = mxVal;
            mxVal = Math.max(nums[i], Math.max(mxVal * nums[i], mnVal * nums[i]));
            mnVal = Math.min(nums[i], Math.min(tmp * nums[i], mnVal * nums[i]));
            res = Math.max(res, mxVal);
        }
        return res;
    }
}
