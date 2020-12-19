import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;


/*
 * @lc app=leetcode id=417 lang=java
 *
 * [417] Pacific Atlantic Water Flow
 */
class Solution {
    private int[][] dires = { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } };
    private int m, n;

    // public List<List<Integer>> pacificAtlantic(int[][] matrix) {
    //     if (matrix.length == 0 || matrix[0].length == 0)
    //         return new ArrayList<>();
    //     m = matrix.length;
    //     n = matrix[0].length;
    //     List<List<Integer>> res = new ArrayList<>();
    //     boolean[][] pac = new boolean[m][n];
    //     boolean[][] atl = new boolean[m][n];
    //     Queue<List<Integer>> q1 = new LinkedList<>();
    //     Queue<List<Integer>> q2 = new LinkedList<>();
    //     for (int i = 0; i < m; ++i) {
    //         q1.offer(Arrays.asList(i, 0));
    //         q2.offer(Arrays.asList(i, n - 1));
    //         pac[i][0] = true;
    //         atl[i][n - 1] = true;
    //     }
    //     for (int i = 0; i < n; ++i) {
    //         q1.offer(Arrays.asList(0, i));
    //         q2.offer(Arrays.asList(m - 1, i));
    //         pac[0][i] = true;
    //         atl[m - 1][i] = true;
    //     }
    //     helper(matrix, pac, q1);
    //     helper(matrix, atl, q2);

    //     for (int i = 0; i < m; ++i) {
    //         for (int j = 0; j < n; ++j) {
    //             if (pac[i][j] && atl[i][j])
    //                 res.add(Arrays.asList(i, j));
    //         }
    //     }
    //     return res;
    // }

    // private void helper(int[][] matrix, boolean[][] visited, Queue<List<Integer>> q) {
    //     while (!q.isEmpty()) {
    //         List<Integer> t = q.poll();
    //         for (int[] dire : dires) {
    //             int x = t.get(0) + dire[0], y = t.get(1) + dire[1];
    //             //没访问过的
    //             if(x < 0 || x == m || y < 0 || y == n || visited[x][y] || matrix[x][y] < matrix[t.get(0)][t.get(1)])
    //                 continue;
    //             //如果matrix[x][y]>=matrix[t.get(0)][t.get(1)]
    //             //说明可以顺着(t.get(0),t.get(1))到达相应的边
    //             visited[x][y] = true;
    //             q.offer(Arrays.asList(x, y));
    //         }
    //     }
    // }


    public List<List<Integer>> pacificAtlantic(int[][] matrix) {
        if (matrix.length == 0 || matrix[0].length == 0)
            return new ArrayList<>();
        m = matrix.length;
        n = matrix[0].length;
        boolean[][] pac = new boolean[m][n];
        boolean[][] atl = new boolean[m][n];
        List<List<Integer>> res = new ArrayList<>();
        for (int i = 0; i < m; ++i) {
            helper(matrix, pac, i, 0, Integer.MIN_VALUE);
            helper(matrix, atl, i, n - 1, Integer.MIN_VALUE);
        }

        for (int i = 0; i < n; ++i) {
            helper(matrix, pac, 0, i, Integer.MIN_VALUE);
            helper(matrix, atl, m - 1, i, Integer.MIN_VALUE);
        }

        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (pac[i][j] && atl[i][j])
                    res.add(Arrays.asList(i, j));
            }
        }
        return res;
    }
    
    private void helper(int[][] matrix, boolean[][] visited, int i, int j, int preVal) {
        if(i < 0 || i == m || j < 0 || j == n || visited[i][j] || matrix[i][j] < preVal)
            return;
        visited[i][j] = true;
        for (int[] dire : dires) {
            int x = i + dire[0], y = j + dire[1];
            helper(matrix, visited, x, y, matrix[i][j]);
        }
    }
}

