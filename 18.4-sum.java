import java.util.Arrays;

/*
 * @lc app=leetcode id=18 lang=java
 *
 * [18] 4Sum
 */
class Solution {
    public List<List<Integer>> fourSum(int[] nums, int target) {
        int n = nums.length;
        Arrays.sort(nums);
        List<List<Integer>> res = new ArrayList<>();
        for (int i = 0; i < n - 3; ++i) {
            //去重
            if (i > 0 && nums[i] == nums[i - 1])
                continue;
            for (int j = i + 1; j < n - 2; ++j) {
                //去重
                if (j > i + 1 && nums[j] == nums[j - 1])
                    continue;
                int l = j + 1, r = n - 1;
                while (l < r) {
                    int sum = nums[i] + nums[j] + nums[l] + nums[r];
                    if (sum == target) {
                        res.add(Arrays.asList(nums[i], nums[j], nums[l], nums[r]));
                        //去重
                        while (l < r && nums[l] == nums[l + 1])
                            ++l;
                        while (l < r && nums[r] == nums[r - 1])
                            --r;
                        ++l;
                        --r;
                    } else if (sum < target)
                        ++l;
                    else
                        --r;
                }
            }
        }
        return res;
    }
}
