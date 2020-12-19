/*
 * @lc app=leetcode id=741 lang=java
 *
 * [741] Cherry Pickup
 */

// @lc code=start
class Solution {
    // https://zxi.mytechroad.com/blog/dynamic-programming/leetcode-741-cherry-pickup/
    private int[][] g;
    // memo[x1][y1][x2]表示从{[x1,y1],[x2,y2]}走到[0,0]所能获得最大樱桃数
    private int[][][] memo;

    public int cherryPickup(int[][] grid) {
        // 从[0,0]到[n-1][n-1]再返回[0,0]可以看成是[n-1,n-1]到[0,0]走了两次
        // 可以看成有两个人同时从[n-1,n-1]向[0,0]走
        // 如果他们走到了同一个位置，而该位置上只有一个cherry，只能有一个人拿走
        // 可以用x1,y1,x2来描述二者的位置，因为他们的坐标和总是相等的,y2 = x1+y1-x2;
        int n = grid.length;
        g = grid;
        memo = new int[n][n][n];

        // 初始状态为Integer.MIN_VALUE,表示子问题没有求解过
        for (int[][] mem : memo) {
            for (int[] e : mem) {
                Arrays.fill(e, Integer.MIN_VALUE);
            }
        }

        // helper()可能返回负值，表示path不存在，此时只能返回0
        return Math.max(0, helper(n - 1, n - 1, n - 1));
    }

    // 两个人都从[n-1,n-1]出发，最终在[0,0]相遇
    private int helper(int x1, int y1, int x2) {

        int y2 = x1 + y1 - x2;

        // 走了grid之外，表示该条路径不存在
        if (x1 < 0 || y1 < 0 || x2 < 0 || y2 < 0)
            return -1;

        // 碰到了障碍物
        if (g[x1][y1] < 0 || g[x2][y2] < 0)
            return -1;

        // 两者同时走到[0,0]这个点
        if (x1 == 0 && y1 == 0)
            // 只有一人能取到樱桃
            return g[x1][y1];

        // 该路径的结果已经得到了
        if (memo[x1][y1][x2] != Integer.MIN_VALUE)
            return memo[x1][y1][x2];

        // 对二者能走到的下一个位置进行比较
        int res = Math.max(Math.max(helper(x1 - 1, y1, x2 - 1), helper(x1, y1 - 1, x2)),
                Math.max(helper(x1, y1 - 1, x2 - 1), helper(x1 - 1, y1, x2)));

        // 该路径是能走通的, 再加上当前位置的樱桃数
        if (res >= 0) {
            res += g[x1][y1];
            // 若当前在同一个位置，只能一人能拿走樱桃
            if (x1 != x2)
                res += g[x2][y2];
        }

        memo[x1][y1][x2] = res;
        return res;
    }
}
// @lc code=end
