import java.util.HashMap;
import java.util.Map;

/*
 * @lc app=leetcode id=205 lang=java
 *
 * [205] Isomorphic Strings
 */

// @lc code=start
class Solution {
    public boolean isIsomorphic(String s, String t) {
        if (s.length() != t.length())
            return false;
        int len = s.length();
        int[] m1 = new int[256], m2 = new int[256];
        for (int i = 0; i < len; i++) {
            char a = s.charAt(i), b = t.charAt(i);
            if (m1[a] != m2[b])
                return false;
            m1[a] = i + 1;
            m2[b] = i + 1;
        }
        return true;
    }
}
// @lc code=end
