/*
 * @lc app=leetcode id=22 lang=java
 *
 * [22] Generate Parentheses
 */

// @lc code=start
class Solution {
    // https://www.cnblogs.com/grandyang/p/4444160.html
    public List<String> generateParenthesis(int n) {
        List<String> res = new ArrayList<>();
        helper(n, n, "", res);
        return res;
    }

    private void helper(int left, int right, String out, List<String> res) {
        if(left < 0 || right < 0 || left > right)
            return;
        if (left == 0 && right == 0) {
            res.add(out);
            return;
        }

        helper(left - 1, right, out + "(", res);
        helper(left, right - 1, out + ")", res);
    }
}
// @lc code=end
