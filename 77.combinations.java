import java.util.Arrays;
import java.util.stream.Collector;
import java.util.stream.Collectors;

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
        helper(n, 0, 1, new int[k], k);
        return res;
    }

    private void helper(int n, int cnt, int pos, int[] out, int k) {
        // out数组用于临时存放每个组合
        if (cnt == k) {
            res.add(Arrays.stream(out).boxed().collect(Collectors.toList()));
            return;
        }

        for (int i = pos; i <= n; ++i) {
            out[cnt++] = i; // 修改当前数状态
            helper(n, cnt, i + 1, out, k);
            --cnt; // 还原当前数状态
        }
    }
}
// @lc code=end
