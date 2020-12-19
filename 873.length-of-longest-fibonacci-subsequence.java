import java.util.Map;

/*
 * @lc app=leetcode id=873 lang=java
 *
 * [873] Length of Longest Fibonacci Subsequence
 */

// @lc code=start
class Solution {
    // https://zxi.mytechroad.com/blog/dynamic-programming/leetcode-873-length-of-longest-fibonacci-subsequence/
    public int lenLongestFibSubseq(int[] A) {
        int n = A.length;
        Map<Integer, Integer> m = new HashMap<>();

        for (int i = 0; i < n; ++i) {
            m.put(A[i], i);
        }
        //dp[i][j]表示以i,j结尾的Fib Seq的数量
        int[][] dp = new int[n][n];
        
        for (int[] e : dp)
            Arrays.fill(e, 2);
        int res = 0;
        for (int j = 0; j < n; ++j) {
            for (int k = j + 1; k < n; ++k) {
                int a_i = A[k] - A[j];

                // 剪枝条件
                if (a_i >= A[j])
                    break;
                    
                if (!m.containsKey(a_i))
                    continue;
                int i = m.get(a_i);
                dp[j][k] = dp[i][j] + 1;
                res = Math.max(res, dp[j][k]);
            }
        }
        return res;
    }
}
// @lc code=end
