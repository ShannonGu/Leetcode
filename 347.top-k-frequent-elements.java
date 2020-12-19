import java.util.HashMap;
import java.util.Map;
import java.util.Queue;

/*
 * @lc app=leetcode id=347 lang=java
 *
 * [347] Top K Frequent Elements
 */
class Solution {
    public List<Integer> topKFrequent(int[] nums, int k) {
        if(k > nums.length)
            return new ArrayList<>();
        List<Integer> res = new ArrayList<>();
        Map<Integer, Integer> cnt = new HashMap<>();

        for (int num : nums) {
            cnt.put(num, cnt.getOrDefault(num, 0) + 1);
        }

        //利用小根堆
        Queue<Integer> q = new PriorityQueue<>(
                (a, b) -> cnt.get(a).equals(cnt.get(b)) ? a.compareTo(b) : cnt.get(a).compareTo(cnt.get(b)));
        
        for (int num : cnt.keySet()) {
            q.offer(num);
            if (q.size() > k)
                q.poll();
        }
        
        while (!q.isEmpty()) {
            res.add(q.poll());
        }
        return res;
    }
}

