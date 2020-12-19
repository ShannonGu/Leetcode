import java.util.ArrayList;

/*
 * @lc app=leetcode id=207 lang=java
 *
 * [207] Course Schedule
 */
class Solution {
    private int[] inDegree;
    private List<Integer>[] graph;
    private int[] q;
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        inDegree = new int[numCourses];
        graph = new ArrayList[numCourses];
        for (int i = 0; i < numCourses; ++i)
            graph[i] = new ArrayList<>();
        q = new int[numCourses];
        for (int[] pre : prerequisites) {
            graph[pre[1]].add(pre[0]);
            ++inDegree[pre[0]];
        }
        return func(numCourses);
    }

    private boolean func(int n) {
        int head = 0, tail = -1;
        for (int i = 0; i < n; ++i) {
            if (inDegree[i] == 0) {
                q[++tail] = i;
            }
        }

        while (head <= tail) {
            int tmp = q[head++];
            for (int e : graph[tmp]) {
                --inDegree[e];
                if (inDegree[e] == 0) {
                    q[++tail] = e;
                }
            }
        }

        return tail == n - 1;
    }
}

