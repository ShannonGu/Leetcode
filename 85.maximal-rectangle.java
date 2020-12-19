import java.util.Collections;
import java.util.List;

/*
 * @lc app=leetcode id=85 lang=java
 *
 * [85] Maximal Rectangle
 */
class Solution {
    // https://www.cnblogs.com/grandyang/p/4322667.html
    // public int maximalRectangle(char[][] matrix) {
    //     if (matrix.length == 0 || matrix[0].length == 0)
    //         return 0;
    //     int n = matrix[0].length, res = 0;
    //     int[] height = new int[n + 1];
    //     height[n] = 0;
    //     for (int i = 0; i < matrix.length; ++i) {
    //         for (int j = 0; j < n; ++j) {
    //             height[j] = matrix[i][j] == '0' ? 0 : 1 + height[j];
    //         }
    //         res = Math.max(res, maxArea(height));
    //     }
    //     return res;
    // }

    // private int maxArea(int[] height) {
    //     Stack<Integer> st = new Stack<>();
    //     // int[] newH = new int[height.length + 1];
    //     // System.arraycopy(height, 0, newH, 0, height.length);
    //     // newH[height.length] = 0;
    //     int res = 0;
    //     for (int i = 0; i < height.length; ++i) {
    //         while (!st.isEmpty() && height[i] < height[st.peek()]) {
    //             int hi = st.pop();
    //             int left = st.isEmpty() ? -1 : st.peek();
    //             int width = i - left - 1;
    //             res = Math.max(res, height[hi] * width);
    //         }
    //         st.push(i);
    //     }
    //     return res;
    // }


    //https://zxi.mytechroad.com/blog/dynamic-programming/leetcode-85-maximal-rectangle/
    //DP
    public int maximalRectangle(char[][] matrix) {
        int r = matrix.length;
        if(r == 0)
            return 0;
        int c = matrix[0].length;

        //dp[i][j]表示第i行上以第j列结尾的连续的'1'的长度,
        int[][] dp = new int[r][c];
        for (int i = 0; i < r; ++i) {
            for (int j = 0; j < c; ++j) {
                dp[i][j] = (matrix[i][j] == '1') ? (j == 0 ? 1 : dp[i][j - 1] + 1) : 0;
            }
        }

        int res = 0;

        for (int i = 0; i < r; ++i) {
            for (int j = 0; j < c; ++j) {

                //按列枚举
                //len表示当前矩形横向的长度
                int len = Integer.MAX_VALUE;
                //k表示当前矩形的下方的行数
                //实际上确定了以matrix[k][j]为当前矩形的右下角，求当前矩形的面积
                for (int k = i; k < r; ++k) {
                    //要遍历每一行取最小的长度
                    len = Math.min(len, dp[k][j]);
                    //长度为0则不能形成矩形
                    if (len == 0)
                        break;
                    //k - i + 1表示当前矩形的纵向的宽度
                    res = Math.max(res, len * (k - i + 1));
                }
            }
        }
        return res;
    }
}
