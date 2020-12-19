/*
 * @lc app=leetcode id=55 lang=java
 *
 * [55] Jump Game
 */
class Solution {
    public boolean canJump(int[] nums) {
        //reach表示最远能到达的位置
        int len = nums.length, reach = 0;
        for (int i = 0; i < len; ++i) {

            //当前位置超出了最远能到达的位置或reach已经覆盖了所有位置
            if (i > reach || reach >= len - 1) break;

            //i + nums[i]表示当前位置能到达的最远位置
            reach = Math.max(reach, i + nums[i]);
        }
        return reach >= len - 1;
    }
}

