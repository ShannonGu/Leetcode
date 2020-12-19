/*
 * @lc app=leetcode id=446 lang=java
 *
 * [446] Arithmetic Slices II - Subsequence
 */
class Solution {
    // https://www.cnblogs.com/EdwardLiu/p/6161170.html
    // https://www.cnblogs.com/grandyang/p/6057934.html
    public int numberOfArithmeticSlices(int[] A) {
        int len = A.length;
        if (len <= 2)
            return 0;
        // 用HashMap做DP;
        // dp[i](d, cnt)表示以第i个数结尾,能与第i个数形成公差为d的arithmetic sequences的数字的个数;
        // 将在i位置上的公差d与前面arithmetic sequences的数字的个数映射起来;
        Map<Integer, Integer>[] dp = new Map[len];
        //记录sequence总数
        int res = 0;
        for (int i = 0; i < len; ++i) {
            dp[i] = new HashMap<>();
            for (int j = 0; j < i; ++j) {
                long delta = (long) A[i] - A[j];
                if (delta > Integer.MAX_VALUE || delta < Integer.MIN_VALUE)
                    continue;
                int diff = (int) delta;

                //首先判断在位置i上是否已经存在了公差为d的情况;
                //这里用java8的新方法getOrDefault;
                int c1 = dp[i].getOrDefault(diff, 0);

                //若[0,i]之间一个位置j上的存在有相同公差的情况
                //表示j之前可以形成公差为diff的数字有c2个
                int c2 = dp[j].getOrDefault(diff, 0);

                //比如  2     4     6      8   公差为2的情况是
                //          2->1
                //                2->2
                //                       2->3
                //6处2->2表示6前面可以形成公差为2的数字有两个,
                //因为是根据8,6得出公差diff=2
                //于是当前可以形成公差为2的sequence有两个2,4,6; 4,6,8
                //加上可以形成公差为diff的arithmetic sequence的个数
                res += c2;

                //更新位置i的个数;
                //这里加1表示i之前可以形成公差为diff的数字有c2+1个包括了j位置上的数字
                dp[i].put(diff, c1 + c2 + 1);
            }
        }
        return res;
    }
}
