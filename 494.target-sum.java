import java.util.Arrays;

/*
 * @lc app=leetcode id=494 lang=java
 *
 * [494] Target Sum
 */
class Solution {
    int[][] memo;

    public int findTargetSumWays(int[] nums, int S) {
        // memo[i][sum]表示到第i个数和为sum的情况数
        // 由于-1000<=sum<=1000，因此需要加上1000以避免出现负数，数组无法访问
        memo = new int[nums.length][2001];
        // 初始化
        for (int[] e : memo) {
            Arrays.fill(e, Integer.MIN_VALUE);
        }
        return helper(nums, 0, 0, S);
    }

    private int helper(int[] nums, int i, int sum, int S) {
        // 已经遍历到最后一个数
        if (i == nums.length) {
            // 可以组合得到S
            if (sum == S)
                return 1;
            else
                return 0;
        }

        // 该种情况已经存在，直接返回
        if (memo[i][sum + 1000] != Integer.MIN_VALUE)
            return memo[i][sum + 1000];
        // +当前位置的数
        int add = helper(nums, i + 1, sum + nums[i], S);
        // -当前位置的数
        int subtract = helper(nums, i + 1, sum - nums[i], S);
        memo[i][sum + 1000] = add + subtract;
        return memo[i][sum + 1000];
    }
}
