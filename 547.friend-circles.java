/*
 * @lc app=leetcode id=547 lang=java
 *
 * [547] Friend Circles
 */
class Solution {
    // solution 1 并查集解法
    private int[] father;

    public int findCircleNum(int[][] M) {
        int n = M.length;
        father = new int[n];
        init(n);

        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                // 是不同的两个人，且M[i][j]==1
                if (i != j && M[i][j] == 1)
                    unite(i, j);
            }
        }

        int cnt = 0;
        for (int i = 0; i < n; ++i) {
            // 判断是否单独一个人
            if (father[i] == i)
                ++cnt;
        }
        return cnt;
    }

    private void init(int n) {
        for (int i = 0; i < n; ++i)
            father[i] = i;
    }

    private int find(int x) {
        if (x == father[x])
            return x;
        father[x] = find(father[x]);
        return father[x];
    }

    private void unite(int x, int y) {
        x = find(x);
        y = find(y);
        if (x != y)
            father[x] = y;
    }

    // solution2 递归
    public int findCircleNum(int[][] M) {
        int n = M.length, res = 0;
        boolean[] vis = new boolean[n];
        for (int i = 0; i < n; i++) {
            if (!vis[i]) {
                dfs(M, i, vis);
                ++res;
            }
        }
        return res;
    }

    private void dfs(int[][] M, int i, boolean[] vis) {
        vis[i] = true;
        for (int j = 0; j < M.length; j++) {
            if (M[i][j] == 1 && !vis[j]) {
                dfs(M, j, vis);
            }
        }
    }
}
