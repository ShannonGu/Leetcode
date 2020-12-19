/*
 * @lc app=leetcode id=216 lang=java
 *
 * [216] Combination Sum III
 */
class Solution {
    List<List<Integer>> res;

    public List<List<Integer>> combinationSum3(int k, int n) {
        res = new ArrayList<>();
        helper(n, k, 1, 0, new ArrayList<>());
        return res;
    }
    
    private void helper(int n, int k, int pos, int sum, List<Integer> temp) {
        if (k == 0) {
            if(sum == n)
                res.add(new ArrayList<>(temp));
            return;
        }

        for (int i = pos; i <= 9; ++i) {
            temp.add(i);
            helper(n, k - 1, i + 1, sum + i, temp);
            temp.remove(temp.size() - 1);
        }
    }
    
}

