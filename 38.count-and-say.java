/*
 * @lc app=leetcode id=38 lang=java
 *
 * [38] Count and Say
 */
class Solution {
    //https://www.cnblogs.com/grandyang/p/4086299.html
    public String countAndSay(int n) {
        if(n < 1)
            return "";
        String res = "1";
        while (n > 1) {
            StringBuilder cur = new StringBuilder();
            for (int i = 0; i < res.length(); ++i) {
                int cnt = 1;
                while (i + 1 < res.length() && res.charAt(i) == res.charAt(i + 1)) {
                    ++cnt;
                    ++i;
                }
                cur.append(cnt).append(String.valueOf(res.charAt(i)));
            }
            res = cur.toString();
            --n;
        }
        return res;
    }
}

