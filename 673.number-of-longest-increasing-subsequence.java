/*
 * @lc app=leetcode id=673 lang=java
 *
 * [673] Number of Longest Increasing Subsequence
 */
class Solution {
    // https://leetcode.com/problems/number-of-longest-increasing-subsequence/solution/
    // https://www.cnblogs.com/grandyang/p/7603903.html
    public int findNumberOfLIS(int[] nums) {
        int n = nums.length;
        // len[i]表示以nums[i]为结尾的递增子序列的长度
        // cnt[i]表示以nums[i]为结尾的递增子序列的个数
        int[] len = new int[n], cnt = new int[n];
        int mx = 1, res = 0;
        Arrays.fill(len, 1);
        Arrays.fill(cnt, 1);
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < i; ++j) {
                if (nums[i] <= nums[j])
                    continue;

                if (len[i] == len[j] + 1)
                    cnt[i] += cnt[j];
                else if (len[i] < len[j] + 1) {
                    len[i] = len[j] + 1;
                    cnt[i] = cnt[j];
                }
            }
            mx = Math.max(mx, len[i]);
        }
        
        for (int i = 0; i < n; ++i) {
            if (mx == len[i])
                res += cnt[i];
        }
        return res;
    }


    //solution 2 
    //Segment Tree
}
