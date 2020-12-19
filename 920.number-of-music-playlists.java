/*
 * @lc app=leetcode id=920 lang=java
 *
 * [920] Number of Music Playlists
 */

// @lc code=start
class Solution {
    // http://www.noteanddata.com/leetcode-920-Number-of-Music-Playlists-solution-note.html
    // https://zxi.mytechroad.com/blog/dynamic-programming/leetcode-920-number-of-music-playlists/
    private final int MOD = 1000000007;

    // public int numMusicPlaylists(int N, int L, int K) {
    //     // dp[i][j]表示在长度为i的列表中有j首不同的歌的歌单数量
    //     long[][] dp = new long[L + 1][N + 1];
    //     //长度为0的歌单有0首不同的歌只能是空歌单
    //     dp[0][0] = 1;
    //     for (int i = 1; i <= L; ++i) {
    //         // 在长度为i的歌单中最多只有Math.min(i, N)首不同的歌
    //         for (int j = 1; j <= Math.min(i, N); ++j) {
    //             // 分两种情况
    //             // 如果第j首歌是之前没放过的, 在前i-1首歌中放了j-1首的基础之上有(N - (j - 1))个选择放第j首不同的歌
    //             // 如果第j首歌是之前放过的，那么也就是在前i-1首歌中已经j首不同的歌了,因为要符合K的要求，所以最近的K首就不能放了
    //             // 只能在j-k中选择，还要避免0
    //             dp[i][j] = (dp[i - 1][j - 1] * (N - (j - 1)) + dp[i - 1][j] * Math.max(j - K, 0)) % MOD;
    //         }
    //     }

    //     return (int)dp[L][N];
    // }


    // 空间复杂优化
    public int numMusicPlaylists(int N, int L, int K) {
        long[] dp = new long[N + 1];
        for (int i = 1; i <= L; ++i) {
            // 在列表长度为i的情况下
            dp[0] = i == 1 ? 1 : 0;
            //在下式dp[j-1]相当于上式中的dp[i-1][j-1],所以这里当i==1时, dp[0]=1
            //就是保证当j=1时, dp[0]也即dp[0][0]==1
            for (int j = Math.min(i, N); j >= 1; --j) {
                dp[j] = (dp[j - 1] * (N - (j - 1)) + dp[j] * Math.max(j - K, 0)) % MOD;
            }
        }
        return (int) dp[N];
    }
}
// @lc code=end