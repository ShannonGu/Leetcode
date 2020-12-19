/*
 * @lc app=leetcode id=769 lang=java
 *
 * [769] Max Chunks To Make Sorted
 */

// @lc code=start
class Solution {
    //https://www.cnblogs.com/grandyang/p/8823944.html
    // public int maxChunksToSorted(int[] arr) {
    //     int res = 0, n = arr.length, mx = 0;
    //     for (int i = 0; i < n; ++i) {
    //         mx = Math.max(mx, arr[i]);
    //         if (mx == i)
    //             ++res;
    //     }
    //     return res;
    // }

    public int maxChunksToSorted(int[] arr) {
        int res = 0, n = arr.length;
        for (int i = 0; i < n; ++i) {
            int cur = arr[i], j = i + 1;
            for (; j <= cur; ++j) {
                cur = Math.max(cur, arr[j]);
                if (cur >= arr[n - 1])
                    return res + 1;
            }
            i = j - 1;
            ++res;
        }
        return res;
    }
}
// @lc code=end

