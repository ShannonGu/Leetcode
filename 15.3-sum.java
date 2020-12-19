import java.util.Arrays;

/*
 * @lc app=leetcode id=15 lang=java
 *
 * [15] 3Sum
 */
class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        if (nums.length < 3)
            return new ArrayList<>();
        Arrays.sort(nums);
        List<List<Integer>> res = new ArrayList<>();
        for (int i = 0; i < nums.length; ++i) {
            if (nums[i] > 0)
                break;
            if (i > 0 && nums[i] == nums[i - 1])
                continue;
            int l = i + 1, r = nums.length - 1;
            int target = -nums[i];
            while (l < r) {
                if (nums[l] + nums[r] == target) {
                    res.add(Arrays.asList(nums[i], nums[l], nums[r]));
                    while (l < r && nums[l] == nums[l + 1])
                        ++l;
                    while (l < r && nums[r] == nums[r - 1])
                        --r;
                    ++l;
                    --r;
                } else if (nums[l] + nums[r] > target)
                    --r;
                else
                    ++l;
            }
        }
        return res;
    }
}
