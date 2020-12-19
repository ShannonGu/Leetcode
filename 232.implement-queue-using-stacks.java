/*
 * @lc app=leetcode id=232 lang=java
 *
 * [232] Implement Queue using Stacks
 */
class MyQueue {

    Stack<Integer> st1, st2;
    /** Initialize your data structure here. */
    public MyQueue() {
        st1 = new Stack<>();
        st2 = new Stack<>();
    }
    
    /** Push element x to the back of queue. */
    public void push(int x) {
        st1.push(x);
    }
    
    /** Removes the element from in front of queue and returns that element. */
    public int pop() {
        shiftSt();
        int res = st2.pop();
        return res;
    }
    
    /** Get the front element. */
    public int peek() {
        shiftSt();
        int res = st2.peek();
        return res;
    }
    
    /** Returns whether the queue is empty. */
    public boolean empty() {
        return st2.isEmpty() && st1.isEmpty();
    }

    public void shiftSt() {
        if(!st2.isEmpty())
            return;
        while (!st1.isEmpty()) {
            st2.push(st1.pop());
        }
    }
}

/**
 * Your MyQueue object will be instantiated and called as such:
 * MyQueue obj = new MyQueue();
 * obj.push(x);
 * int param_2 = obj.pop();
 * int param_3 = obj.peek();
 * boolean param_4 = obj.empty();
 */

