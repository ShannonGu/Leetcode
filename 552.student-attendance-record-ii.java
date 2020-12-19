/*
 * @lc app=leetcode id=552 lang=java
 *
 * [552] Student Attendance Record II
 */
class Solution {
    // https://www.cnblogs.com/grandyang/p/6866756.html
    private final int MOD = 1000000007;

    public int checkRecord(int n) {
        if (n == 0)
            return 0;
            
        // P[i]表示前i个字符中以P结尾的排列个数
        // PorL[i]表示前i个字符中以P或者L结尾的排列个数
        // 先不考虑字符A的情况
        // 由于P字符可以在任意字符后面添加,因此P[i] = PorL[i - 1];
        // 而PorL[i]则由两部分组成，即P[i] + L[i],其中P[i]已经更新
        // L[i]的情况是只能当前一个字符是P,或者前一个字符是L,且再前一个字符是P的情况加上L(不能有超过两个连续的L)
        // 即L[i] = P[i - 1] + P[i - 2];
        // 因此PorL[i] = P[i] + P[i - 1] + P[i - 2];
        // 于是可以先求解出P[i]和PorL[i]的情况
        long[] P = new long[n + 1], PorL = new long[n + 1];

        P[0] = 1;
        P[1] = 1;
        PorL[0] = 1;
        PorL[1] = 2;
        for (int i = 2; i <= n; ++i) {
            P[i] = PorL[i - 1];
            PorL[i] = (P[i] + P[i - 1] + P[i - 2]) % MOD;
        }

        long res = PorL[n];
        // 而求解包含一个A的情况时, 只需要挨个遍历，将遍历到的位置放置A
        // 那么数组就被划分成左右两部分, 且这两个部分就不能再有A了
        // 而分成的左右两部分长度都是比原数组长度小的，组合情况在前面已经计算过了
        // 于是只要将两个子数组的排列个数相乘，再累加即得到答案
        for (int i = 1; i <= n; ++i) {
            long t = (PorL[i - 1] * PorL[n - (i - 1) - 1]) % MOD;
            res = (res + t) % MOD;
        }
        return (int) res;
    }
}
