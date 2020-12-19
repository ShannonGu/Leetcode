/*
 * @lc app=leetcode id=665 lang=java
 *
 * [665] Non-decreasing Array
 */
class Solution {
    // https://www.cnblogs.com/grandyang/p/7565424.html
    public boolean checkPossibility(int[] nums) {
        
        //cnt表示最多只能修改一次
        int cnt = 1, n = nums.length;
        for (int i = 1; i < n; ++i) {
            //出现了一次递减的情况
            if (nums[i] < nums[i - 1]) {
                //如果此时不能修改了
                //说明整个数组不能改成非递减数组
                if (cnt == 0)
                    return false;
                //如果当前元素是第二个元素
                //或者>=nums[i-2]
                if (i == 1 || nums[i] >= nums[i - 2]) {
                    //为了尽量减少修改次数
                    //只能将数组前面的每个尽量减小
                    // nums[i - 1] = nums[i - 2];
                    nums[i - 1] = nums[i];
                } else {
                    //否则，为保持非递减
                    nums[i] = nums[i - 1];
                }
                //修改次数减少
                --cnt;
            }
        }
        return true;
    }
}
