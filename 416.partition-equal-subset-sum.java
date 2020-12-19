/*
 * @lc app=leetcode id=416 lang=java
 *
 * [416] Partition Equal Subset Sum
 *
 * https://leetcode.com/problems/partition-equal-subset-sum/description/
 *
 * algorithms
 * Medium (40.63%)
 * Likes:    1236
 * Dislikes: 35
 * Total Accepted:    88.2K
 * Total Submissions: 216.7K
 * Testcase Example:  '[1,5,11,5]'
 *
 * Given a non-empty array containing only positive integers, find if the array
 * can be partitioned into two subsets such that the sum of elements in both
 * subsets is equal.
 * 
 * Note:
 * 
 * 
 * Each of the array element will not exceed 100.
 * The array size will not exceed 200.
 * 
 * 
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: [1, 5, 11, 5]
 * 
 * Output: true
 * 
 * Explanation: The array can be partitioned as [1, 5, 5] and [11].
 * 
 * 
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: [1, 2, 3, 5]
 * 
 * Output: false
 * 
 * Explanation: The array cannot be partitioned into equal sum subsets.
 * 
 * 
 * 
 * 
 */
class Solution {
    //https://www.cnblogs.com/grandyang/p/5951422.html
    //0-1背包问题
    // public boolean canPartition(int[] nums) {
    //     int sum = 0;
    //     for (int num : nums) {
    //         sum += num;
    //     }
    //     if ((sum & 1) == 1)
    //         return false;
    //     int target = sum / 2, n = nums.length;
    //     //dp[i][j] 表示[0, i]中是否可以取出一个子集满足其和等于j
    //     //于是最终结果就是返回dp[n][target];
    //     boolean[][] dp = new boolean[n + 1][target + 1];
    //     for (int i = 0; i <= n; ++i) {
    //         dp[i][0] = true;
    //     }
    //     for (int i = 0; i <= target; ++i) {
    //         dp[0][i] = false;
    //     }
    //     dp[0][0] = true;
    //     for (int i = 1; i <= n; ++i) {
    //         for (int j = target; j >= nums[i - 1]; --j) {
    //             dp[i][j] = dp[i - 1][j] || dp[i - 1][j - nums[i - 1]];
    //         }
    //     }
    //     return dp[n][target];
    // }
    
    //0-1背包优化
    public boolean canPartition(int[] nums) {
        int sum = 0;
        for(int num : nums)
            sum += num;
        if((sum & 1) == 1)
            return false;

        int target = sum >> 1;
        boolean[] dp = new boolean[target + 1];
        dp[0] = true;
        for (int num : nums) {
            for (int i = target; i >= num; --i) {
                dp[i] = dp[i] || dp[i - num];
            }
        }
        return dp[target];
    }
}

