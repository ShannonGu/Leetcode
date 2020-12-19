import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/*
 * @lc app=leetcode id=239 lang=java
 *
 * [239] Sliding Window Maximum
 */
class Solution {
    //https://www.cnblogs.com/grandyang/p/4656517.html
    public int[] maxSlidingWindow(int[] nums, int k) {
        //单调队列，维护一个单调递减的队列
        if (k > nums.length || k == 0)
            return new int[] {};
        Deque<Integer> q = new LinkedList<>();
        // List<Integer> res = new ArrayList<>();
        int[] res = new int[nums.length - k + 1];
        int cnt = 0;
        for (int i = 0; i < nums.length; ++i) {
            //若队头元素超过了窗口尺寸，则出队
            if (!q.isEmpty() && q.peekFirst() == i - k)
                q.pollFirst();
            //若当前遍历的元素大于队尾元素，则队尾元素不可能成为当前窗口的最大值
            while (!q.isEmpty() && nums[i] > nums[q.peekLast()])
                q.pollLast();
            //而若当前元素比队尾元素小，则有可能成为之后的窗口的最大值
            q.offerLast(i);
            //若当前元素位置>=k-1,说明已经开始形成窗口了
            //此时队头就是当前窗口的最大值
            if (i >= k - 1)
                // res.add(nums[q.peekFirst()]);
                res[cnt++] = nums[q.peekFirst()];
        }
        // return res.stream().mapToInt(Integer::intValue).toArray();
        return res;
    }
}
