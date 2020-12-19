/*
 * @lc app=leetcode id=80 lang=java
 *
 * [80] Remove Duplicates from Sorted Array II
 */
class Solution {
    //https://www.cnblogs.com/grandyang/p/4329295.html
    public int removeDuplicates(int[] nums) {
        if(nums.length < 3)
            return nums.length;
        int n = nums.length;

        //cnt表示还能重复几次
        int pre = 0, cur = 1, cnt = 1;
        while (cur < n) {
            //若cnt==0，表示此时pre指向的是第二次重复的元素
            //则cur前进一步，寻找下一个不同的元素
            if (nums[pre] == nums[cur] && cnt == 0)
                ++cur;
            else {
                //否则,若cnt不等于0,说明pre指向的是第一个元素
                //cur指向的是第一个重复元素
                //--cnt
                if(nums[pre] == nums[cur])
                    --cnt;
                else
                //若两者不等，说明pre指向的元素只出现了一次
                    cnt = 1;
                //pre可以向前移动放置元素
                nums[++pre] = nums[cur++];
            }
        }
        return pre + 1;
    }
}
