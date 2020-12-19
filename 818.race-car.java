import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/*
 * @lc app=leetcode id=818 lang=java
 *
 * [818] Race Car
 */
class Solution {
    //http://zxi.mytechroad.com/blog/graph/leetcode-818-race-car/
    //https://leetcode.com/problems/race-car/discuss/124326/Summary-of-the-BFS-and-DP-solutions-with-intuitive-explanation
    // public int racecar(int target) {
    //     Queue<int[]> q = new LinkedList<>();
        
    //     // ArrayList<Integer> arr = new ArrayList<>(Arrays.asList(0, 1));
    //     q.offer(new int[]{0, 1});
    //     Set<String> m = new HashSet<>();
    //     m.add(0 + "_" + 1);
    //     int level = 0;
    //     while (!q.isEmpty()) {
    //         int len = q.size();
    //         while (len-- > 0) {
    //             int[] tmp = q.poll();
    //             // int pos = tmp[0], spe = tmp[1];
    //             if (tmp[0] == target)
    //                 return level;

    //             // int pos1 = pos + spe;
    //             // int spe1 = (spe << 1);
    //             int[] nxt = new int[]{tmp[0] + tmp[1], tmp[1] << 1};
    //             String key = (nxt[0] + "_" + nxt[1]);
    //             //剪枝条件
    //             if(!m.contains(key) && nxt[0] > 0 && nxt[0] < (target << 1)){
    //                 q.offer(nxt);
    //                 m.add(key);
    //             }

    //             // int spe2 = spe > 0 ? -1 : 1;
    //             // ArrayList<Integer> p2 = new ArrayList<>(Arrays.asList(pos, spe2));
    //             nxt = new int[]{tmp[0], tmp[1] > 0 ? -1 : 1};
    //             key = (nxt[0] + "_" + nxt[1]);
    //             if (!m.contains(key) && nxt[0] > 0 && nxt[0] < (target << 1)) {
    //                 q.offer(nxt);
    //                 m.add(key);
    //             }
    //         }
    //         ++level;
    //     }
    //     return -1;
    // }


    // https://www.cnblogs.com/grandyang/p/10360655.html
    public int racecar(int target) {
        //dp[i]表示到达位置i，所需要的最少指令个数
        int[] dp = new int[target + 1];
        //对于某个位置i,分为两种情况
        //一种是在到达该位置之前，回头两次
        //另一种是超过该位置后再回头
        for(int i = 1; i <= target; ++i){
            dp[i] = Integer.MAX_VALUE;
            //第一种情况
            //j为正向加速能到达的位置,初始为1,cnt1为正向加速次数
            //车正向加速的位置是0->1->3->7->15.....
            //所以j每次更新应该是2^cnt1-1;
            int j = 0, cnt1 = 0;
            while(j < i){
                //对于每个j位置,都要反向跑一次
                //此时反向加速距离k从0开始遍历,不能超过j,否则没有意义
                //同理k每次更新应该是2^cnt2-1;
                int k = 0, cnt2 = 0;
                while(k < j){
                    //正向走到了j,反向走了距离k,即可表示正向走了(j-k);
                    //所需指令数为cnt1+1+cnt2+1,两个1表示反向操作两次
                    //第二次反向后，就朝着i的方向加速了;
                    //此时与i之间的距离为i-(j-k);
                    dp[i] = Math.min(dp[i], cnt1 + 1 + cnt2 + 1 + dp[i - (j - k)]);
                    ++cnt2;
                    k = (1 << cnt2) - 1;
                }
                ++cnt1;
                j = (1 << cnt1) - 1;
            }

            //第二种情况是当超过i位置后才回头的情况;
            //此时cnt1是刚好能超过或到达i位置的加速次数，可以直接使用
            //此时比较i和j,若想等，则表示一开始可以直接正向加速到达位置i;
            //否则需要反向操作一次，然后距离差为j-i, 直接调用dp[j-i],加上反向操作1次;
            dp[i] = Math.min(dp[i], cnt1 + (i == j ? 0 : 1 + dp[j - i]));
        }
        return dp[target];
    }
}

