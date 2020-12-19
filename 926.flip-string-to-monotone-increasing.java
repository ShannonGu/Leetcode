/*
 * @lc app=leetcode id=926 lang=java
 *
 * [926] Flip String to Monotone Increasing
 */
class Solution {

    //https://zxi.mytechroad.com/blog/dynamic-programming/leetcode-926-flip-string-to-monotone-increasing/
    // public int minFlipsMonoIncr(String S) {
    //     int len = S.length();

    //     //dp[i][0]表示将S[0, i-1](即前i个字符)变成Monotone且S[i-1] = 0所需要的最小变动次数
    //     //dp[i][1]表示将S[0, i-1](前i个字符)变成Monotone且S[i-1] = 1所需要的最小变动次数
    //     int[][] dp = new int[len + 1][2];

    //     for (int i = 1; i <= len; ++i) {
    //         if (S.charAt(i - 1) == '0') {
    //             //当S[i - 1] == '0'不需要变动
    //             //故等于dp[i - 1][0];
    //             dp[i][0] = dp[i - 1][0];

    //             //需将S[i-1]变成'1'
    //             //然后再将S[0, i-2]变成Monotone且最后一位是0的次数
    //             //与将S[0, i-2]变成Monotone且最后一位是1的次数之间选择小者;
    //             dp[i][1] = Math.min(dp[i - 1][0], dp[i - 1][1]) + 1;
    //         }
    //         else {
    //             //S[i-1]=='1'

    //             //S[i-1]为'1'，需将之变为0;
    //             //再计算将S[0, i-2]变为Monotone且最后一位是0的次数;
    //             dp[i][0] = dp[i - 1][0] + 1;

    //             //S[i-1]不需要变动
    //             //在将S[i-1]变成Monotone且最后一位是0的次数(此时S[i-1]为第一个'1');
    //             //与将S[i-1]变成Monotone且最后一位是1的次数之间选择小者;
    //             dp[i][1] = Math.min(dp[i - 1][0], dp[i - 1][1]);
    //         }
    //     }

    //     //最后在两种情况下选择小者;
    //     return Math.min(dp[len][0], dp[len][1]);
    // }

    //对上面解法的优化
    // public int minFlipsMonoIncr(String S) {
    //     int len = S.length();
    //     int dp0 = 0, dp1 = 0;
    //     for (int i = 1; i <= len; ++i) {
    //         if (S.charAt(i - 1) == '0') {
    //             int tmp0 = dp0;
    //             dp1 = Math.min(dp0, dp1) + 1;
    //             dp0 = tmp0;
    //         } else {
    //             int tmp0 = dp0 + 1;
    //             dp1 = Math.min(dp0, dp1);
    //             dp0 = tmp0;
    //         }
    //     }
    //     return Math.min(dp0, dp1);
    // }


    //另一种dp解法
    public int minFlipsMonoIncr(String S) {
        int len = S.length();
        //l[i]表示将S[0, i]全部转变成'0'所需要的最小次数
        int[] l = new int[len + 1];//'1'->'0'
 
        //r[i]表示将S[i, n-1]全部转换成'1'所需要的最小次数
        int[] r = new int[len + 1];//'0'->'1'

        //根据首尾字符确定l[0]和r[len-1]的初值;
        l[0] = S.charAt(0) - '0';
        r[len - 1] = '1' - S.charAt(len - 1);

        for (int i = 1; i < len; ++i) {
            l[i] = l[i - 1] + S.charAt(i) - '0';
        }

        for (int i = len - 2; i >= 0; --i) {
            r[i] = r[i + 1] + '1' - S.charAt(i);
        }

        //ans = min(l[i-1] + r[i], l[n-1], r[0]);
        int ans = r[0];
        for (int i = 1; i <= len; ++i) {
            ans = Math.min(ans, l[i - 1] + r[i]);
        }
        return ans;
    }
}

