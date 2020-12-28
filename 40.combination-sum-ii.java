import java.util.Arrays;
import java.util.List;

/*
 * @lc app=leetcode id=40 lang=java
 *
 * [40] Combination Sum II
 */

// @lc code=start
class Solution {
    private List<List<Integer>> res;

    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        Arrays.sort(candidates);
        res = new ArrayList<>();
        List<Integer> out = new ArrayList<>();
        helper(candidates, out, target, 0);
        return res;
    }

    private void helper(int[] candidates, List<Integer> out, int target, int level) {

        if (target == 0) {
            res.add(new ArrayList<>(out));
            return;
        } else if (target < 0)
            return;

        for (int i = level; i < candidates.length; i++) {
            // 去重
            if (i > level && candidates[i] == candidates[i - 1])
                continue;
            out.add(candidates[i]);
            // 注意这里是i+1而不是level+1
            helper(candidates, out, target - candidates[i], i + 1);
            out.remove(out.size() - 1);
        }
    }
}
// @lc code=end
