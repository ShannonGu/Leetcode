/*
 * @lc app=leetcode id=324 lang=java
 *
 * [324] Wiggle Sort II
 */
class Solution {
    // http://buttercola.blogspot.com/2016/01/leetcode-wiggle-sort-ii.html
    // 思路就是取数组的中位数，将数组分成两部分，一部分比中位数小，一部分比中位数大;
    // 然后一边取小，取大，构成wiggle sequence
    public void wiggleSort(int[] nums) {
        int len = nums.length;
        if (len <= 1)
            return;

        // 这里已经将中位数放置到最终位置上了;
        int midIdx = findMedian(nums, 0, len - 1, (len - 1) >> 1);
        int[] tmp = new int[len];
        int left = 0, right = len - 1;
        // 使用辅助数组将小于中位数的数放置到中位数前面,大于等于的放置到中位数后面;
        for (int i = 0; i < len; ++i) {
            if (nums[i] < nums[midIdx]) {
                tmp[left] = nums[i];
                ++left;
            } else if (nums[i] > nums[midIdx]) {
                tmp[right] = nums[i];
                --right;
            }
        }

        // 剩余位置中位数;可能有多个数于中位数相等;
        for (int i = left; i <= right; ++i) {
            tmp[i] = nums[midIdx];
        }

        // left从中位数开始,right最大数开始;
        // 确保一小一大排列;
        left = ((len - 1) >> 1);
        right = len - 1;
        for (int i = 0; i < len; ++i) {
            if ((i & 1) == 0) {
                nums[i] = tmp[left];
                --left;
            } else {
                nums[i] = tmp[right];
                --right;
            }
        }
    }

    // 取得中位数的索引;
    private int findMedian(int[] nums, int lo, int hi, int k) {
        if (lo >= hi) {
            return lo;
        }

        int pivot = partition(nums, lo, hi);
        if (pivot == k)
            return pivot;
        //找到的排序后的pivot位置上数太大了,比之小的数超过了k
        //pivot>k,则在nums[lo, pivot - 1]中寻找一个数使得该数在排序后正好在数组中的k位置上
        if (pivot > k)
            return findMedian(nums, lo, pivot - 1, k);
        else
        //反之在nums[pivot + 1, hi]中寻找
            return findMedian(nums, pivot + 1, hi, k);
    }

    // 类似于快排;
    private int partition(int[] nums, int lo, int hi) {
        int pivot = nums[lo];
        int i = lo + 1, j = hi;
        while (i <= j) {
            //这里确保pivot前面都是比之小的
            while (i <= j && nums[i] < pivot)
                ++i;
            //确保pivot后面都是不小于它的
            //因为要确保形成wiggle sequence
            while (i <= j && nums[j] >= pivot)
                --j;
            if (i <= j)
                swap(nums, i, j);
        }
        // 注意这里已经将pivot放置到目标位置上了;
        swap(nums, lo, j);

        // 返回pivot所在位置;
        return j;
    }

    private void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }
}
