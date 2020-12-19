/*
 * @lc app=leetcode id=367 lang=java
 *
 * [367] Valid Perfect Square
 */
class Solution {
    public boolean isPerfectSquare(int num) {
        if(num == 1)
            return true;
        long l = 0, r = num;
        while (l < r) {
            long mid = l + r >> 1;
            long tmp = mid * mid;
            if (tmp >= num)
                r = mid;
            else
                l = mid + 1;
        }
        return l * l == num;
    }

    // public boolean isPerfectSquare(int num) {
    //     for (int i = 1; i <= num / i; ++i) {
    //         if (i * i == num)
    //             return true;
    //     }
    //     return false;
    // }
}

