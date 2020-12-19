import java.util.Arrays;
import java.util.List;

/*
 * @lc app=leetcode id=349 lang=java
 *
 * [349] Intersection of Two Arrays
 */
class Solution {
    public int[] intersection(int[] nums1, int[] nums2) {
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        int n = nums1.length < nums2.length ? nums1.length : nums2.length;
        int[] res = new int[n];
        int i = 0, j = 0, idx = 0;
        while (i < nums1.length && j < nums2.length) {
            if (nums1[i] < nums2[j])
                ++i;
            else if (nums1[i] > nums2[j])
                ++j;
            else {
                if (idx == 0 || res[idx - 1] != nums1[i])
                    res[idx++] = nums1[i];
                ++i;
                ++j;
            }
        }
        // return Arrays.copyOf(res, idx);
        return Arrays.copyOf(res, idx);
    }
}

