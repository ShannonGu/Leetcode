/*
 * @lc app=leetcode id=88 lang=java
 *
 * [88] Merge Sorted Array
 */
class Solution {
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int i = m - 1, j = n - 1;
        int idx = m + n - 1;
        while (i >= 0 && j >= 0) {
            if (nums1[i] > nums2[j])
                nums1[idx--] = nums1[i--];
            else
                nums1[idx--] = nums2[j--];
        }
        if (j >= 0) {
            for (int k = j; k >= 0; --k)
                nums1[k] = nums2[k];
        }
    }
}
