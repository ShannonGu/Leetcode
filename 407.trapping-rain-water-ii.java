import java.util.PriorityQueue;

/*
 * @lc app=leetcode id=407 lang=java
 *
 * [407] Trapping Rain Water II
 */

// @lc code=start
class Solution {
    //https://www.cnblogs.com/grandyang/p/5928987.html
    public int trapRainWater(int[][] heightMap) {
        if(heightMap.length == 0 || heightMap[0].length == 0)
            return 0;
        int m = heightMap.length, n = heightMap[0].length;
        //mx用于记录当前海平面的高度
        int res = 0, mx = Integer.MIN_VALUE;
        //维护一个堆
        PriorityQueue<int[]> q = new PriorityQueue<>((w1, w2) -> w1[0] - w2[0]);
        boolean[][] vis = new boolean[m][n];
        int[][] dirs = { { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 } };
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                //边界上的点不可能装水，所以先将它们存入堆中
                if (i == 0 || i == m - 1 || j == 0 || j == n - 1) {
                    q.offer(new int[] { heightMap[i][j], i * n + j });
                    vis[i][j] = true;
                }
            }
        }

        //以边界上的点作为启动点，寻找可以装水的坑
        while (!q.isEmpty()) {
            int[] tmp = q.poll();
            //h为当前的高度，
            int h = tmp[0], r = tmp[1] / n, c = tmp[1] % n;
            //更新当前海平面的高度
            mx = Math.max(mx, h);
            for (int i = 0; i < dirs.length; i++) {
                int x = r + dirs[i][0], y = c + dirs[i][1];
                //考察其邻点是否是边界上的点或者已经访问过
                if (x < 0 || x >= m || y < 0 || y >= n || vis[x][y])
                    continue;
                vis[x][y] = true;
                //如果邻点小于海平面的高度，说明该点可以与海平面形成坑装水
                if (heightMap[x][y] < mx)
                    res += mx - heightMap[x][y];
                //将该点存入堆中
                q.offer(new int[] { heightMap[x][y], x * n + y });
            }
        }
        
        return res;
    }
}
// @lc code=end

