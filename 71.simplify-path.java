/*
 * @lc app=leetcode id=71 lang=java
 *
 * [71] Simplify Path
 */
class Solution {
    // https://www.cnblogs.com/grandyang/p/4347125.html
    public String simplifyPath(String path) {
        Stack<String> st = new Stack<>();
        String[] p = path.split("/");
        for (String t : p) {
            if (!st.isEmpty() && t.equals("..")) {
                st.pop();
            } else if (!t.equals(".") && !t.equals("") && !t.equals("..")) {
                st.push(t);
            }
        }

        StringBuilder res = new StringBuilder("");
        while (!st.isEmpty()) {
            res.insert(0, st.pop()).insert(0, "/");
        }
        if (res.length() == 0)
            res.append("/");
        return res.toString();
    }
}
