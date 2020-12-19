import java.util.Map;

/*
 * @lc app=leetcode id=523 lang=java
 *
 * [523] Continuous Subarray Sum
 */
class Solution {
    // 此题和525,560类似，都是将累加和与数的下标建立映射
    // 用来求解子数组是否满足某种条件

    // 基于这样一个事实
    // 若a和b分别除以c,得到的余数相同，那么(a-b)必定能够被c整除
    // 于是可以通过一个hash table将余数与当前数的位置建立映射
    // 因为最少需要两个连续元素，所以当一个连续子数组的余数已经存在与hashtable中了
    // 则说明之前存在可以被k整除的子数组的和
    public boolean checkSubarraySum(int[] nums, int k) {
        Map<Integer, Integer> m = new HashMap<>();
        // 初始化,因为子数组的长度至少要2
        m.put(0, -1);
        int sum = 0;
        for (int i = 0; i < nums.length; ++i) {
            sum += nums[i];

            // 注意除数k不能为0
            // 任何sum都是0的倍数
            int t = k == 0 ? sum : sum % k;

            if (m.containsKey(t)) {
                // 说明在m.get(t)出余数也为k;
                // 子数组[m.get(t), i]的和是k的倍数
                if (i - m.get(t) > 1)
                    return true;
            } else
                m.put(t, i);
        }
        return false;
    }
}
