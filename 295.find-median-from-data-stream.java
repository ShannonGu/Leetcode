import java.util.PriorityQueue;
import java.util.Queue;

/*
 * @lc app=leetcode id=295 lang=java
 *
 * [295] Find Median from Data Stream
 */
class MedianFinder {
    Queue<Integer> mx, mn;
    /** initialize your data structure here. */
    public MedianFinder() {
        //利用大顶堆和小顶堆
        //保证大顶堆的所有数 < 小顶堆的所有数
        mn = new PriorityQueue<>((v1, v2) -> v1 - v2);
        mx = new PriorityQueue<>((v1, v2) -> v2 - v1);
    }
    
    public void addNum(int num) {
        //为了实现平均分配，在当前数据总数为偶数时，将新数据插入小顶堆
        //因为新插入的数可能会小于大顶堆的数据
        //所以需要将新数据先插入大顶堆，调整后，将大顶堆的最大数插入到小顶堆，再对小顶堆进行调整
        if(((mx.size() + mn.size()) & 1) == 0){
            //先插入大顶堆
            if(mx.size() > 0 && num < mx.peek()){
                mx.offer(num);
                num = mx.poll();
            }
            mn.offer(num);
        }else{
            //若当前数据总数是奇数，将新数据插入大顶堆
            //同理，需要判断新数据是否需要先插入小顶堆
            if (mn.size() > 0 && num > mn.peek()) {
                mn.offer(num);
                num = mn.poll();
            }
            mx.offer(num);
        }
    }
    
    public double findMedian() {
        int size = mx.size() + mn.size();
        if(size == 0)
            return 0.0;
        
        if ((size & 1) == 0) {
            return (double) (mx.peek() + mn.peek()) * 0.5;
        }
        else {
            return mn.peek() * 1.0;
        }
    }
}

/**
 * Your MedianFinder object will be instantiated and called as such:
 * MedianFinder obj = new MedianFinder();
 * obj.addNum(num);
 * double param_2 = obj.findMedian();
 */

