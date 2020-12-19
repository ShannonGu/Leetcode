/*
 * @lc app=leetcode id=622 lang=java
 *
 * [622] Design Circular Queue
 */

// @lc code=start
class MyCircularQueue {
    int[] nums;
    int head, tail;
    int cap;

    /** Initialize your data structure here. Set the size of the queue to be k. */
    public MyCircularQueue(int k) {
        cap = k;
        nums = new int[k + 1];
        head = 0;
        tail = 0;
    }
    
    /** Insert an element into the circular queue. Return true if the operation is successful. */
    public boolean enQueue(int value) {
        if(head == (tail + 1) % (cap + 1))
            return false;
        nums[tail] = value;
        tail = (tail + 1) % (cap + 1);
        return true;
    }
    
    /** Delete an element from the circular queue. Return true if the operation is successful. */
    public boolean deQueue() {
        if(head == tail)
            return false;
        head = (head + 1) % (cap + 1);
        return true;
    }
    
    /** Get the front item from the queue. */
    public int Front() {
        if(head == tail)
            return -1;
        return nums[head];
    }
    
    /** Get the last item from the queue. */
    public int Rear() {
        if(head == tail)
            return -1;
        return nums[(tail + cap + 1 - 1) % (cap + 1)];
    }
    
    /** Checks whether the circular queue is empty or not. */
    public boolean isEmpty() {
        return head == tail;
    }
    
    /** Checks whether the circular queue is full or not. */
    public boolean isFull() {
        return (tail + 1) % (cap + 1) == head;
    }
}

/**
 * Your MyCircularQueue object will be instantiated and called as such:
 * MyCircularQueue obj = new MyCircularQueue(k);
 * boolean param_1 = obj.enQueue(value);
 * boolean param_2 = obj.deQueue();
 * int param_3 = obj.Front();
 * int param_4 = obj.Rear();
 * boolean param_5 = obj.isEmpty();
 * boolean param_6 = obj.isFull();
 */
// @lc code=end

