import java.util.Collections;

/*
 * @lc app=leetcode id=473 lang=java
 *
 * [473] Matchsticks to Square
 */
class Solution {
    //https://www.cnblogs.com/grandyang/p/6238425.html
    int[] sums;
    List<Integer> nums_;
    public boolean makesquare(int[] nums) {
        if (nums.length < 4)
            return false;
        int sum = 0;
        for (int num : nums)
            sum += num;
        //首先判断数组和能够被4整除
        if (sum % 4 != 0)
            return false;
        sums = new int[4];
        nums_ = new ArrayList<>();

        //然后将数组从大到小排序
        // nums = IntStream.of(nums).boxed().sorted(Comparator.reverseOrder()).mapToInt(Integer::intValue).toArray();
        //将数组nums转换为List nums_
        nums_ = Arrays.stream(nums).boxed().collect(Collectors.toList());
        Collections.sort(nums_, Collections.reverseOrder());

        //检查是否可以组成4个和为sum/4的子序列
        return helper(nums_, 0, sum / 4);
    }
    
    private boolean helper(List<Integer> nums_, int pos, int target) {
        //数组中的每个数字已经搭配完毕
        if (pos == nums_.size())
            //检查每部分的和是否==target
            return sums[0] == target && sums[1] == target && sums[2] == target;
        for (int i = 0; i < 4; ++i) {
            //sums[i]加上当前数超过target
            //需要将target重新加入到新的一组
            if (sums[i] + nums_.get(pos) > target)
                continue;
            sums[i] += nums_.get(pos);
            if (helper(nums_, pos + 1, target))
                return true;
            sums[i] -= nums_.get(pos);
        }
        return false;
    }
}

