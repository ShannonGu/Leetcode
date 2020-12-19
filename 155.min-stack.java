import java.util.Stack;

/*
 * @lc app=leetcode id=155 lang=java
 *
 * [155] Min Stack
 */
class MinStack {

    // Stack<Integer> s1, s2;
    // /** initialize your data structure here. */
    // public MinStack() {
    //     s1 = new Stack<>();
    //     s2 = new Stack<>();
    // }

    // public void push(int x) {
    //     s1.push(x);
    //     if(s2.isEmpty() || x <= s2.peek())
    //         s2.push(x);
    // }

    // public void pop() {
    //     if (s1.peek().equals(s2.peek()))
    //         s2.pop();
    //     s1.pop();
    //     // int x = s1.pop();
    //     // if (s2.peek() == x)
    //     //     s2.pop();
    // }

    // public int top() {
    //     return s1.peek();
    // }

    // public int getMin() {
    //     return s2.peek();
    // }

    private int minVal = Integer.MAX_VALUE;
    private Stack<Integer> s;
    /** initialize your data structure here. */
    public MinStack() {
        s = new Stack<>();
    }
    
    public void push(int x) {
        //如果新加入的数比当前最小值要小
        //则将当前最小入栈，同时更新最小值
        if (x <= minVal) {
            s.push(minVal);
            minVal = x;
        }

        //再将当前值入栈
        s.push(x);
    }
    
    public void pop() {
        if(s.isEmpty())
            return;
        int x = s.pop();
        if(x == minVal)
            //由push函数操作可知,x下面的数为x入栈之前的最小值
            minVal = s.pop();
    }
    
    public int top() {
        return s.peek();
    }
    
    public int getMin() {
        return minVal;
    }
}

/**
 * Your MinStack object will be instantiated and called as such:
 * MinStack obj = new MinStack();
 * obj.push(x);
 * obj.pop();
 * int param_3 = obj.top();
 * int param_4 = obj.getMin();
 */

