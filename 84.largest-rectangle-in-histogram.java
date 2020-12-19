import java.util.Stack;

/*
 * @lc app=leetcode id=84 lang=java
 *
 * [84] Largest Rectangle in Histogram
 */
class Solution {
    //单调栈总结http://www.cnblogs.com/grandyang/p/8887985.html
    public int largestRectangleArea(int[] heights) {
        int n = heights.length;
        int[] heights_ = new int[n + 1];
        for (int i = 0; i < n; ++i) {
            heights_[i] = heights[i];
        }
        heights_[n] = 0;
        //维护一个单调递增栈
        Stack<Integer> st = new Stack<>();
        int res = 0, cur = 0;
        //cur表示当前遍历到的位置
        while (cur < n + 1) {
            //若栈不空，且当前位置高度小于栈顶位置
            while (!st.isEmpty() && heights_[cur] < heights_[st.peek()]) {
                //栈顶位置高度
                int hi = st.pop();
                //栈顶位置左边低点
                int left = st.isEmpty() ? -1 : st.peek();
                //当前形成的矩形的宽度
                int width = cur - left - 1;
                //更新最大矩形面积
                res = Math.max(res, heights_[hi] * width);
            }
            //栈空，或者高度递增则入栈
            st.push(cur++);
        }
        return res;
    }
}

