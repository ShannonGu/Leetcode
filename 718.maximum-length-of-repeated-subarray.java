/*
 * @lc app=leetcode id=718 lang=java
 *
 * [718] Maximum Length of Repeated Subarray
 */
class Solution {
    // https://zxi.mytechroad.com/blog/dynamic-programming/leetcode-718-maximum-length-of-repeated-subarray/
    // public int findLength(int[] A, int[] B) {
    //     int m = A.length, n = B.length;
    //     int[][] dp = new int[m + 1][n + 1];
    //     int res = 0;
    //     for (int i = 1; i <= m; ++i) {
    //         for (int j = 1; j <= n; ++j) {
    //             if (A[i - 1] == B[j - 1])
    //                 dp[i][j] = dp[i - 1][j - 1] + 1;
    //             res = Math.max(res, dp[i][j]);
    //         }
    //     }
    //     return res;
    // }

    public int findLength(int[] A, int[] B) {
        if (A.length < B.length) {
            int[] tmp = A;
            A = B;
            B = tmp;
        }

        int m = A.length, n = B.length;
        int[] dp = new int[n + 1];
        int res = 0;
        for (int i = 1; i <= m; ++i) {
            for (int j = n; j >= 1; --j) {
                dp[j] = A[i - 1] == B[j - 1] ? dp[j - 1] + 1 : 0;
                res = Math.max(res, dp[j]);
            }
        }
        return res;
    }
}
