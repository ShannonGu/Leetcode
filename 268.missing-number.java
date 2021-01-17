/*
 * @lc app=leetcode id=268 lang=java
 *
 * [268] Missing Number
 */

// @lc code=start
class Solution {
    public int missingNumber(int[] nums) {
        int n = nums.length;
        int[] newNums = new int[n + 1];
        for (int i = 0; i < n; i++) {
            newNums[i] = nums[i];
        }
        newNums[n] = -1;
        for (int i = 0; i <= n; i++) {
            while (newNums[i] != i && newNums[i] != -1) {
                swap(newNums, i, newNums[i]);
            }
        }

        for (int i = 0; i <= n; i++) {
            if (newNums[i] != i)
                return i;
        }
        return -1;
    }

    private void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }
}
// @lc code=end