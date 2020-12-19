/*
 * @lc app=leetcode id=887 lang=java
 *
 * [887] Super Egg Drop
 */
class Solution {
    //TLE,复杂度太高
    // public int superEggDrop(int K, int N) {
    //     //dp[i][j]表示i个鸡蛋，测j层楼所需要的最小操作次数
    //     int[][] dp = new int[K + 1][N + 1];

    //     //1个鸡蛋只能测完所有楼层
    //     for(int j = 1; j <= N; ++j)
    //         dp[1][j] = j;

    //     for (int i = 2; i <= K; ++i) {
    //         for (int j = 1; j <= N; ++j) {
    //             dp[i][j] = j;
    //             //对于任何k层扔鸡蛋的时候有两种情况
    //             //1.鸡蛋碎掉，则需要用i-1个鸡蛋测k-1层，所以需要dp[i-1][k-1];
    //             //2.鸡蛋没碎，则需要用i个鸡蛋测j-k层，所以需要dp[i][j-k];
    //             //由于要确保测出来，所以需要取最坏情况
    //             //再加上第k层的1次操作
    //             for (int k = 1; k < j; ++k) {
    //                 dp[i][j] = Math.min(dp[i][j], Math.max(dp[i - 1][k - 1], dp[i][j - k]) + 1);
    //             }
    //         }
    //     }
    //     return dp[K][N];
    // }

    //TLE
    // public int superEggDrop(int K, int N) {
    //     mem = new int[K + 1][N + 1];
    //     for(int[] m : mem)
    //         Arrays.fill(m, Integer.MAX_VALUE);
    //     return helper(K, N);
    // }

    // private int helper(int k, int n) {
    //     if(k <= 0)
    //         return 0;
    //     if (k == 1)
    //         //一个鸡蛋需要每一层
    //         return n;
    //     if(n <= 1)
    //         return n;
    //     if(mem[k][n] != Integer.MAX_VALUE)
    //         return mem[k][n];
    //     int ans = Integer.MAX_VALUE;
    //     for (int i = 1; i <= n; ++i) {
    //         //两种情况：
    //         //1.在i层鸡蛋碎了，需要用k-1个鸡蛋测i-1层
    //         //2.在i层鸡蛋没碎，需要用k个鸡蛋测n-i层;
    //         ans = Math.min(ans, 1 + Math.max(helper(k - 1, i - 1), helper(k, n - i)));
    //     }
    //     mem[k][n] = ans;
    //     return ans;
    // }

    // //mem[i][j]:表示i个鸡蛋测j层楼所需要的最小次数
    // private int[][] mem;





    // // https://www.cnblogs.com/grandyang/p/11048142.html
    // // https://zxi.mytechroad.com/blog/dynamic-programming/leetcode-891-super-egg-drop/
    
    // //使用BinarySearch进行优化

    // //mem[i][j]表示i个鸡蛋测j层楼所需要的最小操作次数
    // private int[][] mem;
    
    // public int superEggDrop(int K, int N) {
    //     mem = new int[K + 1][N + 1];
    //     for (int[] m : mem)
    //         Arrays.fill(m, Integer.MAX_VALUE);
    //     return helper(K, N);
    // }

    // private int helper(int k, int n) {
    //     if(k <= 0)
    //         return 0;
    //     if(k == 1)
    //         return n;
    //     if(n <= 1)
    //         return n;
    //     if(mem[k][n] != Integer.MAX_VALUE)
    //         return mem[k][n];
        
    //     //有前面的TLE的情况可以看出，
    //     //broken[i] = helper(k-1, i - 1)是随着i的递增而递增的
    //     //unbroken[i] = helper(k, n - i)是随着i的递增而递减的
    //     //mem[k][n] = 1 + min(max(broken[i], unbroken[i])), 1<= i <=n
    //     //所以需要找到最小的i使得broken[i]>=unbroken[i],
    //     //此时可以最小化max(broken[i], unbroken[i]);
    //     //于是可以用二分查找，进行优化
    //     //在[1,n]内找到第一个i是的broken[i] >= unbroken[i];
    //     int l = 1, r = n;
    //     while (l < r) {
    //         int mid = l + r >> 1;
    //         int broken = helper(k - 1, mid - 1);
    //         int unbroken = helper(k, n - mid);
    //         if (broken >= unbroken)
    //             r = mid;
    //         else
    //             l = mid + 1;
    //     }
    //     mem[k][n] = 1 + helper(k - 1, l - 1);
    //     return mem[k][n];
    // }
    



    // public int superEggDrop(int K, int N) {
    //     int[][] dp = new int[K + 1][N + 1];
    //     for(int j = 1; j <= N; ++j)
    //         dp[1][j] = j;
    //     for (int i = 2; i <= K; ++i) {
    //         for (int j = 1; j <= N; ++j) {
    //             dp[i][j] = j;
    //             int l = 1, r = j;
    //             while (l < r) {
    //                 int mid = l + r >> 1;
    //                 if (dp[i - 1][mid - 1] >= dp[i][j - mid])
    //                     r = mid;
    //                 else
    //                     l = mid + 1;
    //             }

    //             dp[i][j] = Math.min(dp[i][j], Math.max(dp[i - 1][l - 1], dp[i][j - l]) + 1);
    //         }
    //     }
    //     return dp[K][N];
    // }




    //还可以进一步优化
    public int superEggDrop(int K, int N) {
        //由于对于固定的k,dp[i][j-k]会随着j的递增而递增,
        //最优决策点也会随着j的递增而递增
        //所以在每次移动j后，从上一次的最优决策点的位置来继续向后查找最优点即可
        //复杂度为O(KN)

        int[][] dp = new int[K + 1][N + 1];
        for(int j = 1; j <= N; ++j)
            dp[1][j] = j;
            
        for (int i = 2; i <= K; ++i) {
            //s表示当前j值下的最优决策点
            int s = 1;
            for (int j = 1; j <= N; ++j) {
                dp[i][j] = j;
                while (s < j && dp[i - 1][s - 1] < dp[i][j - s])
                    ++s;
                dp[i][j] = Math.min(dp[i][j], Math.max(dp[i - 1][s - 1], dp[i][j - s]) + 1);
            }
        }
        return dp[K][N];
    }
}

