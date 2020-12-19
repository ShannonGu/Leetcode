/*
 * @lc app=leetcode id=456 lang=java
 *
 * [456] 132 Pattern
 */
class Solution {
    // https://leetcode.com/problems/132-pattern/solution/
    // Approach 4:Stack
    public boolean find132pattern(int[] nums) {
        if (nums.length < 3)
            return false;

        Stack<Integer> st = new Stack<>();
        int n = nums.length;
        // min[i]表示[0,i]的最小值
        int[] min = new int[n];
        min[0] = nums[0];
        for (int i = 1; i < n; ++i) {
            min[i] = Math.min(min[i - 1], nums[i]);
        }

        // 从后向前遍历
        // 栈中记录的位置i后面的元素
        for (int i = n - 1; i >= 0; --i) {
            if (nums[j] > min[j]) {
                // 首先寻找三个数中最小和次小数
                while (!st.isEmpty() && st.peek() <= min[i])
                    st.pop();
                // 退出循环后, 如果栈不空, 说明栈顶元素是大于[0,i]的最小值
                // 如果此时栈顶元素大于nums[i], 而栈中元素均是位置i后面的元素
                // 于是三个数满足题目要求
                if (!st.isEmpty() && st.peek() < nums[i])
                    return true;
                st.push(nums[i]);
            }
        }
        return false;
    }
}
