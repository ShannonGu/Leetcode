/*
 * @lc app=leetcode id=60 lang=java
 *
 * [60] Permutation Sequence
 */

// @lc code=start 
class Solution {
    // https://www.cnblogs.com/grandyang/p/4358678.html
    // https://www.jiuzhang.com/solution/permutation-sequence#tag-highlight
    public String getPermutation(int n, int k) {
        StringBuilder res = new StringBuilder("");

        int used = 0, fac = 1;

        for (int i = 1; i < n; ++i) {
            fac *= i;
        }
        --k;

        for (int i = 0; i < n; ++i) {
            int idx = k / fac;
            k %= fac;
            for (int j = 0; j < n; ++j) {
                if ((used & (1 << j)) == 0) {
                    if (idx == 0) {
                        used |= 1 << j;
                        res.append((char) ('0' + j + 1));
                        break;
                    } else
                        --idx;
                }
            }
            if (i < n - 1)
                fac = fac / (n - 1 - i);
        }
        return res.toString();
    }
}
// @lc code=end
