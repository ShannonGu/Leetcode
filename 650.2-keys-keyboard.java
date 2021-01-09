/*
 * @lc app=leetcode id=650 lang=java
 *
 * [650] 2 Keys Keyboard
 */
class Solution {
    // https://www.cnblogs.com/grandyang/p/7439616.html
    public int minSteps(int n) {
        // 如果n为素数，那么只能通过n次步骤来实现
        // 若n有小于n的因子p，那么长度n就可以由长度p得到，
        // 其操作次数等价于把一个长度为1的A延伸到长度i/j
        // 因此n的总次数为从1个A得到长度p的次数加上得到长度i/j的次数
        int[] dp = new int[n + 1];
        int h = (int) Math.sqrt(n);
        for (int i = 2; i <= n; i++) {
            dp[i] = i;
            for (int j = 2; j <= h; j++) {
                // 这里之所以只需要遍历到一个因子就break
                // 是因为相当于分解质因数，一个合数的因数分解是相同的
                // 例如dp[36]=dp[2] + dp[18]而dp[18]又可以继续分解
                // 所以并不需要遍历完所有因子求最小值
                if (i % j == 0) {
                    dp[i] = dp[j] + dp[i / j];
                    break;
                }
            }
        }
        return dp[n];
    }

    // public int minSteps(int n) {
    // int res = 0;
    // for (int i = 2; i <= n; ++i) {
    // // 始终找不到比自己小的因子,所以只有n本身这个因子
    // while (n % i == 0) {
    // res += i;
    // n /= i;
    // }
    // }
    // return res;
    // }
}
