/*
 * @lc app=leetcode id=321 lang=java
 *
 * [321] Create Maximum Number
 */
class Solution {
    // https://www.youtube.com/watch?v=YYduNJfzWaA
    // https://zxi.mytechroad.com/blog/dynamic-programming/leetcode-321-create-maximum-number/
    // https://www.cnblogs.com/grandyang/p/7776979.html
    public int[] maxNumber(int[] nums1, int[] nums2, int k) {
        // 先贪心的从nums1, nums2分别挑选出最大的数
        // 设从nums1中挑选k1个数，则从nums2中挑选k2 = k-k1个数
        // 然后分别将从nums1, nums2挑选出来的数组成最大数
        // 再将他们合并成最大值
        int l1 = nums1.length, l2 = nums2.length;

        // 用作求max()的操作，因为max()中的参数要求是数组
        int[] best = new int[0];
        // 然后遍历k1,k2的分配情况
        // 找到两个数组成的全局最大值
        // 从nums1中挑选k1个数
        for (int k1 = 0; k1 <= k; ++k1) {
            // 从nums2挑选k2个数
            int k2 = k - k1;
            if (k1 > l1 || k2 > l2)
                continue;
            // 遍历，得到全局最大值
            best = max(best, 0, mergeNums(maxNums(nums1, k1), maxNums(nums2, k2)), 0);
        }
        return best;
    }

    // 在nums数组中按位置顺序，挑选k个数，组成一个最大值
    private int[] maxNums(int[] nums, int k) {
        // 用来存放挑选出来的数
        int[] ans = new int[k];
        // j表示ans中数的个数
        int j = 0;
        for (int i = 0; i < nums.length; ++i) {
            // k-j表示最多还能挑的数
            // nums.length - i > k - j表示nums中剩余的数还可供挑选，直至满足k个数
            // 如果新加入的数nums[i] > ans中的数，说明还可以组成更大的数
            while (j > 0 && nums[i] > ans[j - 1] && nums.length - i > k - j) {
                // 于是找到可以替换的起始位置
                --j;
            }
            // 还没到k个数，则继续加入
            if (j < k)
                ans[j++] = nums[i];
        }
        return ans;
    }

    // 合并两个数组，使得合并后的数组里存放组成的最大数
    private int[] mergeNums(int[] nums1, int[] nums2) {
        int l1 = nums1.length, l2 = nums2.length;
        int[] ans = new int[l1 + l2];
        // 两个数组的起点
        int s1 = 0, s2 = 0;
        // 合并后的数组ans的下标
        int index = 0;
        while (s1 != l1 || s2 != l2) {
            // 每次从两个数组中挑选一个较大的数
            ans[index++] = max(nums1, s1, nums2, s2) == nums1 ? nums1[s1++] : nums2[s2++];
        }
        return ans;
    }

    // 两个数组比较大小
    // s1表示nums1的起点位置,s2表示nums2的起点位置
    private int[] max(int[] nums1, int s1, int[] nums2, int s2) {
        // nums1开始比较的位置
        for (int i = s1; i < nums1.length; ++i) {
            // nums2开始比较的位置
            int j = s2 + i - s1;
            // nums2全部比较完成
            if (j >= nums2.length)
                return nums1;
            if (nums1[i] < nums2[j])
                return nums2;
            if (nums1[i] > nums2[j])
                return nums1;
            // nums1, nums2的当前元素相等，则分别转到下一个元素继续比较
        }
        return nums2;
    }
}
