import java.util.Map;

/*
 * @lc app=leetcode id=560 lang=java
 *
 * [560] Subarray Sum Equals K
 */
class Solution {
    //类似题目325,525,523
    // https://www.cnblogs.com/grandyang/p/6810361.html
    public int subarraySum(int[] nums, int k) {

        // 建立连续子数组之和与该和出现的次数之间的映射;
        // 基于sum[i, j] = sum[0, j] - sum[0, i];
        Map<Integer, Integer> cnt = new HashMap<>();

        int res = 0, sum = 0, n = nums.length;
        // 这里需要初始化m[0]=1;
        // 目的在于当第一次某个连续子数组之和等于k时,可以加上该种情况;
        cnt.put(0, 1);
        for (int i = 0; i < n; ++i) {
            // 记录到数组当前元素的累加和;
            sum += nums[i];

            // 建立hash table的目的是快速查找sum-k是否存在;
            // 即是否有连续子数组的和为sum-k;
            // 若存在，则和为k的子数组也一定存在;
            // sum为[0, j]的累加和, 若sum-k存在,
            // 则表明存在sum[i, j] = k = sum[0, j] - sum[0, i];
            res += cnt.getOrDefault(sum - k, 0);
            
            cnt.put(sum, cnt.getOrDefault(sum, 0) + 1);
        }
        return res;
    }
}
