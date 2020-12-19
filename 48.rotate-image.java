/*
 * @lc app=leetcode id=48 lang=java
 *
 * [48] Rotate Image
 */
class Solution {

    // https://www.cnblogs.com/grandyang/p/4389572.html
    // public void rotate(int[][] matrix) {
    //     int n = matrix.length;
    //     for (int i = 0; i < n / 2; ++i) {
    //         for (int j = i; j < n - 1 - i; ++j) {
    //             int tmp = matrix[i][j];
    //             matrix[i][j] = matrix[n - 1 - j][i];
    //             matrix[n - 1 - j][i] = matrix[n - 1 - i][n - 1 - j];
    //             matrix[n - 1 - i][n - 1 - j] = matrix[j][n - 1 - i];
    //             matrix[j][n - 1 - i] = tmp;
    //         }
    //     }
    // }


    //先以对角线为轴翻转
    //再以x轴中线上下翻转
    public void rotate(int[][] matrix) {
        int n = matrix.length;
        for (int i = 0; i < n - 1; ++i) {
            for (int j = 0; j < n - i; ++j) {
                int tmp = matrix[i][j];
                matrix[i][j] = matrix[n - 1 - j][n - 1 - i];
                matrix[n - 1 - j][n - 1 - i] = tmp;
            }
        }

        for (int i = 0; i < n / 2; ++i) {
            for (int j = 0; j < n; ++j) {
                int tmp = matrix[i][j];
                matrix[i][j] = matrix[n - 1 - i][j];
                matrix[n - 1 - i][j] = tmp;
            }
        }
    }
}
