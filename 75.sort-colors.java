/*
 * @lc app=leetcode id=75 lang=java
 *
 * [75] Sort Colors
 */
class Solution {

    // 三指针;
    public void sortColors(int[] nums) {
        // red和blue分别表示当前的0和2应该放置的位置
        int red = 0, blue = nums.length - 1;
        int i = 0;
        while (i <= blue) {
            if (nums[i] == 0) {
                // i位置是0
                swap(nums, i, red);
                ++i;
                ++red;
            } else if (nums[i] == 2) {
                // i位置为2
                swap(nums, i, blue);
                // 这里i不变，因为还要继续考察blue位置交换的数是什么
                --blue;
            } else
                // i位置是1，寻找下一个需要排序的数
                ++i;
        }
    }

    private void swap(int[] nums, int x, int y) {
        int tmp = nums[x];
        nums[x] = nums[y];
        nums[y] = tmp;
    }

    // 计数排序;
    // public void sortColors(int[] nums) {
    // int[] count = { 0, 0, 0 };
    // for (int i = 0; i < nums.length; ++i) {
    // ++count[nums[i]];
    // }

    // int idx = 0;
    // for (int i = 0; i < 3; ++i) {
    // for (int j = 0; j < count[i]; ++j) {
    // nums[idx++] = i;
    // }
    // }
    // }
}
