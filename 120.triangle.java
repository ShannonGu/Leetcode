/*
 * @lc app=leetcode id=120 lang=java
 *
 * [120] Triangle
 */
class Solution {
    public int minimumTotal(List<List<Integer>> triangle) {
        int n = triangle.size();
        if(n == 0)
            return 0;
        List<Integer> dp = triangle.get(n - 1);
        for (int i = n - 2; i >= 0; --i) {
            for (int j = 0; j <= i; ++j) {
                dp.set(j, Math.min(dp.get(j), dp.get(j + 1)) + triangle.get(i).get(j));
            }
        }
        return dp.get(0);
    }
}

