/*
 * @lc app=leetcode id=89 lang=java
 *
 * [89] Gray Code
 */

// @lc code=start
class Solution {
    private List<Integer> res;
    private Set<Integer> set;

    public List<Integer> grayCode(int n) {
        res = new ArrayList<>();
        set = new HashSet<>();
        helper(n, 0);
        return res;
    }

    private void helper(int n, int out) {
        if (!set.contains(out)) {
            res.add(out);
            set.add(out);
        }

        // 从第0位开始遍历
        for (int i = 0; i < n; i++) {
            int t = out;
            // 对每一位取反
            if ((t & (1 << i)) == 0)
                t |= (1 << i);
            else
                t &= ~(1 << i);
            // 考察是否已经在set中
            if (set.contains(t))
                continue;
            // 递归进行
            helper(n, t);
            break;
        }
    }
}
// @lc code=end
