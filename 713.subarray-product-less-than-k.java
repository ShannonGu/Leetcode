/*
 * @lc app=leetcode id=713 lang=java
 *
 * [713] Subarray Product Less Than K
 */
class Solution {
    // https://www.cnblogs.com/grandyang/p/7753959.html
    public int numSubarrayProductLessThanK(int[] nums, int k) {
        // 维护一个乘积严格小于k的窗口
        if (nums.length == 0 || k <= 1)
            return 0;
        // left为窗口的左边界
        int res = 0, prod = 1, left = 0, n = nums.length;
        // i代表窗口的右边界
        for (int i = 0; i < n; ++i) {
            prod *= nums[i];
            // 使窗口内元素的乘积<k
            while (prod >= k) {
                prod /= nums[left];
                ++left;
            }

            // 计算以窗口右边界为结尾的子数组的个数
            res += i - left + 1;
        }
        return res;
    }
}
