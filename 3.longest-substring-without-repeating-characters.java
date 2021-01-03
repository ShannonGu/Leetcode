import java.util.Iterator;
import java.util.Map;

/*
 * @lc app=leetcode id=3 lang=java
 *
 * [3] Longest Substring Without Repeating Characters
 */
class Solution {
    // https://leetcode.com/problems/longest-substring-without-repeating-characters/solution/
    public int lengthOfLongestSubstring(String s) {
        if (s.length() < 2)
            return s.length();
        int n = s.length(), res = 0;
        // 将每一个字符与与其上一次出现的位置的下一个位置建立映射
        // 因为如果该字符出现重复，子串的起始位置直接从下一个位置考虑
        Map<Character, Integer> map = new HashMap<>();
        // last标志子串的起始位置
        int last = 0;
        for (int front = 0; front < n; front++) {
            // 如果有重复字符出现，需要更新下一个子串的起始位置
            if (map.containsKey(s.charAt(front))) {
                // last始终保持距离front最近的位置，防止出现"abba"这种情况
                last = Math.max(map.get(s.charAt(front)), last);
            }
            // 更新最长不重复子串的长度
            res = Math.max(res, front - last + 1);
            map.put(s.charAt(front), front + 1);
        }
        return res;
    }
}
