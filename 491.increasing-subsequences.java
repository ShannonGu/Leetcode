import java.util.Set;

/*
 * @lc app=leetcode id=491 lang=java
 *
 * [491] Increasing Subsequences
 */
class Solution {
    private List<List<Integer>> res;

    public List<List<Integer>> findSubsequences(int[] nums) {
        res = new ArrayList<>();
        helper(nums, 0, new ArrayList<>());
        return res;
    }
    
    private void helper(int[] nums, int pos, List<Integer> out) {
        if (out.size() >= 2) {
            res.add(new ArrayList<>(out));
        }

        //去重,同一层递归中，不能使用两个相同的数字
        Set<Integer> set = new HashSet<>();
        for (int i = pos; i < nums.length; ++i) {
            if (out.size() > 0 && nums[i] < out.get(out.size() - 1) || set.contains(nums[i]))
                continue;
            set.add(nums[i]);
            out.add(nums[i]);
            helper(nums, i + 1, out);
            out.remove(out.size() - 1);
        }
    }
}

