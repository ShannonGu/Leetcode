/*
 * @lc app=leetcode id=1024 lang=java
 *
 * [1024] Video Stitching
 */

// @lc code=start
class Solution {
    //Greedy
    //https://massivealgorithms.blogspot.com/2019/04/leetcode-1024-video-stitching.html
    // public int videoStitching(int[][] clips, int T) {
    //     //先将clips每个片段按照起始时间排序
    //     Arrays.sort(clips, (c1, c2) -> c1[0] - c2[0]);

    //     //curEd表示当前能够延伸到最远的右端点位置
    //     int cnt = 0, curEd = 0;
    //     int i = 0, n = clips.length;
    //     while (i < n) {
    //         //若当前遍历到的片段的起点超过了curEd，
    //         //表示[curEd, clips[i][0]]是一段空白，无法覆盖到, 所以也就覆盖[0,T]
    //         if (clips[i][0] > curEd)
    //             return -1;
    //         //不断更新curEd
    //         int maxEd = curEd;
    //         //遍历处于curEd范围内的区间，更新curEd
    //         while (i < n && clips[i][0] <= curEd) {
    //             maxEd = Math.max(maxEd, clips[i][1]);
    //             ++i;
    //         }
    //         //此时选择右端点最大的那个区间作为一个覆盖区间
    //         ++cnt;
    //         //更新curEd
    //         curEd = maxEd;
    //         //如果已经覆盖了T，则cnt即为最少需要的区间数
    //         if (curEd >= T)
    //             return cnt;
    //     }
    //     return -1;
    // }

    //DP解法
    //https://zxi.mytechroad.com/blog/leetcode/leetcode-weekly-contest-131-1021-1022-1023-1024/
    // private final int kInf = 101;

    // public int videoStitching(int[][] clips, int T) {
    //     //dp[i][j]表示覆盖[i,j]最少需要的clip数
    //     int[][] dp = new int[T + 1][T + 1];
    //     for (int[] e : dp) {
    //         Arrays.fill(e, kInf);
    //     }
    //     //遍历每个clip看其能覆盖那些区间
    //     for (int[] clip : clips) {
    //         int st = clip[0], ed = clip[1];
    //         //考察长度为l的每个区间[i,j],
    //         for (int l = 1; l <= T; ++l) {
    //             for (int i = 0; i <= T - l; ++i) {
    //                 int j = i + l;

    //                 if (st > j || ed < i)
    //                     //当前clip与[i,j]无交集
    //                     continue;
    //                 else if (st <= i && ed >= j)
    //                     //当前clip完全覆盖[i,j]
    //                     dp[i][j] = 1;
    //                 else if (ed >= j)
    //                     //i < st
    //                     //[i, j]与clip有公共部分[st, j], 而[i,j]中的[i, st]还需要另外计算
    //                     dp[i][j] = Math.min(dp[i][j], dp[i][st] + 1);
    //                 else if (st <= i)
    //                     //j > ed
    //                     //此时有公共部分[i, ed], [ed, j]需另外计算
    //                     dp[i][j] = Math.min(dp[i][j], dp[ed][j] + 1);
    //                 else
    //                     //i < st, ed < j
    //                     //有公共部分[st, ed]
    //                     dp[i][j] = Math.min(dp[i][j], dp[i][st] + 1 + dp[ed][j]);
    //             }
    //         }
    //     }
    //     return dp[0][T] == kInf ? -1 : dp[0][T];
    // }



    public int videoStitching(int[][] clips, int T) {
        //dp[i]表示覆盖[0,i]需要的最少clip数
        int[] dp = new int[T + 1];
        //初始化为T+1,默认不可能
        Arrays.fill(dp, T + 1);

        dp[0] = 0;

        for (int i = 0; i <= T; i++) {
            for (int[] clip : clips) {
                //如果i在clip区间中，说明[0, clip[0]]可以有dp[clip[0]]得到
                //而[clip[0], i]这一段是需要新加入一个区间予以覆盖
                if (clip[0] <= i && i <= clip[1])
                    dp[i] = Math.min(dp[i], dp[clip[0]] + 1);
            }
        }
        
        return dp[T] == T + 1 ? -1 : dp[T];
    }


}
// @lc code=end