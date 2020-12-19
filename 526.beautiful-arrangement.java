/*
 * @lc app=leetcode id=526 lang=java
 *
 * [526] Beautiful Arrangement
 */
class Solution {
    private boolean[] used;
    private int res = 0;

    public int countArrangement(int N) {
        used = new boolean[N + 1];
        helper(N, 1);
        return res;
    }

    private void helper(int N, int pos) {
        // pos表示遍历到第几个位置
        if (pos == N + 1) {
            ++res;
            return;
        }

        for (int i = 1; i <= N; ++i) {
            if (!used[i] && (i % pos == 0 || pos % i == 0)) {
                used[i] = true;
                helper(N, pos + 1);
                used[i] = false;
            }
        }
    }
}
