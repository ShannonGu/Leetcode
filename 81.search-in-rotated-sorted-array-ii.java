/*
 * @lc app=leetcode id=81 lang=java
 *
 * [81] Search in Rotated Sorted Array II
 */

// @lc code=start
class Solution {
    public boolean search(int[] nums, int target) {
        if (nums.length == 0)
            return false;
            
        int l = 0, r = nums.length - 1;
        while (l < r) {
            int mid = l + r >> 1;
            if (nums[mid] < nums[r]) {
                if (nums[mid] < target && nums[r] >= target)
                    l = mid + 1;
                else
                    r = mid;
            } else if (nums[mid] > nums[r]) {
                if (nums[mid] >= target && nums[l] <= target)
                    r = mid;
                else
                    l = mid + 1;
            } else
                // nums[mid] == nums[r]
                --r;
        }
        return nums[l] == target;
    }
}
// @lc code=end
