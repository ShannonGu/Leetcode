/*
 * @lc app=leetcode id=645 lang=java
 *
 * [645] Set Mismatch
 */
class Solution {
    // public int[] findErrorNums(int[] nums) {
    //     int len = nums.length;
    //     if(len == 0)
    //         return new int[] {};
    //     int[] res = new int[2];
    //     for (int i = 0; i < len; ++i) {
    //         while (nums[i] != i + 1) {
    //             if (nums[i] == nums[nums[i] - 1]) {
    //                 res[0] = nums[i];
    //                 break;
    //             }
    //             int tmp = nums[nums[i] - 1];
    //             nums[nums[i] - 1] = nums[i];
    //             nums[i] = tmp;
    //         }
    //     }

    //     for (int i = 0; i < len; ++i) {
    //         if(nums[i] != i + 1)
    //             res[1] = i + 1;
    //     }
    //     return res;
    // }

    public int[] findErrorNums(int[] nums) {
        int len = nums.length;
        if(len == 0)
            return new int[] {};
        int[] res = new int[2];
        //遍历每个元素，将该元素应该出现的位置上的数变为其相反数
        //若遍历到某个位置的元素时，该元素应该出现的位置上的数已经是负数了，
        //说明该数绝对值即重复数
        for (int num : nums) {
            if (nums[Math.abs(num) - 1] < 0)
                res[0] = Math.abs(num);
            else
                nums[Math.abs(num) - 1] *= -1;
        }
        
        for (int i = 0; i < len; ++i) {
            if (nums[i] > 0)
                res[1] = i + 1;
        }
        return res;
    }
}

