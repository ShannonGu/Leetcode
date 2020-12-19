/*
 * @lc app=leetcode id=96 lang=java
 *
 * [96] Unique Binary Search Trees
 */
class Solution {
    // https://www.cnblogs.com/grandyang/p/4299608.html
    public int numTrees(int n) {
        // dp[i]表示当有i个数字能组才能BST的个数
        int[] dp = new int[n + 1];
        // 空树也是一颗BST
        // dp[1]以1为根，左右子树都为空树
        // dp[2] = dp[0] * dp[1] + dp[1] * dp[0]
        
        // n=2的情况是以1为根，左子树为空，右子树为2,dp[0]*dp[1]
        // 加上以2为根，右子树为空，左子树为1,dp[1] * dp[2];
        
        //n=3的情况类似，分别以1,2,3为根考虑
        dp[0] = dp[1] = 1;
        for (int i = 2; i <= n; ++i) {
            for (int j = 0; j < i; ++j) {
                dp[i] += dp[j] * dp[i - j - 1];
            }
        }
        return dp[n];
    }
}
