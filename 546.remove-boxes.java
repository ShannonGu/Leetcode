/*
 * @lc app=leetcode id=546 lang=java
 *
 * [546] Remove Boxes
 */
class Solution {
    // https://www.cnblogs.com/grandyang/p/6850657.html
    // https://zxi.mytechroad.com/blog/dynamic-programming/leetcode-546-remove-boxes/
    // private int[][][] mem;

    // //为了求得分数的最大值，所以总是希望尽可能地将连在一起最多的box消除掉
    // public int removeBoxes(int[] boxes) {
    //     int n = boxes.length;
    //     // dp[i][j][k]表示[i,j]中能够取得最大的分数,
    //     // 如果boxes[j]后面有连续k个的box和boxes[j]是一样的颜色
    //     mem = new int[n][n][n];

    //     return dfs(boxes, 0, n - 1, 0);
    // }

    // private int dfs(int[] boxes, int l, int r, int k) {
    //     if (l > r)
    //         return 0;
    //     if (mem[l][r][k] > 0)
    //         return mem[l][r][k];

    //     // case1,将boxes[r]和其后面的k个相同颜色的boxes合并
    //     // 可以得到(k+1)*(k+1)分数,然后再求解剩下的[l][r-1][0]的情况
    //     int res = (k + 1) * (k + 1) + dfs(boxes, l, r - 1, 0);

    //     // case2遍历分割点
    //     for (int i = l; i < r; ++i) {
    //         // 当boxes[i]与boxes[r]颜色相同时, 可以先将r与后面k个boxes移到i后面
    //         // 这样情况是求[l][i][k+1]与[i+1][r-1][0]的得到分数的总和
    //         // 再与case1比较全局最大值
    //         if (boxes[i] == boxes[r])
    //             res = Math.max(res, dfs(boxes, l, i, k + 1) + dfs(boxes, i + 1, r - 1, 0));
    //     }

    //     mem[l][r][k] = res;
    //     return res;
    // }



    //对上面解法的优化
    private int[][][] mem = new int[101][101][101];

    public int removeBoxes(int[] boxes) {
        int n = boxes.length;
        return dfs(boxes, 0, n - 1, 0);
    }

    private int dfs(int[] boxes, int l, int r, int k) {
        if(l > r)
            return 0;
        
        //尽可能多的将连在一起的相同的颜色box一起计算
        while (l < r && boxes[r - 1] == boxes[r]) {
            --r;
            ++k;
        }

        if(mem[l][r][k] > 0)
            return mem[l][r][k];
            
        int res = (k + 1) * (k + 1) + dfs(boxes, l, r - 1, 0);
        for (int i = l; i < r; ++i) {
            if (boxes[i] == boxes[r])
                res = Math.max(res, dfs(boxes, l, i, k + 1) + dfs(boxes, i + 1, r - 1, 0));
        }
        mem[l][r][k] = res;
        return res;
    }
}
