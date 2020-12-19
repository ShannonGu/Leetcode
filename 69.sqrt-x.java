/*
 * @lc app=leetcode id=69 lang=java
 *
 * [69] Sqrt(x)
 */
class Solution {
    //https://www.cnblogs.com/grandyang/p/4346413.html
    //https://www.acwing.com/solution/LeetCode/content/154/
    // public int mySqrt(int x) {
    //     if (x <= 1)
    //         return x;
    //     long l = 0, r = x;
    //     while (l < r) {
    //         int mid = l + ((r - l) >> 1);
    //         if (x / mid >= mid)
    //             l = mid + 1;
    //         else
    //             r = mid;
    //     }
    //     return l - 1;

    // }

    public int mySqrt(int x) {
        if(x < 0)
            return -1;
        if(x <= 1)
            return x;
        long l = 0, r = x;
        while (l < r) {
            long mid = l + r + 1 >> 1;
            if (x / mid >= mid)
                l = mid;
            else
                r = mid - 1;
        }
        return (int) l;
    }
}

