/*
 * @lc app=leetcode id=228 lang=java
 *
 * [228] Summary Ranges
 */
class Solution {
    public List<String> summaryRanges(int[] nums) {
        if (nums.length == 0)
            return new ArrayList<>();
        List<String> res = new ArrayList<>();
        int i = 0, j = 0, n = nums.length;
        while (j < n) {
            while (j < n && j - i == nums[j] - nums[i])
                ++j;
            StringBuilder tmp = new StringBuilder("");
            if (j < n) {
                if (j - 1 != i) {
                    tmp.append(nums[i]).append("->").append(nums[j - 1]);
                } else
                    tmp.append(nums[i]);
                i = j;
            } else {
                if (i < n - 1)
                    tmp.append(nums[i]).append("->").append(nums[n - 1]);
                else
                    tmp.append(nums[i]);
            }
            res.add(tmp.toString());
        }
        return res;
    }
}
