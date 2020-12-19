
/*
 * @lc app=leetcode id=611 lang=java
 *
 * [611] Valid Triangle Number
 */
class Solution {
    public int triangleNumber(int[] nums) {
        Arrays.sort(nums);
        int cnt = 0, n = nums.length;
        for (int i = n - 1; i >= 2; --i) {
            int l = 0, r = i - 1;
            while (l < r) {
                if (nums[l] + nums[r] > nums[i]) {
                    //两小边之和大于最大的第三边
                    //则两小边之间的每一条边都能作为最小边
                    cnt += r - l;
                    //缩小第二大边,继续寻找
                    --r;
                } else
                    //增大最小边
                    ++l;
            }
        }
        return cnt;
    }
}

