import java.util.Arrays;

/*
 * @lc app=leetcode id=685 lang=java
 *
 * [685] Redundant Connection II
 */
class Solution {
    private int[] root;

    public int[] findRedundantDirectedConnection(int[][] edges) {
        //要寻找入度为2的节点，保证他的入度只能为1
        //如果没有，要保证图中没有环，也即有一个入度为0的根
        //有三种情况
        //第一种：无环，但是有结点入度为2的结点（结点3）
        // [[1,2], [1,3], [2,3]]
        // 返回[2,3]
        //   1
        //  / \
        // v   v
        // 2-->3

        // 第二种：有环，没有入度为2的结点
        // [[1,2], [2,3], [3,4], [4,1], [1,5]]
        // 返回[4,1]
        // 5 <- 1 -> 2
        //      ^    |
        //      |    v
        //      4 <- 3

        // 第三种：有环，且有入度为2的结点（结点1）
        // [[1,2],[2,3],[3,1],[4,1]]
        // 返回[3,1]
        //      4
        //     /
        //    v
        //    1
        //  /  ^
        // v    \
        // 2 --> 3

        int n = edges.length;
        root = new int[n + 1];
        int[] fir = new int[] {}, sec = new int[] {};
        
        //首先要找到入度为2的节点
        for (int[] edge : edges) {
            int a = edge[0], b = edge[1];
            if (root[b] == 0)
                root[b] = a;
            else {
                //之前edge[1]已经有一个root了，也即入度不是0了
                fir = new int[] { root[b], b };
                //sec为当前edge,
                //注意这里不能写sec = edge,因为是引用
                //所以下面一句edge[1] = 0，也会对这里产生影响
                sec = new int[] { a, b };

                //将后面加入的使得入度为2的边标记删除
                edge[0] = edge[1] = -1;
            }
        }
        
        //重新初始化
        for (int i = 0; i <= n; ++i) {
            root[i] = i;
        }


        //遍历每条边
        for (int[] edge : edges) {
            //如edge[1] < 0说明遍历到了改变edge[1]值的那条边
            //也就是使得edge[1]的入度为2的那条边
            //情况1
            int a = edge[0], b = edge[1];
            if (a < 0 || b < 0)
                continue;

            int pa = find(a), pb = find(b);

            if (pa == pb)
                //若fir为空，说明图中没有入度为2的节点，此时存在一个环且没有定点的入度是2，即情况2
                //否则,是情况3, 此时只能删除[3, 1],因为要保证图为一棵树，且没有点的入度是2
                return fir.length == 0 ? edge : fir;

            root[pb] = pa;
        }

        //如果循环中没有返回值，则是第一种情况，图中没有环
        return sec;
    }

    private int find(int x) {
        while (root[x] != x) {
            x = root[x];
        }
        return x;
    }
}

