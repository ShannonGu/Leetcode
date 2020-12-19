import java.util.HashMap;
import java.util.HashSet;

/*
 * @lc app=leetcode id=403 lang=java
 *
 * [403] Frog Jump
 *
 * https://leetcode.com/problems/frog-jump/description/
 *
 * algorithms
 * Hard (36.12%)
 * Total Accepted:    51.4K
 * Total Submissions: 142.3K
 * Testcase Example:  '[0,1,3,4,5,7,9,10,12]'
 *
 * A frog is crossing a river. The river is divided into x units and at each
 * unit there may or may not exist a stone. The frog can jump on a stone, but
 * it must not jump into the water.
 * 
 * Given a list of stones' positions (in units) in sorted ascending order,
 * determine if the frog is able to cross the river by landing on the last
 * stone. Initially, the frog is on the first stone and assume the first jump
 * must be 1 unit.
 * 
 * 
 * If the frog's last jump was k units, then its next jump must be either k -
 * 1, k, or k + 1 units. Note that the frog can only jump in the forward
 * direction.
 * 
 * Note:
 * 
 * The number of stones is ≥ 2 and is < 1,100.
 * Each stone's position will be a non-negative integer < 2^31.
 * The first stone's position is always 0.
 * 
 * 
 * 
 * Example 1:
 * 
 * [0,1,3,5,6,8,12,17]
 * 
 * There are a total of 8 stones.
 * The first stone at the 0th unit, second stone at the 1st unit,
 * third stone at the 3rd unit, and so on...
 * The last stone at the 17th unit.
 * 
 * Return true. The frog can jump to the last stone by jumping 
 * 1 unit to the 2nd stone, then 2 units to the 3rd stone, then 
 * 2 units to the 4th stone, then 3 units to the 6th stone, 
 * 4 units to the 7th stone, and 5 units to the 8th stone.
 * 
 * 
 * 
 * Example 2:
 * 
 * [0,1,2,3,4,8,9,11]
 * 
 * Return false. There is no way to jump to the last stone as 
 * the gap between the 5th and 6th stone is too large.
 * 
 * 
 */
class Solution {
    // https://blog.csdn.net/YABAJ/article/details/77115969
    // https://www.cnblogs.com/grandyang/p/5888439.html
    // 记忆数组memo + dfs;
    // public boolean canCross(int[] stones) {
    //     // 将位置和弹跳力的结合信息与是否能到达到该位置建立映射;
    //     // 表示在pos位置和拥有k弹跳力时能否最终跳到最后一个石头;
    //     HashMap<Integer, Boolean> memo = new HashMap();
    //     return helper(memo, stones, 0, 0);
    // }

    // private boolean helper(HashMap<Integer, Boolean> memo, int[] stones, int pos, int k) {
    //     // 已经到达了最后一个位置;
    //     if (pos == stones.length - 1)
    //         return true;

    //     // 利用题目所给条件将位置和弹跳力结合起来;
    //     // stones数量<=1100,不会超过11位,且每次跳跃不超过2^31;
    //     // 所以将二者用按位或组合后放入memo数组作为key;
    //     int key = pos | (k << 11);
    //     // 已经处理过这种情况
    //     // 直接返回;
    //     if (memo.containsKey(key))
    //         return memo.get(key);
    //     // 遍历后面的每个位置;
    //     for (int i = pos + 1; i < stones.length; ++i) {
    //         // 计算位置i相对于位置pos的距离;
    //         int dist = stones[i] - stones[pos];
    //         // 小于k-1，表示对于位置i,位置pos上此时的弹跳力太大了;
    //         if (dist < k - 1)
    //             continue;
    //         // 大于k+1,对于位置i及后面的位置，位置pos上此时的弹跳力太小了;
    //         if (dist > k + 1) {
    //             // 这种情况不可能到达最后一个位置;
    //             memo.put(key, false);
    //             return memo.get(key);
    //         }
    //         // 处于k-1~k+1之间;
    //         // 向后递归
    //         if (helper(memo, stones, i, dist)) {
    //             // 表示当前位置能够到达最后一个石头;
    //             memo.put(key, true);
    //             return memo.get(key);
    //         }
    //     }
    //     // 当前位置不能到达;
    //     memo.put(key, false);
    //     return memo.get(key);
    // }

    // 动态规划;
    // public boolean canCross(int[] stones) {
    //     // 建立每个石头与其在该位置上能跳的距离之间的映射;
    //     HashMap<Integer, HashSet<Integer>> map = new HashMap();
    //     int len = stones.length;
    //     // dp[i]表示在位置为i的石头上青蛙的弹跳力
    //     // 只有青蛙能跳到该石头上,dp[i]才>0;
    //     int[] dp = new int[len];
    //     map.put(0, new HashSet<Integer>());
    //     // 第0个位置上弹跳力始终为1;
    //     map.get(0).add(1);
    //     int k = 0;
    //     for (int i = 1; i < len; ++i) {
    //         // 寻找能跳到石头i上的第一个石头k;
    //         while (stones[i] - stones[k] > dp[k] + 1)
    //             ++k;

    //         map.put(i, new HashSet<Integer>());
    //         for (int j = k; j < i; ++j) {
    //             // 石头j,i之间的距离;
    //             int gap = stones[i] - stones[j];
    //             // 若青蛙能从中间某个石头j跳到i上,就更新石头i上的弹跳力;
    //             // gap-1,gap,gap+1表示石头j上弹跳力+1,弹跳力,弹跳力+1;
    //             if (map.get(j).contains(gap - 1) || map.get(j).contains(gap) || map.get(j).contains(gap + 1)) {
    //                 // 满足条件则表示位置j上存在弹跳力到达位置i;
    //                 // 则更新位置i的弹跳力;
    //                 map.get(i).add(gap);
    //                 // 更新石头i上的最大弹跳力;
    //                 dp[i] = Math.max(dp[i], gap);
    //             }
    //         }
    //     }

    //     // 只要青蛙能跳到该石头上,dp[i]才>0;
    //     // 所以需要判断最后一个石头的最大弹跳力是否>0;
    //     return dp[dp.length - 1] > 0;
    // }


    
    public boolean canCross(int[] stones) {
        int len = stones.length;
        if (len == 0)
            return true;
        // 建立每个位置的石头与在该位置上弹跳力之间的映射;
        HashMap<Integer, HashSet<Integer>> map = new HashMap();

        // 为每个位置初始化一个在该位置上弹跳力的集合;
        for (int i = 0; i < len - 1; ++i) {
            map.put(stones[i], new HashSet<Integer>());
        }
        // 由题意，位置0的弹跳力只能是1;
        map.get(0).add(1);

        for (int i = 0; i < stones.length - 1; ++i) {
            int stone = stones[i];
            // 遍历在当前位置上的弹跳力;
            for (int step : map.get(stone)) {
                // 所能到达的位置;
                int reach = stone + step;
                // 若能到达的位置是最后一个位置,表示能成功;
                if (reach == stones[len - 1])
                    return true;
                // 否则，取该位置弹跳力的集合;
                HashSet<Integer> set = map.get(reach);

                // 首先判断位置reach是不是某个石头的位置;
                if (set != null) {
                    // 若是,则更新位置reach上的弹跳力;
                    set.add(step);
                    if (step - 1 > 0)
                        set.add(step - 1);
                    set.add(step + 1);
                }
            }
        }
        // 最后一个位置之前每个石头均不能到达最后一个石头;
        return false;
    }
}
