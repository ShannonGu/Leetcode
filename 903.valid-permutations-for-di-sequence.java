class Solution {
    // https://www.cnblogs.com/grandyang/p/11094525.html
    private final int MOD = 1000000007;

    public int numPermsDISequence(String S) {
        int res = 0, n = S.length();
        
        // dp[i][j]表示由[0,i]内的数字组成且结尾数字为j的满足要求的序列的个数
        int[][] dp = new int[n + 1][n + 1];

        // [0,0]以0结尾的序列只有1个(0)
        dp[0][0] = 1;

        for (int i = 1; i <= n; ++i) {
            // 末尾要加的数字为j
            for (int j = 0; j <= i; ++j) {
                if (S.charAt(i - 1) == 'D') {
                    // 降序,所以要从结尾数字>=j的上一轮序列中寻找
                    // 对于序列中>=j的数字，均加上1,然后末尾加上j
                    // 前i个数字中组成的序列中最大数字为i-1
                    for (int k = j; k <= i - 1; ++k) {
                        dp[i][j] = (dp[i][j] + dp[i - 1][k]) % MOD;
                    }
                } else {
                    // 升序,从结尾数字<j的上一轮序列中寻找
                    // 对于序列中>=j的数字，均加上1,然后末尾加上j
                    for (int k = 0; k < j; ++k) {
                        dp[i][j] = (dp[i][j] + dp[i - 1][k]) % MOD;
                    }
                }
            }
        }

        // 计算[0,n]内以各个数字结尾的满足要求的序列的个数
        for (int i = 0; i <= n; ++i) {
            res = (res + dp[n][i]) % MOD;
        }

        return res;
    }
}