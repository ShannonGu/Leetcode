/*
 * @lc app=leetcode id=692 lang=java
 *
 * [692] Top K Frequent Words
 */
class Solution {
    // https://leetcode.com/problems/top-k-frequent-words/solution/
    // Approach 2

    // https://www.cnblogs.com/grandyang/p/7689927.html
    public List<String> topKFrequent(String[] words, int k) {
        Map<String, Integer> cnt = new HashMap();
        for (String word : words) {
            cnt.put(word, cnt.getOrDefault(word, 0) + 1);
        }
        // PriorityQueue<String> heap = new PriorityQueue<String>(
        // (w1, w2) -> cnt.get(w1).equals(cnt.get(w2)) ? w2.compareTo(w1) : cnt.get(w1)
        // - cnt.get(w2));

        // 这里首先根据单词的频率排序,频率小的在前面;
        // 若频率相同,则单词字典序大的在前面;
        // 因为这是小根堆,所以最终出队会是
        // 频率小，频率相同但字典序大的顺序
        // 最后将它们翻转会是频率大，频率相同但字典序小的顺序;
        Queue<String> heap = new PriorityQueue<String>(new Comparator<String>() {
            public int compare(String s1, String s2) {
                if (cnt.get(s1).equals(cnt.get(s2)))
                    // 注意这里是s2.compareTo(s1);
                    // 而不是s1.compareTo(s2);
                    // 因为要保证小根堆里字典序大的在前面;
                    // 最后答案翻转一下就变成频率相同字典序小的在前面;
                    return s2.compareTo(s1);
                else
                    return cnt.get(s1) - cnt.get(s2);
            }
        });
        for (String word : cnt.keySet()) {
            heap.offer(word);
            // 维持堆的容量始终为k;
            if (heap.size() > k)
                heap.poll();
        }

        List<String> res = new ArrayList();
        while (!heap.isEmpty())
            res.add(heap.poll());

        // 注意翻转;
        Collections.reverse(res);
        return res;
    }
}
