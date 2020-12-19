/*
 * @lc app=leetcode id=34 lang=java
 *
 * [34] Find First and Last Position of Element in Sorted Array
 */
class Solution {
    //https://www.cnblogs.com/grandyang/p/4409379.html

    public int[] searchRange(int[] nums, int target) {
        int[] res = new int[] { -1, -1 };
        if (nums.length == 0)
            return res;

        int l = 0, r = nums.length - 1;
        while (l < r) {
            int mid = l + r >> 1;
            if (nums[mid] < target)
                l = mid + 1;
            else
                r = mid;
        }
        //注意l越界的问题，若{3,4}, target=5,不存在target,此时l=2,下标越界
        if (l == nums.length || nums[l] != target)
            return res;
        res[0] = l;
        r = nums.length - 1;
        while (l < r) {
            int mid = l + r + 1 >> 1;
            if (nums[mid] <= target)
                l = mid;
            else
                r = mid - 1;
        }
        res[1] = l;
        return res;
    }
}

