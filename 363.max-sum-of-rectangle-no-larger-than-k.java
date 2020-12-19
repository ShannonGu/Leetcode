import java.util.TreeSet;

/*
 * @lc app=leetcode id=363 lang=java
 *
 * [363] Max Sum of Rectangle No Larger Than K
 */

// @lc code=start
class Solution {
    // https://www.cnblogs.com/grandyang/p/5617660.html
    public int maxSumSubmatrix(int[][] matrix, int k) {
        if (matrix.length == 0 || matrix[0].length == 0)
            return 0;
        int m = matrix.length, n = matrix[0].length;
        int res = Integer.MIN_VALUE;

        // 矩形左侧边界
        for (int i = 0; i < n; ++i) {
            // 两列之间每一行的累加和
            int[] sums = new int[m];
            // 矩形右侧边界
            for (int j = i; j < n; ++j) {
                // 对于固定的列,累加每一行上的数
                // 即求出了此时矩形的和
                // p表示矩形的下边界
                for (int p = 0; p < m; ++p) {
                    sums[p] += matrix[p][j];
                }

                int cur = 0;
                TreeSet<Integer> set = new TreeSet<>();
                set.add(0);

                for (int sum : sums) {
                    cur += sum;
                    // 取得 >= cur-k的最小数
                    Integer it = set.ceiling(cur - k);
                    // 如果存在
                    // cur-it就是接近k的数，再进行比较取得最近的<=k的数
                    if (it != null) {
                        res = Math.max(res, cur - it);
                    }
                    set.add(cur);
                }
            }
        }
        return res;
    }
}
// @lc code=end
