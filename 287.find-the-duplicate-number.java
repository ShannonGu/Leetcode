/*
 * @lc app=leetcode id=287 lang=java
 *
 * [287] Find the Duplicate Number
 */
class Solution {
    // public int findDuplicate(int[] nums) {
    //     if(nums.length <= 1)
    //         return -1;
    //     int l = 1, r = nums.length - 1;
    //     while (l < r) {
    //         int mid = l + r >> 1;
    //         if (count(nums, l, mid) > mid - l + 1)
    //             r = mid;
    //         else
    //             l = mid + 1;
    //     }
    //     return l;
    // }

    // public int count(int[] nums, int left, int right) {
    //     int cnt = 0;
    //     for (int i = 0; i < nums.length; ++i) {
    //         if (nums[i] <= right && nums[i] >= left)
    //             ++cnt;
    //     }
    //     return cnt;
    // }


    //Floyd's Tortoise and Hare (Cycle Detection)
    public int findDuplicate(int[] nums) {
        //与循环链表类似
        //因为nums中每个数都在[1,n]中，每个数所指向的索引都会存在
        //而且数量为n+1，是有限的,所以当中存在一个环
        //此外索引0不会出现nums中,nums[0]不会在出现在环里
        //所以可以将nums[0]当做循环链表的表头

        //先找到快慢指针相遇的位置，此时该位置一定在环内
        int tortoise = nums[0], hare = nums[0];
        do{
            //相当于慢指针
            tortoise = nums[tortoise];
            //相当于快指针,速度是慢指针的两倍
            hare = nums[nums[hare]];
        }while(tortoise != hare);


        //找到环的入口
        int p1 = nums[0], p2 = tortoise;
        while (p1 != p2) {
            p1 = nums[p1];
            p2 = nums[p2];
        }
        return p1;
    }
}

