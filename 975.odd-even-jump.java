import java.util.Map;
import java.util.TreeMap;

/*
 * @lc app=leetcode id=975 lang=java
 *
 * [975] Odd Even Jump
 */

// @lc code=start
class Solution {
    // https://zxi.mytechroad.com/blog/dynamic-programming/leetcode-975-odd-even-jump/
    public int oddEvenJumps(int[] A) {
        int n = A.length;
        // 将每个数与其下标映射
        TreeMap<Integer, Integer> m = new TreeMap();
        // dp[i][0]表示在位置i是否可以通过up jump到达末尾
        // dp[i][1]表示在位置i是否可以通过down jump到达末尾
        boolean[][] dp = new boolean[n][2];
        // 最后一个位置可以不用跳可以直接到达
        dp[n - 1][0] = dp[n - 1][1] = true;

        m.put(A[n - 1], n - 1);

        //最后一个位置一定可以
        int ans = 1;

        // 从后向前遍历
        // 分别考虑每个位置up, down jump情况
        for (int i = n - 2; i >= 0; --i) {
            // 首先考虑up jump的情况
            // 根据规则,在其后面的数中找到比当前数大的数中的最小数,如果相同就找最接近当前位置的数
            Map.Entry me1 = m.ceilingEntry(A[i]);
            // 如果存在，只要判断在该数时的down jump是否能达到结尾
            if (me1 != null) {
                // 取该数的位置信息idx
                int idx = (Integer) me1.getValue();
                // 将该数的down jump情况赋给当前位置up jump情况
                dp[i][0] = dp[idx][1];
            }

            // 然后考虑当前位置的down jump情况
            // 在其后面的数中找到比当前小的数中的最大数,如果相同就找最接近当前位置的数
            Map.Entry me2 = m.floorEntry(A[i]);
            // 如果存在
            if (me2 != null) {
                // 取得该数的位置idx
                int idx = (Integer) me2.getValue();
                // 将该数的up jump情况赋给当前位置的down jump情况
                dp[i][1] = dp[idx][0];
            }

            // 因为从每个位置都是从up jump开始
            // 所以要判断每个位置的up jump是否能到达结尾
            if (dp[i][0])
                ++ans;
            // 存储每个数的位置信息
            m.put(A[i], i);
        }
        return ans;
    }
}
// @lc code=end
