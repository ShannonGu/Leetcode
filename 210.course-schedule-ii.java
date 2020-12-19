import java.util.ArrayList;

/*
 * @lc app=leetcode id=210 lang=java
 *
 * [210] Course Schedule II
 */
class Solution {
    private List<List<Integer>> graph;
    private int[] inDegree;
    private int[] q;

    public int[] findOrder(int numCourses, int[][] prerequisites) {
        graph = new ArrayList<>(numCourses);
        inDegree = new int[numCourses];
        q = new int[numCourses];

        for (int i = 0; i < numCourses; ++i)
            graph.add(new ArrayList<>());

        for (int[] pre : prerequisites) {
            graph.get(pre[1]).add(pre[0]);
            ++inDegree[pre[0]];
        }

        if(func(numCourses))
            return q;
        else
            return new int[] {};
    }
    
    private boolean func(int n) {
        int head = 0, tail = -1;
        for (int i = 0; i < n; ++i) {
            if (inDegree[i] == 0)
                q[++tail] = i;
        }

        while (head <= tail) {
            int t = q[head++];
            for (int i : graph.get(t)) {
                --inDegree[i];
                if (inDegree[i] == 0)
                    q[++tail] = i;
            }
        }
        
        return tail == n - 1;
    }
}

