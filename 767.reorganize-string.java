import java.util.PriorityQueue;
import java.util.Queue;

/*
 * @lc app=leetcode id=767 lang=java
 *
 * [767] Reorganize String
 */
class Solution {
    //https://leetcode.com/problems/reorganize-string/solution/
    public String reorganizeString(String S) {
        //先统计每个字符的个数
        //如果超过字符串长度的一半
        //则不可能
        int n = S.length();
        int[] cnt = new int[26];
        for (char c : S.toCharArray()) {
            cnt[c - 'a']++;
        }
        //再利用大根堆对字符进行排序
        Queue<Character> q = new PriorityQueue<>(
                (c1, c2) -> cnt[c2 - 'a'] - cnt[c1 - 'a']);
                
        for (int i = 0; i < 26; ++i) {
            if (cnt[i] > 0) {
                //某个字符数量超过总数一半
                if (cnt[i] > (n + 1) / 2)
                    return "";
                q.offer((char) ('a' + i));
            }
        }
        
        StringBuilder res = new StringBuilder("");
        //每次取出次数最多的两个字符
        //并入res,修改次数后再继续存入大根堆
        while (q.size() >= 2) {
            char c1 = q.poll();
            char c2 = q.poll();
            res.append(c1);
            res.append(c2);
            if (--cnt[c1 - 'a'] > 0)
                q.offer(c1);
            if (--cnt[c2 - 'a'] > 0)
                q.offer(c2);
        }
        
        //将剩下的两个字符存入res中
        while((q.size() > 0))
            res.append(q.poll());
        return res.toString();
    }
}

