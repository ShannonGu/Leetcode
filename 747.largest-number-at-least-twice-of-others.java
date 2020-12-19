/*
 * @lc app=leetcode id=747 lang=java
 *
 * [747] Largest Number At Least Twice of Others
 */

// @lc code=start
class Solution {
    public int dominantIndex(int[] nums) {
        if(nums.length == 0)
            return -1;

        //取得数组中第一大和第二大的数，比较两者即可
        int firMx = nums[0], secMx = Integer.MIN_VALUE;
        int idx = 0;
        for (int i = 1; i < nums.length; ++i) {
            if (nums[i] > secMx) {
                if (nums[i] > firMx) {
                    secMx = firMx;
                    firMx = nums[i];
                    idx = i;
                }else
                    secMx = nums[i];
            }
        }
        return firMx >= 2 * secMx ? idx : -1;
    }
}
// @lc code=end

