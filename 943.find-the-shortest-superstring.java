/*
 * @lc app=leetcode id=943 lang=java
 *
 * [943] Find the Shortest Superstring
 */


// @lc code=start
class Solution {
    // https://zxi.mytechroad.com/blog/searching/leetcode-943-find-the-shortest-superstring/
    // private int n;
    // private int[][] g;// g[i][j]:将words[j]拼接到words[i]后面所需要的代价(需要预处理)
    // private String[] a;
    // private int best_len;// 用于更新得到最短的superstring的长度
    // private int[] path;
    // private int[] best_path;// 长度最短的字符串的最佳路径，用于记录该字符串由A中字符串的组成的下标顺序

    // public String shortestSuperstring(String[] A) {
    //     n = A.length;
    //     g = new int[n][n];
    //     a = A;
    //     for (int i = 0; i < n; ++i) {
    //         for (int j = 0; j < n; ++j) {
    //             // 初始情况
    //             // 然后计算A[i]的后缀和A[j]的前缀的相同长度,A[j]剩下的即为代价
    //             g[i][j] = A[j].length();
    //             // k表示公共部分的长度
    //             for (int k = 1; k <= Math.min(A[i].length(), A[j].length()); ++k) {
    //                 // 计算A[i]的后缀和A[j]的前缀相同的长度
    //                 if (A[i].substring(A[i].length() - k).equals(A[j].substring(0, k)))
    //                     g[i][j] = A[j].length() - k;
    //             }
    //         }
    //     }

    //     path = new int[n];
    //     Arrays.fill(path, -1);
    //     best_len = Integer.MAX_VALUE;

    //     dfs(0, 0, 0);

    //     StringBuilder res = new StringBuilder(A[best_path[0]]);

    //     for (int k = 1; k < n; ++k) {
    //         int i = best_path[k - 1];
    //         int j = best_path[k];
    //         res.append(A[j].substring(A[j].length() - g[i][j]));
    //     }
    //     return res.toString();
    // }

    // // d表示使用的字符串的个数
    // // used记录那些字符串已经使用过了
    // // cur_len用于记录当前组成的superstring的长度
    // private void dfs(int d, int used, int cur_len) {
    //     // 剪枝条件
    //     // 如果当前组成的superstring的长度已经超过已经得到的superstring的长度
    //     // 则直接返回
    //     if (cur_len >= best_len)
    //         return;

    //     // 使用了所有了字符串
    //     if (d == n) {
    //         best_len = cur_len;
    //         best_path = path.clone();
    //         return;
    //     }

    //     for (int i = 0; i < n; ++i) {
    //         // 第i个字符串已经使用过了
    //         if ((used & (1 << i)) != 0)
    //             continue;
    //         // 路径上第d个字符串是A[i];
    //         path[d] = i;
    //         // used|(1 << i)标记第A[i]被使用
    //         // 如果是路径中第一个字符串，长度直接是a[i]的长度
    //         // 否则是a[i]加在路径上一个字符串后面的代价
    //         dfs(d + 1, used | (1 << i), d == 0 ? a[i].length() : cur_len + g[path[d - 1]][i]);
    //         path[d] = -1;
    //     }
    // }

    


    //可以将该题抽象出一个关于有向图的模型
    //该图中A中每个字符串都是一个节点
    //每个节点都有两条有向边相连，边权值为一个字符串拼接到前一个字符串所要付出的代价
    //那么问题就可以转化为求一条路径，该路径经过了图中所有节点，
    //且每个节点只经过一次，对应的最短路径上节点拼接而成的superstring即为所求
    public String shortestSuperstring(String[] A) {
        int n = A.length;
        //g[i][j]:将words[j]拼接到words[i]后面所需要的代价(需要预处理),
        //即words[j]除去与words[i]的公共部分的剩下的字符串的长度
        int[][] g = new int[n][n];
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                g[i][j] = A[j].length();
                //k表示两个字符串公共部分的长度
                for (int k = 1; k <= Math.min(A[i].length(), A[j].length()); ++k) {
                    if (A[i].substring(A[i].length() - k).equals(A[j].substring(0, k)))
                        g[i][j] = A[j].length() - k;
                }
            }
        }



        //dp[s][i]:表示经过s(二进制表示)所表示的节点仅一次，且以i字符串结尾的最短距离
        //一共n个字符串,用n位二进制数表示n个字符串的使用情况
        int[][] dp = new int[1 << n][n];
        //parent[s][i]表示在s状态下字符串i的上一个字符串
        int[][] parents = new int[1 << n][n];

        //对二者初始化
        for (int[] e : dp) {
            Arrays.fill(e, Integer.MAX_VALUE / 2);
        }

        for (int[] e : parents) {
            Arrays.fill(e, -1);
        }

        // 每条路径的只有单个字符串的初始状态
        for (int i = 0; i < n; ++i) {
            dp[1 << i][i] = A[i].length();
        }

        for (int s = 1; s < (1 << n); ++s) {
            // 当前状态下结尾的是字符串j
            for (int j = 0; j < n; ++j) {
                // 如果j没有出现在s中,则跳过
                if ((s & (1 << j)) == 0)
                    continue;
                // int preS = s - (1 << j);
                int preS = s & ~(1 << j);//得到上一个状态;
                // 与最短路径类似的更新方法
                for (int i = 0; i < n; ++i) {
                    // 基于状态preS更新其每一个路径结尾为j的最短距离
                    if (dp[preS][i] + g[i][j] < dp[s][j]) {
                        dp[s][j] = dp[preS][i] + g[i][j];
                        // 记录当前状态下字符串j的上一个字符串是i
                        parents[s][j] = i;
                    }
                }
            }
        }

        // 用于记录最后一个状态(即所有字符串都用上了)的最短路径的结尾字符串
        int idx = 0, len = Integer.MAX_VALUE;
        //最后一个状态
        int s = (1 << n) - 1;
        for (int i = 0; i < n; ++i) {
            if (dp[s][i] < len) {
                len = dp[s][i];
                idx = i;
            }
        }

        // 最后一个状态;
        s = (1 << n) - 1;
        StringBuilder res = new StringBuilder("");
        while (s > 0) {
            //寻找idx的上一个字符串
            int i = parents[s][idx];
            //已经遍历到了第一个字符串
            if (i < 0)
                res.insert(0, A[idx]);
            else
            //A[idx].length() - g[i][idx]表示公共部分长度
                res.insert(0, A[idx].substring(A[idx].length() - g[i][idx]));

            //退回到上一个状态
            s &= ~(1 << idx);

            //转到上一个字符串
            idx = i;
        }
        return res.toString();
    }
}
// @lc code=end
