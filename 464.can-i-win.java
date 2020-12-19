import java.util.HashMap;
import java.util.Map;

/*
 * @lc app=leetcode id=464 lang=java
 *
 * [464] Can I Win
 */
class Solution {
    // http://zxi.mytechroad.com/blog/searching/leetcode-464-can-i-win/
    // 0:unknown, 1:can win, -1:can not win
    // 一个byte占8位,节省空间
    private byte[] mem;

    public boolean canIWin(int maxChoosableInteger, int desiredTotal) {
        int sum = maxChoosableInteger * (maxChoosableInteger + 1) / 2;
        // 如果总和小于目标数的话
        // 两者都不可能win
        if (sum < desiredTotal)
            return false;

        if (desiredTotal <= 0)
            return true;

        // mem[i]表示在当前i这个状态下，自己是输还是赢
        //用一个maxChoosableInteger大小的byte型数组表示某一个数是否使用了
        mem = new byte[1 << maxChoosableInteger];
        
        return helper(maxChoosableInteger, desiredTotal, 0);
    }

    private boolean helper(int M, int T, int state) {
        // T<= 0表示，到自己选数时，目标数已经小于0，说明上一次对方已经到达了T
        if (T <= 0)
            return false;

        // 在之前选好的数组成的state下已经有了状态
        // 判断是否==1
        if (mem[state] != 0)
            return mem[state] == 1;

        // 在[1, M]中选数
        // 这里i用于左移,选中的数需要+1
        for (int i = 0; i < M; ++i) {
            // state第i位不为0，说明i+1已经被用过了
            if ((state & (1 << i)) > 0)
                continue;

            // state | (1 << i)标记i+1已经被用过了
            // 如果在当前情况下，对方继续选择时，不能win
            if (!helper(M, T - (i + 1), state | (1 << i))) {
                // 则自己会win
                mem[state] = 1;
                return true;
            }
        }

        // 全都遍历后，仍不能得到自己win的情况
        // 则lose;
        mem[state] = -1;
        return false;
    }

    // private Map<Integer, Boolean> mem;

    // public boolean canIWin(int maxChoosableInteger, int desiredTotal) {
    //     int sum = maxChoosableInteger * (maxChoosableInteger + 1) / 2;
    //     if (sum < desiredTotal)
    //         return false;
    //     if (maxChoosableInteger >= desiredTotal)
    //         return true;
    //     mem = new HashMap<>();
    //     return helper(maxChoosableInteger, desiredTotal, 0);
    // }
    
    // private boolean helper(int M, int T, int used) {
    //     if(T <= 0)
    //         return false;
    //     if(mem.containsKey(used))
    //         return mem.get(used);
    //     for (int i = 0; i < M; ++i) {
    //         int cur = (1 << i);
    //         if ((cur & used) == 0) {
    //             if (!helper(M, T - (i + 1), used | cur)) {
    //                 mem.put(used, true);
    //                 return true;
    //             }
    //         }
    //     }
    //     mem.put(used, false);
    //     return false;
    // }
}
