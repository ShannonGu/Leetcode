/*
 * @lc app=leetcode id=31 lang=java
 *
 * [31] Next Permutation
 */

// @lc code=start
class Solution {
    // https://www.cnblogs.com/grandyang/p/4428207.html
    public void nextPermutation(int[] nums) {
        int n = nums.length, i = n - 2, j = n - 1;
        // 如果数组递减，说明已经最大数了
        // 下一个数就是递增数组组成的数
        // 首先从后往前遍历，遍历到后面一个数大于前面一个数时停止
        while (i >= 0 && nums[i] >= nums[i + 1])
            --i;
        // 然后在从后往前遍历，找到第一个大于nums[i]的数
        // 将二者置换
        if (i >= 0) {
            while (nums[j] <= nums[i])
                --j;
            int tmp = nums[i];
            nums[i] = nums[j];
            nums[j] = tmp;
        }

        // 然后再将nums[i]后面的数逆置
        int l = i + 1, r = n - 1;
        while (l < r) {
            int tmp = nums[l];
            nums[l] = nums[r];
            nums[r] = tmp;
            ++l;
            --r;
        }
    }
}
// @lc code=end
