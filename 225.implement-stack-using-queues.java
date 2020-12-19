/*
 * @lc app=leetcode id=225 lang=java
 *
 * [225] Implement Stack using Queues
 */

// @lc code=start
class MyStack {

    Deque<Integer> q;
    /** Initialize your data structure here. */
    public MyStack() {
        q = new LinkedList<>();
    }
    
    /** Push element x onto stack. */
    public void push(int x) {
        q.offerLast(x);
    }
    
    /** Removes the element on top of the stack and returns that element. */
    public int pop() {
        if(q.isEmpty())
            return -1;
        int res = q.pollLast();
        return res;
    }
    
    /** Get the top element. */
    public int top() {
        if(q.isEmpty())
            return -1;
        return q.peekLast();
    }
    
    /** Returns whether the stack is empty. */
    public boolean empty() {
        return q.isEmpty();
    }
}

/**
 * Your MyStack object will be instantiated and called as such:
 * MyStack obj = new MyStack();
 * obj.push(x);
 * int param_2 = obj.pop();
 * int param_3 = obj.top();
 * boolean param_4 = obj.empty();
 */
// @lc code=end

