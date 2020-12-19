import java.util.HashMap;
import java.util.Map;

/*
 * @lc app=leetcode id=1074 lang=java
 *
 * [1074] Number of Submatrices That Sum to Target
 */

// @lc code=start
class Solution {
    // https://leetcode.com/problems/number-of-submatrices-that-sum-to-target/discuss/303750/JavaC%2B%2BPython-Find-the-Subarray-with-Target-Sum
    public int numSubmatrixSumTarget(int[][] matrix, int target) {
        if (matrix.length == 0 || matrix[0].length == 0)
            return 0;
        int m = matrix.length, n = matrix[0].length;

        // 首先算出每一行的前缀和,直接利用原矩阵存储
        for (int i = 0; i < m; ++i) {
            for (int j = 1; j < n; ++j) {
                matrix[i][j] += matrix[i][j - 1];
            }
        }

        int res = 0;
        // 按列遍历
        for (int i = 0; i < n; ++i) {
            for (int j = i; j < n; ++j) {
                // hashmap用来存储以matrix[0][i]为左上角顶点和以matrix[k][j]为右下角顶点的矩形的所有数的和
                Map<Integer, Integer> map = new HashMap<>();
                // 这里的操作和之前的求数组中子数组之和有相似之处
                // 都是采用hashmap辅助
                map.put(0, 1);
                int cur = 0;
                // 固定列不动，按行遍历，求出行之间哪一个矩形的和是等于target,加上它们的数量
                for (int k = 0; k < m; ++k) {
                    cur += matrix[k][j] - (i > 0 ? matrix[k][i - 1] : 0);
                    res += map.getOrDefault(cur - target, 0);
                    // 更新当前矩形和的数量
                    map.put(cur, map.getOrDefault(cur, 0) + 1);
                }
            }
        }
        return res;
    }
}
// @lc code=end
