/*
 * @lc app=leetcode id=134 lang=java
 *
 * [134] Gas Station
 */
class Solution {
    public int canCompleteCircuit(int[] gas, int[] cost) {
        if(gas.length == 0)
            return -1;
        int n = gas.length;
        int total = 0, sum = 0, st = 0;
        // 用total表示每站的gas和cost的差的累加和;
        // sum表示到当前站gas与cost累加的情况;
        // 假设起点start=0,并从这里出发,如果当前gas值大于cost,则可以继续前进
        // 到达下一站是将剩余gas加上当前站的gas减去当前cost,看是否小于0,
        // 若小于0，说明当前站及之前的站均不能作为起点,把start设为下一站;
        // 因为当前站之前的sum不小于0，而到了当前站小于0,说明此前积累的剩余gas加上
        // 此站gas仍不能抵消当前站的cost,从而表明从起点到当前站每一站均不能作为起点;

        for (int i = 0; i < n; ++i) {
            total += gas[i] - cost[i];
            sum += gas[i] - cost[i];
            if (sum < 0) {
                st = i + 1;
                sum = 0;
            }
        }
        return total < 0 ? -1 : st;
    }
}

