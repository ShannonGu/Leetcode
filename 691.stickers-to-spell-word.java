import java.util.Map;

/*
 * @lc app=leetcode id=691 lang=java
 *
 * [691] Stickers to Spell Word
 */
class Solution {
    // https://www.cnblogs.com/grandyang/p/8468045.html
    // https://leetcode.com/problems/stickers-to-spell-word/discuss/108334/Explaining-StefanPochmann's-Rewrite-of-contest-winner's-solution-and-%2Bjava
    public int minStickers(String[] stickers, String target) {
        int n = target.length(), m = 1 << n;
        // dp[i]表示组成target的第i个子集所需要的最少的sticker个数
        int[] dp = new int[m];
        for (int i = 1; i < m; ++i) {
            dp[i] = -1;
        }

        dp[0] = 0;

        for (int state = 0; state < m; ++state) {
            // 不能state代表的子集无法被拼出来，也就无法在其基础上去构建其他子集
            if (dp[state] == -1)
                continue;
            for (String sticker : stickers) {
                // 在state代表的子集的基础上去拼别的子集
                int cur = state;
                for (char c : sticker.toCharArray()) {
                    for (int i = 0; i < n; ++i) {
                        // target有当前这个字符,并且该字符未出现在当前cur位置的子集合中
                        // 将该字符加入到子集合中
                        if (target.charAt(i) == c && ((cur >> (n - 1 - i)) & 1) == 0) {
                            cur |= 1 << (n - 1 - i);
                            break;
                        }
                    }
                }

                if (dp[cur] == -1 || dp[cur] > dp[state] + 1) {
                    dp[cur] = dp[state] + 1;
                }
            }
        }
        return dp[m - 1];
    }

}
