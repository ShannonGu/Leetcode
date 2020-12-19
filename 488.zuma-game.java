/*
 * @lc app=leetcode id=488 lang=java
 *
 * [488] Zuma Game
 */
class Solution {
    // https://zxi.mytechroad.com/blog/searching/leetcode-488-zuma-game/
    public int findMinStep(String board, String hand) {
        // 建立颜色数组来记录hand中每个颜色的球的个数
        int[] h = new int[128];
        for (char color : hand.toCharArray())
            ++h[color];
        return helper(board, h);
    }

    // 返回清空board所需要的最少数量的hand中的球
    // 如果不能成功，则返回-1
    private int helper(String board, int[] hand) {

        if (board.isEmpty())
            return 0;

        int res = Integer.MAX_VALUE;
        int i = 0, j = 0;
        // 从左向右依次遍历
        while (i < board.length()) {
            while (j < board.length() && board.charAt(i) == board.charAt(j))
                ++j;

            // board[i, j-1]有相同的颜色
            char color = board.charAt(i);
            // 需要need个color颜色的球才能清除board[i, j-1];
            int need = 3 - (j - i);
            // 确保hand中有足够数量的color颜色的球
            if (hand[color] >= need) {
                // 移除已经board[i, j - 1], 并处理可能引起的连锁情况
                String next = update(board.substring(0, i) + board.substring(j));
                // hand中减去用掉的handB个color颜色的球
                hand[color] -= need;
                // 继续递归求解
                int tmp = helper(next, hand);
                // 如果该种情况是有解的
                if (tmp >= 0)
                    // 更新答案
                    res = Math.min(res, need + tmp);
                // 恢复hand球的个数
                hand[color] += need;
            }

            // 继续遍历其他颜色的球的情况
            i = j;
        }

        // 若res未变化，说明不能清空board;
        return res == Integer.MAX_VALUE ? -1 : res;
    }

    // 更新board，移除所有超过3个的连续的球
    // "YWWRRRWWYY" -> "YWWWWYY" -> "YYY" -> ""
    private String update(String board) {
        int i = 0;
        while (i < board.length()) {
            StringBuilder tmp = new StringBuilder("");
            int j = i;
            while (j < board.length() && board.charAt(i) == board.charAt(j))
                ++j;
            if (j - i >= 3) {
                tmp.append(board.substring(0, i)).append(board.substring(j));
                board = tmp.toString();
                i = 0;
            } else {
                ++i;
            }
        }
        return board;
    }
}
