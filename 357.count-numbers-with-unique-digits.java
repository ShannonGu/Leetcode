/*
 * @lc app=leetcode id=357 lang=java
 *
 * [357] Count Numbers with Unique Digits
 */
class Solution {
    //https://www.cnblogs.com/grandyang/p/5582633.html
    public int countNumbersWithUniqueDigits(int n) {
        //通项公式为, k表示几位数
        //一位数满足要求的有(0~9)10个数
        //二位数满足要求的是除去(11~99)即90-9=81=9*9;
        //三位数满足要求的是900-(10+9+9)*9=9*(100-28)=9*72=9*9*8;
        //                10,                     k == 1
        //        f(k) = 
        //             9*  9*8....(9 - k + 2),   k >= 2
        if(n == 0)
            return 1;
        int res = 0;
        for (int i = 1; i <= n; ++i) {
            res += count(i);
        }
        return res;
    }

    private int count(int k) {
        if(k < 1)
            return 0;
        if(k == 1)
            return 10;
        int res = 1;
        for (int i = 9; i >= 11 - k; --i) {
            res *= i;
        }
        return res * 9;
    }
}

