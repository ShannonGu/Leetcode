/*
 * @lc app=leetcode id=74 lang=java
 *
 * [74] Search a 2D Matrix
 */
class Solution {
    //https://www.cnblogs.com/grandyang/p/4323301.html
    //将二维数组转换为一维进行BinarySearch
    // public boolean searchMatrix(int[][] matrix, int target) {
    //     if(matrix.length == 0 || matrix[0].length == 0)
    //         return false;
    //     int row = matrix.length, col = matrix[0].length;
    //     if(target < matrix[0][0] || target > matrix[row - 1][col - 1])
    //         return false;
    //     int l = 0, r = row * col;
    //     while (l < r) {
    //         int mid = l + ((r - l) >> 1);
    //         if (matrix[mid / col][mid % col] == target)
    //             return true;
    //         else if (matrix[mid / col][mid % col] < target)
    //             l = mid + 1;
    //         else
    //             r = mid;
    //     }
    //     return false;
    // }


    public boolean searchMatrix(int[][] matrix, int target) {
        if(matrix.length == 0 || matrix[0].length == 0)
            return false;
        int row = matrix.length, col = matrix[0].length;
        if(target < matrix[0][0] || target > matrix[row - 1][col - 1])
            return false;
        int l = 0, r = row;
        while (l < r) {
            int mid = l + ((r - l) >> 1);
            if (matrix[mid][0] == target)
                return true;
            else if (matrix[mid][0] < target)
                l = mid + 1;
            else
                r = mid;
        }
        
        //target可能在的目标行
        int pos = r - 1;
        l = 0;
        r = col;
        while (l < r) {
            int mid = l + ((r - l) >> 1);
            if (matrix[pos][mid] == target)
                return true;
            else if (matrix[pos][mid] < target)
                l = mid + 1;
            else
                r = mid;
        }
        return false;
    }
}

