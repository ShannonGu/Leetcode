/*
 * @lc app=leetcode id=153 lang=java
 *
 * [153] Find Minimum in Rotated Sorted Array
 */
class Solution {

    public int findMin(int[] nums) {
        int l = 0, r = nums.length - 1;
        // 先判断是否整体有序
        if (nums[l] > nums[r]) {
            while (l < r) {
                int mid = l + r >> 1;
                // [mid+1, r]部分有序，答案在[l, mid]中,
                // 划分为[l, mid]和[mid+1, r]
                if (nums[mid] < nums[r])
                    r = mid;
                else
                    l = mid + 1;
            }
            return nums[l];
        }
        return nums[0];
    }
}
