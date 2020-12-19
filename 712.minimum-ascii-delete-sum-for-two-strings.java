class Solution {
    public int minimumDeleteSum(String s1, String s2) {
        int n1 = s1.length(), n2 = s2.length();
        // dp[i][j]表示将s1[0,i]s2[0,j]转换成相等需要删除的字符的最小Ascii值
        int[][] dp = new int[n1 + 1][n2 + 1];

        for (int i = 1; i <= n1; ++i) {
            dp[i][0] = dp[i - 1][0] + (int) s1.charAt(i - 1);
        }

        for (int i = 1; i <= n2; ++i) {
            dp[0][i] = dp[0][i - 1] + (int) s2.charAt(i - 1);
        }
        
        for (int i = 1; i <= n1; ++i) {
            for (int j = 1; j <= n2; ++j) {
                if (s1.charAt(i - 1) == s2.charAt(j - 1))
                    dp[i][j] = dp[i - 1][j - 1];
                else {
                    dp[i][j] = Math.min(dp[i - 1][j - 1] + (int) s1.charAt(i - 1) + (int) s2.charAt(j - 1),
                            Math.min(dp[i - 1][j] + (int) s1.charAt(i - 1), dp[i][j - 1] + (int) s2.charAt(j - 1)));
                }
            }
        }
        return dp[n1][n2];
    }
}