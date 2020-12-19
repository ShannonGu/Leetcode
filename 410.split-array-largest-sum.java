/*
 * @lc app=leetcode id=410 lang=java
 *
 * [410] Split Array Largest Sum
 */
class Solution {
    // https://www.youtube.com/watch?v=_k-Jb4b7b_0
    // // 记忆化数组加+DFS;
    // // sums[i]表示累加和;
    // private int[] sums = new int[1001];
    // // mem[i][j]表示nums[0, i]之间被分成j个group的最大值之间的最小值;
    // private int[][] mem = new int[1001][51];

    // public int splitArray(int[] nums, int m) {
    //     int n = nums.length;
    //     for (int i = 0; i < n; ++i) {
    //         for (int j = 0; j <= m; ++j)
    //             mem[i][j] = Integer.MAX_VALUE;
    //     }

    //     sums[0] = nums[0];
    //     // 计算数组的累加和;
    //     for (int i = 1; i < n; ++i) {
    //         sums[i] = sums[i - 1] + nums[i];
    //     }
    //     return helper(nums, n - 1, m);
    // }

    // private int helper(int[] nums, int k, int m) {
    //     // 表示[0,k]只能分成一组
    //     if (m == 1)
    //         return sums[k];
    //     // 表示[0,k]之间的数的个数小于需要划分的组数，无法划分;
    //     if (m > k + 1)
    //         return Integer.MAX_VALUE;
    //     // 表示[0,k]之间该种情况已经计算过;
    //     if (mem[k][m] != Integer.MAX_VALUE)
    //         return mem[k][m];
    //     // 设置每种划分最大值的全局最小值;
    //     int ans = Integer.MAX_VALUE;
    //     //遍历划分点
    //     for (int i = 0; i < k; ++i) {
    //         // 递归求解每种划分的最大值;
    //         // 划分出[i+1, k]一组,剩下[0, i]继续划分m-1;
    //         int tmp = Math.max(helper(nums, i, m - 1), sums[k] - sums[i]);
    //         // 更新最大值之间的最小值;
    //         ans = Math.min(tmp, ans);
    //     }

    //     // 更新记忆数组;
    //     mem[k][m] = ans;
    //     return mem[k][m];
    // }



    
    // public int splitArray(int[] nums, int m) {
    //     int n = nums.length;

    //     // 累加和
    //     int[] sums = new int[n];
    //     // dp[i][j]表示将[0,j]之间的数划分成i个group的所得到各种划分的最大值的最小值;
    //     int[][] dp = new int[m + 1][n];
    //     for (int i = 0; i <= m; ++i) {
    //         for (int j = 0; j < n; ++j) {
    //             dp[i][j] = Integer.MAX_VALUE;
    //         }
    //     }
    //     sums[0] = nums[0];
    //     for (int i = 1; i < n; ++i)
    //         sums[i] = sums[i - 1] + nums[i];

    //     // 若只划分成一组，直接是整个数组的累加和;
    //     for (int i = 0; i < n; ++i)
    //         dp[1][i] = sums[i];
    
    //     // i表示划分group数;
    //     for (int i = 2; i <= m; ++i) {
    //         // j表示数的个数;
    //         // j应>=i-1,因为被划分的数的个数应>=组数;
    //         // [0,j]之间的个数应>=i;
    //         for (int j = i - 1; j < n; ++j) {
    //             // k为最右边分割点
    //             for (int k = 0; k < j; ++k) {
    //                 int tmp = Math.max(dp[i - 1][k], sums[j] - sums[k]);
    //                 // 更新所有划分的最大值的最小值;
    //                 dp[i][j] = Math.min(dp[i][j], tmp);
    //             }
    //         }
    //     }
    //     return dp[m][n - 1];
    // }



    // Binary Search
    // https://www.cnblogs.com/grandyang/p/5933787.html
    public int splitArray(int[] nums, int m) {
        long l = 0, r = 0;
        //所有划分产生的每组和的最大值的最小值
        //若m==1,则整个nums数组就是一个子数组
        //若m==nums.length,则每个数字就是一个子数组,划分每组和的最大值中的最小值max(nums)
        //所以所求在[max(nums), sum(nums) + 1)之间
        //我们要尽量缩小这个最小值
        for (int i = 0; i < nums.length; ++i) {
            l = Math.max(nums[i], l);
            r += nums[i];
        }

        while (l < r) {
            //mid作为每个划分中每组和的上界
            long mid = l + ((r - l) >> 1);
            //判断是否可以划分出以mid为上界的<=m组
            //如果可以，继续尝试减小上界,使组的个数逼近m;
            if (canSplit(nums, m, mid))
                r = mid;
            else
                //否则，说明这个上界太小了，导致划分的组数>m了
                l = mid + 1;
        }
        return (int) l;
    }

    private boolean canSplit(int[] nums, int m, long sum) {
        //sum为每个组的和的上界
        //cnt为组的个数
        int cnt = 1, curSum = 0;
        for (int i = 0; i < nums.length; ++i) {
            curSum += nums[i];

            //当前组的和大于sum
            if (curSum > sum) {
                //重新开始划分一个新组
                curSum = nums[i];
                //组的个数加1;
                ++cnt;
                //大于m则不能使用sum作为上界
                if (cnt > m)
                    return false;
            }
        }
        //否则可以划分成m组以内，使得每个组的和均<=sum;
        return true;
    }
}
