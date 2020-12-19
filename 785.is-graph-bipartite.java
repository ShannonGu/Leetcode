import java.util.ArrayList;

/*
 * @lc app=leetcode id=785 lang=java
 *
 * [785] Is Graph Bipartite?
 */
class Solution {
    //染色法判定二分图
    private List<Integer>[] g;
    private int[] color;

    public boolean isBipartite(int[][] graph) {
        int n = graph.length;
        g = new ArrayList[n + 1];
        for (int i = 0; i <= n; ++i)
            g[i] = new ArrayList<>();
        
        for (int i = 0; i < n; ++i) {
            for (int e : graph[i])
                g[i].add(e);
        }
        
        color = new int[n];

        boolean flag = true;
        for (int i = 0; i < n; ++i) {
            if (color[i] == 0) {
                if (!dfs(i, 1)) {
                    flag = false;
                    break;
                }
            }
        }
        return flag ? true : false;
    }

    private boolean dfs(int u, int c) {
        color[u] = c;
        for (int e : g[u]) {
            if (color[e] == 0) {
                if (!dfs(e, 3 - c))
                    return false;
            } else if (color[e] == c)
                return false;
        }
        return true;
    }
}
