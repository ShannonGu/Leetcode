/*
 * @lc app=leetcode id=376 lang=java
 *
 * [376] Wiggle Subsequence
 */
class Solution {
    // https://www.cnblogs.com/grandyang/p/5697621.html
    // public int wiggleMaxLength(int[] nums) {
    //     int len = nums.length;
    //     if (len == 0)
    //         return 0;

    //     // 有首差值为正,负两种摆动子序列;
    //     // p[i]表示到位置i时首差值为正的摆动子序列的最大长度;
    //     // q[i]表示到位置i时首差值为负的摆动子序列的最大长度;
    //     int[] p = new int[len];
    //     int[] q = new int[len];

    //     // 初始化为1;
    //     for (int i = 0; i < len; ++i) {
    //         p[i] = 1;
    //         q[i] = 1;
    //     }

    //     for (int i = 1; i < len; ++i) {
    //         for (int j = 0; j < i; ++j) {
    //             // 对[0,i)内的数进行遍历;
    //             if (nums[i] > nums[j])
    //                 // 表示nums[i]可以接在nums[j]后面,此时首差值为正;
    //                 // 更新首差值为正的摆动子序列的全局最大值;
    //                 p[i] = Math.max(p[i], q[j] + 1);
    //             else if (nums[i] < nums[j])
    //                 // 同理;
    //                 q[i] = Math.max(q[i], p[j] + 1);
    //         }
    //     }

    //     // 取摆动子序列的全局最大值;
    //     return Math.max(p[len - 1], q[len - 1]);
    // }


    public int wiggleMaxLength(int[] nums) {
        if(nums.length < 2)
            return nums.length;
        int n = nums.length;
        //up[i]表示[0, i]差值为正的wiggle sequence的最大长度
        //down[i]表示[0, i]差值为负的wiggle sequence的最大长度
        int[] up = new int[n], down = new int[n];
        up[0] = down[0] = 1;
        for (int i = 1; i < n; ++i) {
            //当前位置差值为正
            if (nums[i] > nums[i - 1]) {
                //要形成wiggle seq,只能与前面差值为负的seq连接
                up[i] = down[i - 1] + 1;
                //在i位置处差值为负的seq的最大长度不变
                down[i] = down[i - 1];
            } else if (nums[i] < nums[i - 1]) {
                up[i] = up[i - 1];
                down[i] = up[i - 1] + 1;
            } else {
                up[i] = up[i - 1];
                down[i] = down[i - 1];
            }
        }
        return Math.max(up[n - 1], down[n - 1]);
    }

    // // 对上面DP解法进行优化
    // // 使用Greedy
    // public int wiggleMaxLength(int[] nums) {
    //     //维护两个变量;
    //     int p = 1, q = 1, len = nums.length;
    //     for (int i = 1; i < len; ++i) {
    //         if (nums[i] > nums[i - 1])
    //             p = q + 1;
    //         else if (nums[i] < nums[i - 1])
    //             q = p + 1;
    //     }
    //     return Math.min(len, Math.max(p, q));
    //     //下面是例子;
    //     //  1,17,5,10,13,15,10,5,6,8
    //     //P 1 2    4  4  4     6 6 6
    //     //q 1    3          5
    //     //当遇到单调递增时,10,13,15均可接在5后面作为一种情况,所以对于这三者p相同;
    //     //当遇到单调递减时,同理;
    // }
}
