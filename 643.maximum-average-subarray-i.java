/*
 * @lc app=leetcode id=643 lang=java
 *
 * [643] Maximum Average Subarray I
 */
class Solution {
    public double findMaxAverage(int[] nums, int k) {
        if (nums.length < k)
            return 0.0;
        int n = nums.length;
        int res = Integer.MIN_VALUE, cnt = 1, sum = 0;
        for (int i = 0; i < n; ++i) {
            if(i >= k)
                sum -= nums[i - k];
            sum += nums[i];
            if(cnt == k)
                res = Math.max(res, sum);
            else if(cnt < k)
                ++cnt;
        }
        return (double) (res) / k;
    }
}
