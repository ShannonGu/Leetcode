/*
 * @lc app=leetcode id=517 lang=java
 *
 * [517] Super Washing Machines
 */
class Solution {
    // https://www.cnblogs.com/grandyang/p/6648557.html
    public int findMinMoves(int[] machines) {
        int sum = 0;
        for (int machine : machines)
            sum += machine;
        if (sum % machines.length != 0)
            return -1;
        int res = 0, cnt = 0, avg = sum / machines.length;
        // 先求出平均每个machine最终要放的dress数
        // 例如case[2, 0, 14, 4]，最终结果是[5,5,5,5]
        // 二者作差的[-3, -5, 9, -1]
        // 要将所得的差每个都置为0
        // 于是可将第1个的3个dress转移到第0个上,得[0, -8, 9, -1]
        // 将第2个的8个dress转移到第1个上，得[0, 0, 1, -1]
        // 将第2个的1个dress转移到第3个上,得[0,0,0,0]
        for (int machine : machines) {
            cnt += machine - avg;
            res = Math.max(res, Math.max(Math.abs(cnt), machine - avg));
        }
        return res;
    }
}
