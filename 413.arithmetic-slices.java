/*
 * @lc app=leetcode id=413 lang=java
 *
 * [413] Arithmetic Slices
 */
class Solution {
    //https://www.cnblogs.com/grandyang/p/5968340.html
    public int numberOfArithmeticSlices(int[] A) {
        int res = 0, len = A.length;
        //dp[i]表示以i位置结尾的算数切片的个数
        int[] dp = new int[len];

        //从第三个数开始遍历
        for (int i = 2; i < len; ++i) {
            //三数之间成等差数列
            //加上的1是以A[i]结尾的A[i-2, i-1, i], 剩下的是将以A[i-1]结尾的每一个slice加上A[i]
            if (A[i] - A[i - 1] == A[i - 1] - A[i - 2])
                dp[i] = dp[i - 1] + 1;
                
            //res加上每个可以形成等差数列的位置上的dp值
            res += dp[i];
        }
        return res;
    }
}

