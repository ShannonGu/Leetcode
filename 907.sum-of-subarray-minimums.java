/*
 * @lc app=leetcode id=907 lang=java
 *
 * [907] Sum of Subarray Minimums
 */

// @lc code=start
class Solution {
    // https://zxi.mytechroad.com/blog/stack/leetcode-907-sum-of-subarray-minimums/
    // https://www.cnblogs.com/grandyang/p/11273330.html
    // 用到901题的思想
    private final int MOD = 1000000007;

    public int sumSubarrayMins(int[] A) {
        int n = A.length;
        // 维护两个单调栈
        Stack<Integer> nums = new Stack<>();
        Stack<Integer> lens = new Stack<>();
        // left[i]表示在位置i左边比A[i]大的连续子数组的长度
        int[] left = new int[n];
        // right[i]表示在位置i右边比A[i]大的连续子数组的长度
        int[] right = new int[n];

        // 左边有l个大于A[i],右边有r个大于A[i];
        // 于是有(l+1)*(r+1)个数组以A[i]为最小值

        int res = 0;

        // 先处理左边
        for (int i = 0; i < n; ++i) {
            int len = 1;
            while (!nums.isEmpty() && nums.peek() > A[i]) {
                len += lens.pop();
                nums.pop();
            }
            nums.push(A[i]);
            lens.push(len);
            left[i] = len;
        }

        nums.clear();
        lens.clear();

        // 处理右边
        for (int i = n - 1; i >= 0; --i) {
            int len = 1;
            while (!nums.isEmpty() && nums.peek() >= A[i]) {
                len += lens.pop();
                nums.pop();
            }
            nums.push(A[i]);
            lens.push(len);
            right[i] = len;
        }
        for (int i = 0; i < n; ++i) {
            res = (int) (res + (long) A[i] * left[i] * right[i]) % MOD;
        }
        return res;
    }
}
// @lc code=end
