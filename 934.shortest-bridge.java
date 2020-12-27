import java.util.Queue;

/*
 * @lc app=leetcode id=934 lang=java
 *
 * [934] Shortest Bridge
 */

// @lc code=start
class Solution {
    private int[] dires = { -1, 0, 1, 0, -1 };
    private int m, n;
    private Queue<int[]> q;

    public int shortestBridge(int[][] A) {
        if (A.length == 0)
            return 0;
        m = A.length;
        n = A[0].length;
        q = new LinkedList<>();
        boolean flag = false;
        // 首先dfs找到第一个岛屿
        for (int i = 0; i < m; i++) {
            if (flag)// 标记找到了第一个岛屿
                break;
            for (int j = 0; j < n; j++) {
                // 从第一个岛屿的某个点出发，将该岛屿全部标记为2
                if (A[i][j] == 1) {
                    helper(A, i, j);
                    flag = true;
                    break;
                }
            }
        }

        // 然后bfs找第二个岛屿
        // q中存储的是与第一个岛屿紧邻的一层'0'
        // res标记需经过几层'0'才能到达第二个岛屿
        int res = 0;
        while (!q.isEmpty()) {
            ++res;
            int len = q.size();
            while (len-- > 0) {
                int[] t = q.poll();
                for (int k = 0; k < 4; k++) {
                    int x = t[0] + dires[k], y = t[1] + dires[k + 1];
                    if (x < 0 || x >= m || y < 0 || y >= n)
                        continue;
                    if (A[x][y] == 2)
                        continue;
                    if (A[x][y] == 1)
                        return res;
                    // A[x][y] == 0
                    q.offer(new int[] { x, y });
                    A[x][y] = 2;
                }
            }
        }
        return 0;
    }

    private void helper(int[][] A, int i, int j) {
        if (i < 0 || i >= m || j < 0 || j >= n || A[i][j] == 2)
            return;
        // 将该岛屿毗邻的'0'存储起来
        if (A[i][j] == 0) {
            q.offer(new int[] { i, j });
            return;
        }
        A[i][j] = 2;
        helper(A, i - 1, j);
        helper(A, i, j + 1);
        helper(A, i + 1, j);
        helper(A, i, j - 1);
    }
}
// @lc code=end
