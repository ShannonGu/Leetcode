/*
 * @lc app=leetcode id=845 lang=java
 *
 * [845] Longest Mountain in Array
 */
class Solution {

    //https://www.cnblogs.com/grandyang/p/10459400.html
    // public int longestMountain(int[] A) {

    //     if (A.length < 3) {
    //         return 0;
    //     }
    //     int len = A.length;

    //     //up[i]表示A[0, i]中以A[i]结尾的最长连续递增的subarray的长度;
    //     int[] up = new int[len];

    //     //down[i]表示A[i, n - 1]中以A[i]开头的最长连续递减的subarray的长度;
    //     int[] down = new int[len];

    //     int res = 0;
    //     for (int i = 1; i < len; ++i) {
    //         if (A[i] > A[i - 1])
    //             up[i] = up[i - 1] + 1;
    //     }

    //     for (int i = len - 2; i >= 0; --i) {
    //         if (A[i] > A[i + 1]) 
    //             down[i] = down[i + 1] + 1;
    //         //满足mountain的条件
    //         if(up[i] > 0 && down[i] > 0)
    //             res = Math.max(res, up[i] + down[i] + 1);
    //     }

    //     return res;
    // }

    // public int longestMountain(int[] A) {
    //     int res = 0, i = 0, len = A.length;
    //     while (i < len - 1) {

    //         //首先找到mountain左边山脚
    //         while (i < len - 1 && A[i] >= A[i + 1])
    //             ++i;

    //         //然后找到mountain山顶
    //         int peak = i;
    //         while (peak < len - 1 && A[peak] < A[peak + 1])
    //             ++peak;

    //         //继续找moutain右边山脚
    //         int j = peak;
    //         while (j < len - 1 && A[j] > A[j + 1])
    //             ++j;

    //         //满足i < peak < j时，就可以形成一个mountain;
    //         //更新;
    //         if (i < peak && peak < j)
    //             res = Math.max(res, j - i + 1);
    //         i = j;
    //     }
    //     return res;
    // }

    public int longestMountain(int[] A) {

        int res = 0, len = A.length;

        //首先遍历可能成为peak的位置[1, n-1);
        for (int i = 1; i < len - 1; ++i) {
            if (A[i - 1] < A[i] && A[i] > A[i + 1]) {
                //然后寻找左右两个山脚
                int l = i - 1, r = i + 1;
                while (l > 0 && A[l] > A[l - 1])
                    --l;
                while (r < len - 1 && A[r] > A[r + 1])
                    ++r;
                res = Math.max(res, r - l + 1);
            }
        }
        return res;
    }
}
