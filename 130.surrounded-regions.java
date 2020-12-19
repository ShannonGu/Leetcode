/*
 * @lc app=leetcode id=130 lang=java
 *
 * [130] Surrounded Regions
 */
class Solution {
    private int[] root;
    private int[][] dires = { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } };
    public void solve(char[][] board) {
        if(board.length == 0 || board[0].length == 0)
            return;
        int m = board.length, n = board[0].length;
        //定义一个虚节点m*n，作为靠边的'O'的root
        //将靠边的'O'与内部被包围的 'O'分割开
        root = new int[m * n + 1];
        init(m * n);
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                //判断是否属于边上的 'O',若是，则将之与虚节点合并
                if ((i == 0 || i == m - 1 || j == 0 || j == n - 1) && board[i][j] == 'O')
                    unite(i * n + j, m * n);
                else if (board[i][j] == 'O') {
                    for (int[] dire : dires) {
                        int x = i + dire[0], y = j + dire[1];
                        if (board[x][y] == 'O')
                            unite(i * n + j, x * n + y);
                    }
                }
            }
        }
        
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                //判断是否属于靠边的 'O'
                //不是则将之改为 'X'
                if(board[i][j] == 'O' && !isSame(i * n + j, m * n))
                    board[i][j] = 'X';
            }
        }
    }
    
    private void init(int n) {
        for (int i = 0; i <= n; ++i)
            root[i] = i;
    }

    private int find(int x) {
        if (x == root[x])
            return x;
        root[x] = find(root[x]);
        return root[x];
    }
    
    private void unite(int x, int y) {
        root[find(x)] = find(y);
    }

    private boolean isSame(int x, int y) {
        return find(x) == find(y);
    }
}

