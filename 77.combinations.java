/*
 * @lc app=leetcode id=77 lang=java
 *
 * [77] Combinations
 */

// @lc code=start
class Solution {
    private List<List<Integer>> res;

    public List<List<Integer>> combine(int n, int k) {
        if (n == 0)
            return new ArrayList<>();
        res = new ArrayList<>();
        helper(n, k, 1, new ArrayList<>());
        return res;
    }

    private void helper(int n, int k, int pos, List<Integer> out) {
        if (k == 0) {
            res.add(new ArrayList<>(out));
            return;
        }

        for (int i = pos; i <= n; ++i) {
            out.add(i);
            helper(n, k - 1, i + 1, out);
            out.remove(out.size() - 1);
        }
    }
}
// @lc code=end
