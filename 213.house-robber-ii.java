/*
 * @lc app=leetcode id=213 lang=java
 *
 * [213] House Robber II
 */
class Solution {
    // https://www.cnblogs.com/grandyang/p/4518674.html
    public int rob(int[] nums) {
        // 将首尾两个位置分别去掉，求出最大值
        // 再综合考虑
        if (nums.length <= 1)
            return nums.length == 0 ? 0 : nums[0];
            
        return Math.max(rob(nums, 0, nums.length - 1), rob(nums, 1, nums.length));

    }

    private int rob(int[] nums, int left, int right) {
        int rob = 0, notRob = 0;
        for (int i = left; i < right; ++i) {
            //前一天抢了和前一天没抢
            int preRob = rob, preNotRob = notRob;
            rob = preNotRob + nums[i];
            notRob = Math.max(preRob, preNotRob);
        }
        return Math.max(rob, notRob);
    }
}
