import java.util.Queue;
import java.util.Set;
import java.util.Stack;

/*
 * @lc app=leetcode id=32 lang=java
 *
 * [32] Longest Valid Parentheses
 */
class Solution {
    // public int longestValidParentheses(String s) {
    //     if(s.length() < 2)
    //         return 0;
    //     int n = s.length(), res = 0;
    //     //dp[i]表示以s[i-1]结尾的前i个字符的longest valid parentheses的长度
    //     int[] dp = new int[n + 1];
    //     for (int i = 1; i <= n; ++i) {
    //         //s[i-1]=='(', 以s[i-1]结尾invalid
    //         if (s.charAt(i - 1) == '(')
    //             dp[i] = 0;
    //         else {
    //             //可能存在valid parenthese
    //             //取以s[i-2]结尾的longest valid parenthese的之前的那个字符
    //             //i-2为s[i-2]的下标，dp[i-1]即以s[i-2]结尾的longest valid parenthese的长度
    //             int j = i - 2 - dp[i - 1];

    //             //j < 0 的情况"())"
    //             if (j < 0 || s.charAt(j) == ')')
    //                 dp[i] = 0;
    //             else {
    //                 //dp[j]为以s[j-1]结尾的长度
    //                 dp[i] = dp[j] + 2 + dp[i - 1];
    //                 res = Math.max(res, dp[i]);
    //             }
    //         }
    //     }
    //     return res;
    // }


    public int longestValidParentheses(String s) {
        if(s.length() < 2)
            return 0;
        //利用栈存放'('的下标
        Stack<Integer> st = new Stack<>();
        //start用于标记栈中第一个'('的位置
        int start = 0, res = 0;
        for (int i = 0; i < s.length(); ++i) {
            //遇到 '('就入栈
            if (s.charAt(i) == '(')
                st.push(i);
            else {
                //遇到 ')'
                //若栈为空,此时invalid
                //寻找下一个 '('
                if (st.isEmpty())
                    start = i + 1;
                else {
                    //弹出一个 '('
                    st.pop();
                    //如果此时栈为空,说明栈中'('全部配对,此时更新res为i - start + 1
                    //不为空,更新res为i-st.peek();
                    res = st.isEmpty() ? Math.max(res, i - start + 1) : Math.max(res, i - st.peek());
                }
            }
        }
        return res;
    }
}

