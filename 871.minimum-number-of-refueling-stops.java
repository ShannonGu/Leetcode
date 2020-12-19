import java.util.PriorityQueue;
import java.util.Queue;

class Solution {
    // https://zxi.mytechroad.com/blog/dynamic-programming/leetcode-871-minimum-number-of-refueling-stops/
    // public int minRefuelStops(int target, int startFuel, int[][] stations) {
    //     // 0-1背包变形
    //     int n = stations.length;
    //     // dp[i]表示经过第i个加油站后最远能够达到的距离
    //     int[] dp = new int[n + 1];
    //     Arrays.fill(dp, startFuel);

    //     // 遍历每个加油站
    //     for (int i = 0; i < n; ++i) {
    //         // 加油站个数
    //         for (int j = i + 1; j >= 1; --j) {
    //             if (dp[j - 1] >= stations[i][0]) {
    //                 // 如果可以经过j-1个加油站到达加油站i
    //                 // 就可以经过j个加油站到达dp[j - 1] + stations[i][1]
    //                 dp[j] = Math.max(dp[j], dp[j - 1] + stations[i][1]);
    //             }
    //         }
    //     }

    //     for (int i = 0; i < dp.length; ++i) {
    //         // 经过i个加油站已经能够到达target了
    //         if (dp[i] >= target)
    //             return i;
    //     }
    //     return -1;
    // }


    //维护一个大顶堆
    public int minRefuelStops(int target, int startFuel, int[][] stations) {
        //当前能到达的距离
        int cur = startFuel;
        int cnt = 0, i = 0;
        Queue<Integer> q = new PriorityQueue<>((w1, w2) -> w2 - w1);
        while (true) {
            if (cur >= target)
                return cnt;
            //将途中经过的加油站入队，如果加油站在当前能够到达的最远距离内
            while (i < stations.length && stations[i][0] <= cur) {
                q.offer(stations[i++][1]);
            }

            //如果队为空,说明当前油箱耗光后，不能到达下一个加油站
            if (q.isEmpty())
                break;
            //依次选择油量最多的加油站
            cur += q.poll();
            ++cnt;
        }
        return -1;
    }
}