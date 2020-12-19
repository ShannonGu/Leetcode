/*
 * @lc app=leetcode id=47 lang=java
 *
 * [47] Permutations II
 */
class Solution {
    //https://leetcode.com/problems/permutations/discuss/18239/A-general-approach-to-backtracking-questions-in-Java-(Subsets-Permutations-Combination-Sum-Palindrome-Partioning)
    private List<List<Integer>> res;

    public List<List<Integer>> permuteUnique(int[] nums) {
        res = new ArrayList<>();
        boolean[] visited = new boolean[nums.length];
        Arrays.sort(nums);
        helper(nums, new ArrayList<>(), visited);
        return new ArrayList<>(res);
    }
    
    private void helper(int[] nums, List<Integer> temp, boolean[] visited) {
        if (temp.size() == nums.length) {
            res.add(new ArrayList<>(temp));
            return;
        }

        for (int i = 0; i < nums.length; ++i) {
            if (visited[i] || i > 0 && nums[i] == nums[i - 1] && !visited[i - 1])
                continue;
            visited[i] = true;
            temp.add(nums[i]);
            helper(nums, temp, visited);
            temp.remove(temp.size() - 1);
            visited[i] = false;
        }
    }

}

