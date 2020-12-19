/*
 * @lc app=leetcode id=75 lang=java
 *
 * [75] Sort Colors
 */
class Solution {

    //三指针;
    // public void sortColors(int[] nums) {
    //     int red = 0, blue = nums.length - 1;
    //     int i = 0;
    //     while (i <= blue) {
    //         if (nums[i] == 0) {
    //             //red经过一轮操作后总是指向0或者1;
    //             swap(nums, i, red);
    //             ++i;
    //             ++red;
    //         }
    //         else if (nums[i] == 2) {
    //             swap(nums, i, blue);
    //             --blue;
    //         }
    //         else
    //             ++i;
    //     }
    // }

    // private void swap(int[] nums, int x, int y) {
    //     int tmp = nums[x];
    //     nums[x] = nums[y];
    //     nums[y] = tmp;
    // }

    //计数排序;
    public void sortColors(int[] nums) {
        int[] count = { 0, 0, 0 };
        for (int i = 0; i < nums.length; ++i) {
            ++count[nums[i]];
        }

        int idx = 0;
        for (int i = 0; i < 3; ++i) {
            for (int j = 0; j < count[i]; ++j) {
                nums[idx++] = i;
            }
        }
    }
}

