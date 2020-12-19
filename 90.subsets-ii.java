/*
 * @lc app=leetcode id=90 lang=java
 *
 * [90] Subsets II
 */
class Solution {
    //https://leetcode.com/problems/permutations/discuss/18239/A-general-approach-to-backtracking-questions-in-Java-(Subsets-Permutations-Combination-Sum-Palindrome-Partioning)
    List<List<Integer>> res;

    public List<List<Integer>> subsetsWithDup(int[] nums) {
        res = new ArrayList<>();
        Arrays.sort(nums);
        helper(nums, 0, new ArrayList<>());
        return res;
    }

    private void helper(int[] nums, int pos, List<Integer> temp) {
        res.add(new ArrayList<>(temp));
        for (int i = pos; i < nums.length; ++i) {
            if(i > pos && nums[i] == nums[i - 1])
                continue;
            temp.add(nums[i]);
            helper(nums, i + 1, temp);
            temp.remove(temp.size() - 1);
        }
    }
}

