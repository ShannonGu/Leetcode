/*
 * @lc app=leetcode id=153 lang=java
 *
 * [153] Find Minimum in Rotated Sorted Array
 */
class Solution {
    //https://www.cnblogs.com/grandyang/p/4032934.html
    // public int findMin(int[] nums) {
    //     int l = 0, r = nums.length - 1;
    //     int pos = 0;
    //     while (l < r) {
    //         int mid = l + ((r - l) >> 1);
    //         //右半部分有序
    //         if (nums[mid] < nums[r]) {
    //             if (mid == 0 || nums[mid - 1] > nums[mid]) {
    //                 pos = mid;
    //                 break;
    //             } else
    //                 r = mid;
    //         } else {
    //             //左半部分有序
    //             if (nums[mid] > nums[mid + 1]) {
    //                 pos = mid + 1;
    //                 break;
    //             } else
    //                 l = mid + 1;
    //         }
    //     }
    //     return nums[pos];
    // }


    public int findMin(int[] nums) {
        int l = 0, r = nums.length - 1;
        //先判断是不是整体有序
        if (nums[l] > nums[r]) {
            while (l < r) {
                int mid = l + r >> 1;
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

