/*
 * @lc app=leetcode id=34 lang=java
 *
 * [34] Find First and Last Position of Element in Sorted Array
 */
class Solution {
    // https://www.cnblogs.com/grandyang/p/4409379.html
    public int[] searchRange(int[] nums, int target) {
        int[] res = new int[] { -1, -1 };
        if (nums.length == 0)
            return res;
        int l = 0, r = nums.length - 1;
        // 先找到第一个不小于target的数
        while (l < r) {
            int mid = l + r >> 1;
            // 区间划分为[l, mid]或者[mid + 1, r];
            if (nums[mid] < target)
                l = mid + 1;
            else
                r = mid;
        }
        // 判断l是否越界或者是否存在target这个数
        if (l == nums.length || nums[l] != target)
            return res;
        res[0] = l;
        r = nums.length - 1;
        // 再找最后一个不大于target的数
        while (l < r) {
            int mid = l + r + 1 >> 1;
            // 区间划分为[l, mid - 1]或者[mid, r];
            if (nums[mid] <= target)
                l = mid;
            else
                r = mid - 1;
        }
        res[1] = l;
        return res;
    }
}
