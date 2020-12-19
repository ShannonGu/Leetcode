/*
 * @lc app=leetcode id=628 lang=java
 *
 * [628] Maximum Product of Three Numbers
 */
class Solution {
    private static final int MN = Integer.MIN_VALUE, MX = Integer.MAX_VALUE;
    public int maximumProduct(int[] nums) {
        //要么三个都是正数，要么是一个最大的正数，两个是最小的负数
        if(nums.length < 3)
            return 0;
        int mx1 = MN, mx2 = MN, mx3 = MN;
        int mn1 = MX, mn2 = MX;
        for (int num : nums) {
            if (num > mx1) {
                mx3 = mx2;
                mx2 = mx1;
                mx1 = num;
            } else if (num > mx2) {
                mx3 = mx2;
                mx2 = num;
            } else if (num > mx3)
                mx3 = num;

            if (num < mn1) {
                mn2 = mn1;
                mn1 = num;
            } else if (num < mn2) {
                mn2 = num;
            }
        }
        return Math.max(mx1 * mx2 * mx3, mx1 * mn1 * mn2);
    }
}

