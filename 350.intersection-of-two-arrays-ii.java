import java.util.Arrays;

/*
 * @lc app=leetcode id=350 lang=java
 *
 * [350] Intersection of Two Arrays II
 */
class Solution {
    public int[] intersect(int[] nums1, int[] nums2) {
        int n = nums1.length < nums2.length ? nums1.length : nums2.length;
        int[] res = new int[n];
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        int i = 0, j = 0, idx = 0;
        while (i < nums1.length && j < nums2.length) {
            if (nums1[i] < nums2[j]) {
                ++i;
            } else if (nums1[i] > nums2[j]) {
                ++j;
            } else {
                res[idx++] = nums1[i];
                ++i;
                ++j;
            }
        }
        return Arrays.copyOf(res, idx);
    }
}

