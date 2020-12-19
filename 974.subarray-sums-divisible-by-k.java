/*
 * @lc app=leetcode id=974 lang=java
 *
 * [974] Subarray Sums Divisible by K
 */
class Solution {
    // https://leetcode.com/problems/subarray-sums-divisible-by-k/solution/
    public int subarraysDivByK(int[] A, int K) {
        // 将前缀和sum mod K和该余数出现的次数映射起来
        Map<Integer, Integer> m = new HashMap<>();
        // 初始化，防止后面出现余数为0的情况
        m.put(0, 1);
        int sum = 0, res = 0;
        for (int i = 0; i < A.length; ++i) {
            sum += A[i];
            // 防止出现负数的sum
            int t = (sum % K + K) % K;
            m.put(t, m.getOrDefault(t, 0) + 1);
        }

        Iterator it = m.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry e = (Map.Entry) it.next();
            int cnt = (Integer) e.getValue();

            // 对K具有相同余数的两个前缀sum,之间的差一定是K的倍数
            // 这些前缀sum之间的每个组合得到的差都是K的倍数
            // 所以求组合总数
            res += cnt * (cnt - 1) / 2;
        }
        return res;
    }
}
