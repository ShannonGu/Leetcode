/*
 * @lc app=leetcode id=668 lang=java
 *
 * [668] Kth Smallest Number in Multiplication Table
 */
class Solution {
    //BinarySearch
    public int findKthNumber(int m, int n, int k) {
        if(m == 0 || n == 0 || k > m * n)
            return 0;
        int l = 1, r = m * n;
        while (l < r) {
            int mid = l + r >> 1;
            int cnt = helper(m, n, mid);
            if(cnt >= k)
                r = mid;
            else
                l = mid + 1;
        }
        return l;
    }
    
    private int helper(int m, int n, int target) {
        int i = m, j = 1;
        int cnt = 0;
        while (i > 0 && j <= n) {
            if (i * j <= target) {
                cnt += i;
                ++j;
            } else
                --i;
        }
        return cnt;
    }
}

