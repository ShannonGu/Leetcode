/*
 * @lc app=leetcode id=633 lang=java
 *
 * [633] Sum of Square Numbers
 */

// @lc code=start
class Solution {
    // solution 1
    // public boolean judgeSquareSum(int c) {
    // for (long a = 0; a * a <= c; a++) {
    // double b = Math.sqrt(c - a * a);
    // if (b == (int) b)
    // return true;
    // }
    // return false;
    // }

    // solution2 双指针
    public boolean judgeSquareSum(int c) {
        if (c < 0)
            return false;
        int a = (int) (Math.sqrt(c));
        if (a * a == c) {
            return true;
        } else {
            int b = 1;
            // 注意"="
            while (b <= a) {
                if (b * b + a * a == c)
                    return true;
                else if (b * b + a * a < c) {
                    b++;
                } else
                    a--;
            }
        }
        return false;
    }
}
// @lc code=end
