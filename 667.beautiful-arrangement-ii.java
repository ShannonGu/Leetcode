/*
 * @lc app=leetcode id=667 lang=java
 *
 * [667] Beautiful Arrangement II
 */
class Solution {
    // public int[] constructArray(int n, int k) {
    //     if (k >= n)
    //         return new int[] {};
    //     int l = 1, r = l + k;
    //     int[] ans = new int[n];
    //     int idx = 1;
    //     ans[0] = l++;
    //     while(k-- > 0 && idx < n){
    //         ans[idx] = (idx & 0x1) == 0 ? l++ : r--;
    //         idx++;
    //     }
    //     while(idx < n){
    //         ans[idx] = ++idx;
    //     }
    //     return ans;
    // }

    public int[] constructArray(int n, int k) {
        int i = 1, j = n, idx = 0;
        int[] ans = new int[n];
        //按照一大一小的顺序排列
        while (i <= j) {
            //保证当k==1是最后一个数是小的结尾，以便k==1时，递增排列
            if (k > 1) {
                //若k为奇数，首先放置小数
                if ((k & 0x1) == 1)
                    ans[idx++] = i++;
                else
                //反之，首先放置大数
                    ans[idx++] = j--;
                --k;
            } else
                //当k==1是就按照递增顺序填充
                ans[idx++] = i++;
        }
        return ans;
    }
}
