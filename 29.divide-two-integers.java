/*
 * @lc app=leetcode id=29 lang=java
 *
 * [29] Divide Two Integers
 */
class Solution {
    //https://www.cnblogs.com/grandyang/p/4431949.html
    public int divide(int dividend, int divisor) {
        //首先判断除数为0,或者被除数是Integer最小负数且除数是-1
        //这些情况会产生溢出
        if(divisor == 0 || (dividend == Integer.MIN_VALUE && divisor == -1))
            return Integer.MAX_VALUE;
        
        //考虑dividend为Integer.MIN_VALUE,转为绝对值就溢出了，所以需要long型
        long m = Math.abs((long) dividend), n = Math.abs((long) divisor);
        
        //判断两数是否同号
        //两数同号异或后得0
        int sign1 = dividend < 0 ? 1 : 0, sign2 = divisor < 0 ? 1 : 0;
        int sign = (sign1 ^ sign2) == 0 ? 1 : -1;

        if(n == 1)
            return sign == 1 ? (int) m : -(int) m;

        //商
        int res = 0;

        //计算被除数中有多少个除数
        while (n <= m) {
            //cnt记录除数的个数
            long tmp = n;
            int cnt = 1;

            while ((tmp << 1) <= m) {
                tmp <<= 1;
                cnt <<= 1;
            }

            //被除数减去除数的个数，然后继续在剩下的差中计算除数的个数
            m -= tmp;

            //更新个数
            res += cnt;
        }

        return sign == 1 ? res : -res;
    }
}

