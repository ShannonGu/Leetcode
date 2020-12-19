import java.util.Set;

/*
 * @lc app=leetcode id=764 lang=java
 *
 * [764] Largest Plus Sign
 */

// @lc code=start
class Solution {
    // https://www.cnblogs.com/grandyang/p/8679286.html
    public int orderOfLargestPlusSign(int N, int[][] mines) {
        int res = 0, cnt = 0;
        int[][] dp = new int[N][N];
        //存放0的位置
        Set<Integer> s = new HashSet<>();

        for (int[] mine : mines) {
            s.add(mine[0] * N + mine[1]);
        }

        // 先求出从当前位置向上、向下的最长连续的1的长度
        // 取他们的最小值,作为可能的半径
        for (int j = 0; j < N; ++j) {
            cnt = 0;
            for (int i = 0; i < N; ++i) {// up
                cnt = s.contains(i * N + j) ? 0 : cnt + 1;
                dp[i][j] = cnt;
            }

            cnt = 0;
            for (int i = N - 1; i >= 0; --i) {// down
                cnt = s.contains(i * N + j) ? 0 : cnt + 1;
                dp[i][j] = Math.min(dp[i][j], cnt);
            }
        }

        //再求出从当前位置向左、向右的最长连续的1的长度
        //取四个方向的最小值，作为每个位置的半径
        //在取每个位置的半径的最大值,作为最大的十字的半径;
        for (int i = 0; i < N; ++i) {
            cnt = 0;
            for (int j = 0; j < N; ++j) {
                cnt = s.contains(i * N + j) ? 0 : cnt + 1;
                dp[i][j] = Math.min(dp[i][j], cnt);
            }
            cnt = 0;
            for (int j = N - 1; j >= 0; --j) {
                cnt = s.contains(i * N + j) ? 0 : cnt + 1;
                dp[i][j] = Math.min(dp[i][j], cnt);
                res = Math.max(res, dp[i][j]);
            }
        }
        return res;
    }
}
// @lc code=end
