/*
 * @lc app=leetcode id=119 lang=java
 *
 * [119] Pascal's Triangle II
 */
class Solution {
    public List<Integer> getRow(int rowIndex) {
        List<Integer> res = new ArrayList<>();
        for(int i = 0; i <= rowIndex; ++i)
            res.add(0);
        res.set(0, 1);
        for (int i = 1; i <= rowIndex; ++i) {
            for (int j = i; j >= 1; --j) {
                int t1 = res.get(j), t2 = res.get(j - 1);
                res.set(j, t1 + t2);
            }
        }
        return res;
    }
}

