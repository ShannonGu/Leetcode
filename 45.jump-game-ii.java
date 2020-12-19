/*
 * @lc app=leetcode id=45 lang=java
 *
 * [45] Jump Game II
 */
class Solution {
    // https://www.cnblogs.com/grandyang/p/4373533.html
    // public int jump(int[] nums) {
    //     // 使用Greedy策略，每次贪心地求一个能够到达的最远范围;
    //     // 然后在这个最远范围内,遍历每一个位置,然后根据该位置上的跳力,
    //     // 得出下一步能到达的最远位置,更新能够到达的最远范围;
    //     // 一旦当这个范围能够覆盖最后一个位置，所用步数一定是最小步数;

    //     // cur表示当前能够到达的最远位置;
    //     int res = 0, len = nums.length, cur = 0;
    //     int i = 0;
    //     // 当最远位置不能覆盖最后一个位置;
    //     // 循环继续;
    //     while (cur < len - 1) {
    //         // 增加一步;
    //         ++res;

    //         // pre表示上一轮更新得到的最远位置;
    //         int pre = cur;

    //         // 遍历在最远位置中的每个位置,根据每个位置上的跳力;
    //         // 不断更新最远位置;
    //         for (; i <= pre; ++i) {
    //             cur = Math.max(cur, i + nums[i]);
    //         }

    //         // 若pre==cur,说明最远位置没有改变,最远位置不能向前延伸;
    //         // 表明无法到达末尾;
    //         if (pre == cur)
    //             return -1;
    //     }
    //     return res;
    // }

    public int jump(int[] nums) {
        int len = nums.length;

        //另一种写法
        //pre上一步能到达的最远位置
        //cur表示当前能到达的最远位置;
        int res = 0, pre = 0, cur = 0;
        for (int i = 0; i < len - 1; ++i) {

            //更新当前能到达的最远位置;
            cur = Math.max(cur, i + nums[i]);
            //若当前位置抵达了上一步的最远位置;
            if (i == pre) {
                //表明需要下一步;
                ++res;

                pre = cur;
                //若当前最远位置已经覆盖末尾位置;
                //已经得出结果;
                if (cur >= len - 1)
                    break;
            }
        }
        return res;
    }
}
