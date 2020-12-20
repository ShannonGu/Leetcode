/*
 * @lc app=leetcode id=69 lang=java
 *
 * [69] Sqrt(x)
 */
class Solution {
    // https://www.cnblogs.com/grandyang/p/4346413.html
    // https://www.acwing.com/solution/LeetCode/content/154/
    // 二分法
    // 如果将区间划分为[l, m]和[m+1, r]
    // 则mid=l+r>>1,相应地l=m+1或者r=m
    // 如果将区间划分为[l, m-1]和[m, r]
    // 则mid=l+r+1>11,相应地l=m或者r=m-1
    public int mySqrt(int x) {
        if (x <= 1)
            return x;
        long l = 0, r = x;
        while (l < r) {
            long mid = l + r + 1 >> 1;
            // 在[m, r]范围内
            if (x / mid >= mid)
                l = mid;
            else // 在[l, m-1]范围内
                r = mid - 1;
        }
        return (int) l;
    }
}
