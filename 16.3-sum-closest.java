import java.util.Arrays;

/*
 * @lc app=leetcode id=16 lang=java
 *
 * [16] 3Sum Closest
 */
class Solution {
    public int threeSumClosest(int[] nums, int target) {
        if (nums.length < 3)
            return -1;
        Arrays.sort(nums);
        int delta = Integer.MAX_VALUE, res = 0, n = nums.length;
        for (int i = 0; i < n; ++i) {
            int l = i + 1, r = n - 1;
            while (l < r) {
                int sum = nums[i] + nums[l] + nums[r];
                if (sum == target)
                    return target;

                int diff = Math.abs(sum - target);
                // 当前三个数的和距离target更近
                // 则更新
                if (diff < delta) {
                    delta = diff;
                    res = sum;
                }

                // 继续寻找距离target的和
                if (sum < target)
                    ++l;
                else
                    --r;
            }
        }
        return res;
    }
}
