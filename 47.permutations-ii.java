import java.util.List;

/*
 * @lc app=leetcode id=47 lang=java
 *
 * [47] Permutations II
 */
class Solution {
    // https://leetcode.com/problems/permutations/discuss/18239/A-general-approach-to-backtracking-questions-in-Java-(Subsets-Permutations-Combination-Sum-Palindrome-Partioning)
    private List<List<Integer>> res;

    public List<List<Integer>> permuteUnique(int[] nums) {
        res = new ArrayList<>();
        boolean[] visited = new boolean[nums.length];
        List<Integer> out = new ArrayList<>();
        Arrays.sort(nums);
        helper(nums, 0, out, visited);
        return res;
    }

    private void helper(int[] nums, int level, List<Integer> out, boolean[] visited) {
        if (level == nums.length) {
            res.add(new ArrayList<>(out));
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            // 去重
            if (visited[i] || i > 0 && nums[i] == nums[i - 1] && !visited[i - 1])
                continue;
            visited[i] = true;
            out.add(nums[i]);
            helper(nums, level + 1, out, visited);
            out.remove(out.size() - 1);
            visited[i] = false;
        }
    }
}
