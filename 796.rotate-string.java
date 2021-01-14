/*
 * @lc app=leetcode id=796 lang=java
 *
 * [796] Rotate String
 */

// @lc code=start
class Solution {
    public boolean rotateString(String A, String B) {
        if (A.length() != B.length())
            return false;

        if (A.length() == 0)
            return true;

        // 小技巧：涉及旋转的不妨考虑将两个字符串叠加
        String tmp = A + A;
        int n = B.length();
        // 利用kmp算法在考察模板串B是否为模式串tmp的子串
        // https://www.acwing.com/activity/content/code/content/67064/
        // 先求next数组
        int[] next = new int[n];
        next[0] = -1;
        for (int i = 1, j = -1; i < n; i++) {
            while (j > -1 && B.charAt(i) != B.charAt(j + 1))
                j = next[j];
            if (B.charAt(i) == B.charAt(j + 1))
                ++j;
            next[i] = j;
        }

        // 在tmp中匹配B
        for (int i = 0, j = -1; i < tmp.length(); i++) {
            while (j > -1 && tmp.charAt(i) != B.charAt(j + 1))
                j = next[j];
            if (tmp.charAt(i) == B.charAt(j + 1))
                ++j;
            // 匹配到直接返回true
            if (j == n - 1)
                return true;
        }
        // tmp中没有B
        return false;
    }
}
// @lc code=end
