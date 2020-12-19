/*
 * @lc app=leetcode id=495 lang=java
 *
 * [495] Teemo Attacking
 */
class Solution {
    public int findPoisonedDuration(int[] timeSeries, int duration) {
        int res = 0, n = timeSeries.length;
        for (int i = 1; i < n; ++i) {
            int diff = timeSeries[i] - timeSeries[i - 1];
            if (diff < duration)
                res += diff;
            else
                res += duration;
        }
        return res + duration;
    }
}
