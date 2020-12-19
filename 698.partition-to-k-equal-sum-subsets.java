/*
 * @lc app=leetcode id=698 lang=java
 *
 * [698] Partition to K Equal Sum Subsets
 */
class Solution {
    //https://www.cnblogs.com/grandyang/p/7733098.html
    //https://leetcode.com/problems/partition-to-k-equal-sum-subsets/discuss/335668/DP-with-Bit-Masking-Solution-%3A-Best-for-Interviews
    private boolean[] visited;
    public boolean canPartitionKSubsets(int[] nums, int k) {
        if (k == 0)
            return false;
        int sum = 0;
        for (int num : nums)
            sum += num;
        if (sum % k != 0)
            return false;
        nums = IntStream.of(nums).boxed().sorted(Comparator.reverseOrder()).mapToInt(Integer::intValue).toArray();
        int n = nums.length;
        visited = new boolean[n];
        return helper(nums, sum / k, 0, 0, k);
    }
    
    private boolean helper(int[] nums, int target, int start, int curSum, int k) {
        if(k == 1)
            return true;
        if(curSum > target)
            return false;
        if(curSum == target)
            return helper(nums, target, 0, 0, k - 1);
        for (int i = start; i < nums.length; ++i) {
            if (visited[i])
                continue;
            visited[i] = true;
            if (helper(nums, target, i + 1, curSum + nums[i], k))

                return true;
            visited[i] = false;
        }
        return false;
    }
}
