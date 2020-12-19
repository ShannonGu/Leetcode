/*
 * @lc app=leetcode id=35 lang=java
 *
 * [35] Search Insert Position
 */
class Solution {
    public int searchInsert(int[] nums, int target) {
        if(nums.length == 0)
            return 0;
        int l = 0, r = nums.length;
        while (l < r) {
            int mid = l + ((r - l) >> 1);
            if (nums[mid] == target)
                return mid;
            else if (nums[mid] > target)
                r = mid;
            else
                l = mid + 1;
        }
        return r;
    }
}

