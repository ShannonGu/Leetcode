/*
 * @lc app=leetcode id=215 lang=java
 *
 * [215] Kth Largest Element in an Array
 */
class Solution {
    //利用快排的partition方法
    //使得前k-1个数均>=pivot, 后面的数均<pivot
    public int findKthLargest(int[] nums, int k) {
        int left = 0, right = nums.length - 1;
        while (true) {
            int pos = partition(nums, left, right);
            if(pos == k - 1)
                return nums[pos];
            else if(pos > k - 1)
                right = pos - 1;
            else
                left = pos + 1;
        }
    }
    
    private int partition(int[] nums, int lo, int hi) {
        int pivot = nums[lo];
        int i = lo + 1, j = hi;
        while (i <= j) {
            while (i <= j && nums[i] >= pivot)
                ++i;
            while (i <= j && nums[j] < pivot)
                --j;
            if (i <= j)
                swap(nums, i, j);
        }
        swap(nums, lo, j);
        return j;
    }
    
    private void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }
}

