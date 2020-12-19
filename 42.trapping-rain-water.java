import java.util.Stack;

/*
 * @lc app=leetcode id=42 lang=java
 *
 * [42] Trapping Rain Water
 */
class Solution {
    //https://leetcode.com/problems/trapping-rain-water/solution/
    // public int trap(int[] height) {
    //     //单调栈解法
    //     //维护一个单调递减的栈，将元素下标存入,
    //     //如果遇到一个元素比当前栈顶元素大，说明形成了一个坑
    //     //可以收集雨水
    //     int n = height.length, res = 0;
    //     int cur = 0;
    //     Stack<Integer> st = new Stack<>();
    //     while (cur < n) {
    //         //栈不空且当前元素比栈顶元素大
    //         //可以形成坑收集雨水
    //         while (!st.isEmpty() && height[cur] > height[st.peek()]) {
    //             //栈顶元素下标
    //             int low = st.pop();
    //             //若此时栈空，说明已经需要寻找新的坑
    //             if (st.isEmpty())
    //                 break;
    
    //             //坑左边最近的高点
    //             int left = st.peek();
    //             
    //             int distance = cur - left - 1;
    //             int deltaH = Math.min(height[left], height[cur]) - height[low];
    //             //若是连续的平地低点，这里distance * deltaH == 0;
    //             res += distance * deltaH;
    //         }

    //         //栈空或者元素递减，则入栈
    //         st.push(cur++);
    //     }
    //     return res;
    // }


    //DP
    public int trap(int[] height) {
        if(height.length == 0)
            return 0;
        int n = height.length;
        //leftMax[i]表示[0,i]之间的最高高度
        //rightMax[i]表示[i, n-1]之间的最高高度
        int[] leftMax = new int[n], rightMax = new int[n];
        leftMax[0] = height[0];
        for (int i = 1; i < n; ++i) {
            leftMax[i] = Math.max(leftMax[i - 1], height[i]);
        }

        rightMax[n - 1] = height[n - 1];
        for (int i = n - 2; i >= 0; --i) {
            rightMax[i] = Math.max(rightMax[i + 1], height[i]);
        }

        int res = 0;
        //用两个方向当前位置的最高点的最小值减去当前位置的高度
        for (int i = 0; i < n; ++i) {
            res += Math.min(leftMax[i], rightMax[i]) - height[i];
        }
        return res;
    }
}

