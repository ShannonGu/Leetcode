/*
 * @lc app=leetcode id=524 lang=java
 *
 * [524] Longest Word in Dictionary through Deleting
 */

// @lc code=start
class Solution {
    public String findLongestWord(String s, List<String> d) {
        String res = "";
        for (String str : d) {
            if (isSubsequence(str, s)) {
                if (str.length() >= res.length()) {
                    // 长度较长者或者字典序小者
                    if (str.length() > res.length() || str.compareTo(res) < 0)
                        res = str;
                }
            }
        }
        return res;
    }

    // 判断x是否为y的子串
    private boolean isSubsequence(String x, String y) {
        int i = 0;
        for (int j = 0; j < y.length() && i < x.length(); j++) {
            if (x.charAt(i) == y.charAt(j))
                i++;
        }
        return i == x.length();
    }
}
// @lc code=end
