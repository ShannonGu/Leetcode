/*
 * @lc app=leetcode id=33 lang=java
 *
 * [33] Search in Rotated Sorted Array
 */
class Solution {
    public int search(int[] nums, int target) {
        if (nums.length == 0)
            return -1;
        int l = 0, r = nums.length - 1;
        while (l < r) {
            int mid = l + r >> 1;
            //mid在右半部分
            if (nums[mid] < nums[r]) {
                //target也处于右半部分
                if (nums[mid] < target && nums[r] >= target)
                    l = mid + 1;
                else
                    r = mid;
            } else {
                //mid在左半部分
                //target也处于左半部分
                if (nums[mid] >= target && nums[l] <= target)
                    r = mid;
                else
                    l = mid + 1;
            }
        }
        return nums[l] == target ? l : -1;
    }
}
