/*
 * @lc app=leetcode id=724 lang=java
 *
 * [724] Find Pivot Index
 */

// @lc code=start
class Solution {
    public int pivotIndex(int[] nums) {
        int sum = 0, leftSum = 0;
        for (int num : nums)
            sum += num;
        for (int i = 0; i < nums.length; ++i) {
            if (leftSum == sum - leftSum - nums[i])
                return i;
            leftSum += nums[i];
        }
        return -1;
    }
}
// @lc code=end
