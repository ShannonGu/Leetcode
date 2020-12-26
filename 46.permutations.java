import java.util.Arrays;
import java.util.stream.Collector;
import java.util.stream.Collectors;

/*
 * @lc app=leetcode id=46 lang=java
 *
 * [46] Permutations
 */
class Solution {
    // https://leetcode.com/problems/permutations/discuss/18239/A-general-approach-to-backtracking-questions-in-Java-(Subsets-Permutations-Combination-Sum-Palindrome-Partioning)
    private List<List<Integer>> res;

    public List<List<Integer>> permute(int[] nums) {
        res = new ArrayList<>();
        helper(nums, 0);
        return res;
    }

    private void helper(int[] nums, int cnt) {
        if (cnt == nums.length - 1) {
            // int[] 转 List<Intgeger>
            // Arrays.stream(arr) 可以替换成IntStream.of(arr)
            // 1.使用Arrays.stream将int[]转换成IntStream
            // 2.使用IntStream中的boxed()装箱, 将IntStream转换成Stream<Integer>
            // 3.使用Stream的collect()，将Stream<T>转换成List<T>，因此正是List<Integer>
            res.add(Arrays.stream(nums).boxed().collect(Collectors.toList()));
            return;
        }

        for (int i = cnt; i < nums.length; ++i) {
            swap(nums, i, cnt); // 修改当前数状态
            helper(nums, cnt + 1);
            swap(nums, i, cnt); // 还原当前数状态
        }
    }

    private void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }
}
