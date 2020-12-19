/*
 * @lc app=leetcode id=26 lang=java
 *
 * [26] Remove Duplicates from Sorted Array
 */
class Solution {
    public int removeDuplicates(int[] nums) {
        if(nums.length < 2)
            return nums.length;
        int i = 0, j = 1;
        while (j < nums.length) {
            while (j < nums.length && nums[i] == nums[j])
                ++j;
            if (j < nums.length && nums[i] != nums[j]) {
                nums[++i] = nums[j++];
            }
        }
        return i + 1;
    }
}

