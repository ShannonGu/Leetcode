/*
 * @lc app=leetcode id=11 lang=java
 *
 * [11] Container With Most Water
 */

// @lc code=start
class Solution {
    public int maxArea(int[] height) {
        int res = 0;
        int i = 0, j = height.length - 1;
        while (i < j) {
            int h = Math.min(height[i], height[j]);
            res = Math.max(res, h * (j - i));
            while (i < j && h == height[i])
                ++i;
            while (i < j && h == height[j])
                --j;
        }
        return res; 
    }
}
// @lc code=end

