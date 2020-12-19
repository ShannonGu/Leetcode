/*
 * @lc app=leetcode id=801 lang=java
 *
 * [801] Minimum Swaps To Make Sequences Increasing
 */
class Solution {

    //递归TLE
    // public int minSwap(int[] A, int[] B) {
    //     int res = Integer.MAX_VALUE;
    //     helper(A, B, 0, 0, res);
    //     return res;
    // }

    // private void helper(int[] A, int[] B, int i, int cur, int res) {
    //     if (cur >= res)
    //         return;

    //     if (i == A.length) {
    //         res = Math.min(res, cur);
    //         return;
    //     }

    //     if (i == 0 || A[i] > A[i - 1] && B[i] > B[i - 1]) {
    //         helper(A, B, i + 1, cur, res);
    //     }

    //     if (i == 0 || A[i] > B[i - 1] && B[i] > A[i - 1]) {
    //         swap(A, B, i);
    //         helper(A, B, i + 1, cur + 1, res);
    //         swap(A, B, i);
    //     }
    // }

    // private void swap(int[] A, int[] B, int i) {
    //     int tmp = A[i];
    //     A[i] = B[i];
    //     B[i] = tmp;
    // }


    // //http://zxi.mytechroad.com/blog/dynamic-programming/leetcode-801-minimum-swaps-to-make-sequences-increasing/
    // //https://www.cnblogs.com/grandyang/p/9311385.html
    // public int minSwap(int[] A, int[] B) {
    //     int n = A.length;

    //     //假设A[0, i-1], B[0, i-1]已经满足严格递增;
    //     //swap[i]表示交换A[i], B[i]使得A,B均严格递增所需要的最小交换次数;
    //     int[] swap = new int[n];

    //     //keep[i]表示不交换A[i], B[i]使得A,B均严格递增所需要的最小交换次数;
    //     int[] keep = new int[n];

    //     //初始化两个数组;
    //     for (int i = 0; i < n; ++i) {
    //         swap[i] = keep[i] = Integer.MAX_VALUE;
    //     }

    //     //考虑第一个元素
    //     //交换
    //     swap[0] = 1;
    //     //不交换
    //     keep[0] = 0;

    //     for (int i = 1; i < n; ++i) {

    //         //因为题目保证能够满足题意
    //         //所以总是会出现下面两种情况之一或者全部
    //         //.....2 3
    //         //.....1 4
    //         if (A[i] > A[i - 1] && B[i] > B[i - 1]) {
    //             //A[i], B[i]不需要交换;
    //             keep[i] = keep[i - 1];
    //             //交换A[i], B[i]， 同时交换A[i-1], B[i-1]
    //             swap[i] = swap[i - 1] + 1;
    //         }

    //         //.....2 3
    //         //.....1 4
    //         if (A[i] > B[i - 1] && B[i] > A[i - 1]) {
    //             //交换A[i], B[i],并且保持A[i-1], B[i-1]不变
    //             //再与之前可能更新过的swap[i]比较
    //             swap[i] = Math.min(swap[i], keep[i - 1] + 1);

    //             //保持A[i], B[i]不变,交换A[i - 1], B[i - 1]
    //             //再与之前更新过的keep[i]比较
    //             keep[i] = Math.min(keep[i], swap[i - 1]);
    //         }
    //     }
    //     return Math.min(swap[n - 1], keep[n - 1]);
    // }
    

    
    //对上面解法的空间复杂度优化;
    public int minSwap(int[] A, int[] B) {
        int n = A.length;

        int swap = 1, keep = 0;
        for (int i = 1; i < n; ++i) {
            //swap, keep均表示上一个位置的状态
            //tmpS, tmpK表示当前状态;
            int tmpS = Integer.MAX_VALUE, tmpK = Integer.MAX_VALUE;
            if (A[i] > A[i - 1] && B[i] > B[i - 1]) {
                tmpK = Math.min(tmpK, keep);
                tmpS = Math.min(swap + 1, tmpS);
            }

            if (A[i] > B[i - 1] && B[i] > A[i - 1]) {
                tmpS = Math.min(tmpS, keep + 1);
                tmpK = Math.min(tmpK, swap);
            }

            swap = tmpS;
            keep = tmpK;
        }
        return Math.min(swap, keep);
    }
}

