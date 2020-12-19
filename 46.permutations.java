import java.util.Arrays;
import java.util.List;

/*
 * @lc app=leetcode id=46 lang=java
 *
 * [46] Permutations
 */
class Solution {
    // https://leetcode.com/problems/permutations/discuss/18239/A-general-approach-to-backtracking-questions-in-Java-(Subsets-Permutations-Combination-Sum-Palindrome-Partioning)
    List<List<Integer>> res;

    public List<List<Integer>> permute(int[] nums) {
        res = new ArrayList<>();
        if (nums.length == 0)
            return res;
        boolean[] visited = new boolean[nums.length];
        helper(nums, new ArrayList<>(), visited);
        return res;
    }

    private void helper(int[] nums, List<Integer> temp, boolean[] visited) {
        if (temp.size() == nums.length) {
            res.add(new ArrayList<>(temp));
            return;
        }

        for (int i = 0; i < nums.length; ++i) {
            if (visited[i])
                continue;
            visited[i] = true;
            temp.add(nums[i]);
            helper(nums, temp, visited);
            temp.remove(temp.size() - 1);
            visited[i] = false;
        }
    }
}
