/*
 * @lc app=leetcode id=650 lang=java
 *
 * [650] 2 Keys Keyboard
 */
class Solution {
    //https://www.cnblogs.com/grandyang/p/7439616.html
    // public int minSteps(int n) {
    //     if (n == 1)
    //         return 0;
    //     int res = n;
    //     for (int i = n - 1; i > 1; --i) {
    //         if (n % i == 0)
    //             res = Math.min(res, minSteps(i) + n / i);
    //     }
    //     return res;
    // }


    public int minSteps(int n) {
        //如果n为素数，那么只能通过n次步骤来实现
        //若n有小于n的因子p，那么一定可以由该因子通过copy(1次)+ paste(n/p-1)总共n/p次得到;
        //而该因子p可以由copy(1次) + paste(p-1次)总共p次得到
        int res = 0;
        for (int i = 2; i <= n; ++i) {
            //始终找不到比自己小的因子,所以只有n本身这个因子
            while (n % i == 0) {
                res += i;
                n /= i;
            }
        }
        return res;
    }
}
