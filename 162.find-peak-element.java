/*
 * @lc app=leetcode id=162 lang=java
 *
 * [162] Find Peak Element
 */
class Solution {
    public int findPeakElement(int[] nums) {
        if(nums.length == 1 || nums[0] > nums[1])
            return 0;
        
        //l从1开始，因为若nums[0]是一个peak element,则可以在前一步得出答案
        int l = 1, r = nums.length - 1;
        while (l < r) {
            int mid = l + r + 1 >> 1;
            if (nums[mid] > nums[mid - 1])
                l = mid;
            else
                r = mid - 1;
        }
        return l;
    }
}

