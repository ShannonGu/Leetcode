import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

/*
 * @lc app=leetcode id=918 lang=java
 *
 * [918] Maximum Sum Circular Subarray
 */
class Solution {
    //https://leetcode.com/problems/maximum-sum-circular-subarray/solution/
    //Approach 2
    public int maxSubarraySumCircular(int[] A) {
        //单调队列，维护一个单调递增的队列
        int n = A.length;
        //前缀和sums数组
        int[] preSums = new int[2 * n + 1];
        //将数组A整体添加到A的末尾
        for (int i = 0; i < 2 * n; ++i) {
            preSums[i + 1] = preSums[i] + A[i % n];
        }

        int res = A[0];
        Deque<Integer> q = new LinkedList<>();

        q.offer(0);
        for (int i = 1; i <= 2 * n; ++i) {
            //因为窗口中不能出现两次同一个元素
            //也即窗口大小最大为n
            if (q.peekFirst() < i - n)
                q.pollFirst();
            
            //求窗口中最大子数组的和
            res = Math.max(res, preSums[i] - preSums[q.peekFirst()]);

            //维护队列单调递增
            //因为对于后面的前缀和来说，如果队列不是递增的，
            //队列前面的某些和比当前的前缀和大，求最大子数组的和时，一定不满足要求
            //要使得留在队列中的前缀和尽量小的递增，才可能求出最大的子数组之和
            while (!q.isEmpty() && preSums[i] <= preSums[q.peekLast()])
                q.pollLast();
            q.offerLast(i);
        }

        return res;
    }
}

