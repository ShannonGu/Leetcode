import java.util.Iterator;
import java.util.Map;

/*
 * @lc app=leetcode id=3 lang=java
 *
 * [3] Longest Substring Without Repeating Characters
 */
class Solution {
    //https://leetcode.com/problems/longest-substring-without-repeating-characters/solution/
    // public int lengthOfLongestSubstring(String s) {
    //     if(s.length() < 2)
    //         return s.length();
    //     Set<Character> set = new HashSet<>();
    //     int i = 0, j = 0, res = 0;
    //     int n = s.length();
    //     while (j < n) {
    //         if (!set.contains(s.charAt(j))) {
    //             set.add(s.charAt(j++));
    //             res = Math.max(res, j - i);
    //         } else {
    //             set.remove(s.charAt(i++));
    //         }
    //     }
    //     return res;
    // }

    public int lengthOfLongestSubstring(String s) {
        int n = s.length(), res = 0;
        Map<Character, Integer> map = new HashMap<>();
        for (int j = 0, i = 0; j < n; ++j) {
            if (map.containsKey(s.charAt(j))) {
                // "abba"
                i = Math.max(map.get(s.charAt(j)), i);
            }
            res = Math.max(res, j - i + 1);
            map.put(s.charAt(j), j + 1);
        }
        return res;
    }
}

