/*
 * @lc app=leetcode id=565 lang=java
 *
 * [565] Array Nesting
 */
class Solution {
    public int arrayNesting(int[] nums) {
        if (nums.length == 0)
            return 0;
        int res = 0, n = nums.length;
        
        //并不需要专门的数组来记录数组是否被遍历过，而是在遍历的过程中，
        //将其交换到其应该出现的位置上，因为如果某个数出现在正确的位置上，
        //那么它一定无法继续嵌套下去，这样就相当于标记了其已经访问过了，
        for (int i = 0; i < n; ++i) {
            int cnt = 1;
            while (nums[i] != i) {
                swap(nums, i, nums[i]);
                ++cnt;
            }
            res = Math.max(res, cnt);
        }
        return res;
    }
    
    private void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }
}

