import java.util.ArrayList;
import java.util.Collections;

/*
 * @lc app=leetcode id=886 lang=java
 *
 * [886] Possible Bipartition
 */
class Solution {
    private ArrayList<Integer>[] graph;
    private int[] color;//0表示还未染色，1表示白色，2表示黑色

    //染色法判定二分图
    public boolean possibleBipartition(int N, int[][] dislikes) {
        graph = new ArrayList[N + 1];
        for (int i = 0; i <= N; ++i) {
            graph[i] = new ArrayList<>();
        }

        color = new int[N + 1];

        for (int[] dislike : dislikes) {
            graph[dislike[0]].add(dislike[1]);
            graph[dislike[1]].add(dislike[0]);
        }

        boolean flag = true;

        for (int i = 1; i <= N; ++i) {
            //还未染色
            if (color[i] == 0) {
                //染成白色，进行递归遍历
                //如果有冲突
                //说明不能构成二分图
                if (!dfs(i, 1)) {
                    flag = false;
                    break;
                }
            }
        }
        return flag ? true : false;
    }


    //染色法判定二分图
    private boolean dfs(int u, int c) {
        color[u] = c;
        //从u的邻接点中开始递归遍历，相邻两个点不能是同一个颜色
        for (int e : graph[u]) {
            //如果当前邻接点还未染色
            if (color[e] == 0) {
                //将之染成不同另一个颜色
                //若不能成功，说明不能构成二分图
                if (!dfs(e, 3 - c))
                    return false;
            } else if (color[e] == c)
                return false;
        }
        return true;
    }
}
