/*
 * @lc app=leetcode id=378 lang=java
 *
 * [378] Kth Smallest Element in a Sorted Matrix
 */
class Solution {
    // http://www.cnblogs.com/grandyang/p/5727892.html

    public int kthSmallest(int[][] matrix, int k) {

        if (matrix.length == 0 || matrix[0].length == 0)
            return 0;
        int n = matrix.length;
        // l为matrix最小数,r为最大数;
        int l = matrix[0][0], r = matrix[n - 1][n - 1];
        while (l < r) {
            // 以matrix最大数和最小数的中间值为target，进行二分查找;
            int mid = l + r >> 1;

            // 求出<=target的数的个数cnt;
            int cnt = helper(matrix, mid);
            if (cnt >= k)
                r = mid;
            else
                l = mid + 1;
        }
        return l;
    }

    // 求出matrix中<=target的数的个数;
    private int helper(int[][] matrix, int target) {
        // 根据matrix的性质,行有序,列有序;
        // 可以取matrix左下角的数为基准;
        // i为起始行,j为起始列;
        int n = matrix.length;
        int i = n - 1, j = 0;
        int cnt = 0;
        while (i >= 0 && j < n) {
            // 若左下角的数<=target,则该数所在的列上面每一行的数均小于<=target;
            // cnt += i+1,同时在该行继续向右寻找<=target的数;
            if (matrix[i][j] <= target) {
                cnt += i + 1;
                ++j;
            } else {
                // 若>target,则继续以上一行的第一个数为基准;
                --i;
            }
        }
        return cnt;
    }
}
