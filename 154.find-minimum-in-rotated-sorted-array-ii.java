/*
 * @lc app=leetcode id=154 lang=java
 *
 * [154] Find Minimum in Rotated Sorted Array II
 */
class Solution {
    public int findMin(int[] nums) {
        int l = 0, r = nums.length - 1;
        while (l < r) {
            int mid = l + r >> 1;
            
            if (nums[mid] < nums[r])
            //右半部分[mid, r]有序,并且mid可能是最终结果
                r = mid;
            else if (nums[mid] == nums[r])
            //有重复元素,需要左移右边界
                --r;
            else
            //mid在左侧递增部分,且[l, mid]部分有序
                l = mid + 1;
        }
        return nums[r];
    }
}

