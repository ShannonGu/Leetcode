/*
 * @lc app=leetcode id=740 lang=java
 *
 * [740] Delete and Earn
 */

// @lc code=start
class Solution {
    // https://zxi.mytechroad.com/blog/dynamic-programming/leetcode-740-delete-and-earn/
    public int deleteAndEarn(int[] nums) {
        // 观察得知,如果拿走nums[i]，则可以将所有与其一样的copy都拿走
        // 同时不能拿走nums[i-1], nums[i+1]
        // 于是该题和198 house-robber相似，只能一个隔一个的rob房子

        if (nums.length == 0)
            return 0;

        // 先取得最大最小值
        int mx = 0, mn = Integer.MAX_VALUE;
        for (int num : nums) {
            mx = Math.max(mx, num);
            mn = Math.min(mn, num);
        }
        
        // 然后创建与nums数的范围一样大的数组
        int[] house = new int[mx - mn + 1];
        // 遍历nums将每个num放入到house中对应的位置
        // nums中没有出现的数，其在house中位置值为0，视为没有价值
        for (int num : nums) {
            house[num - mn] += num;
        }
        
        return rob(house);
    }

    private int rob(int[] nums) {
        int n = nums.length;
        // rob表示抢当前房子能获得最大收益,notRob表示不抢当前房子获得的最大收益
        int rob = 0, notRob = 0;
        for (int i = 0; i < n; ++i) {
            int preRob = rob, preNotRob = notRob;
            // 抢当前的房子，说明前一个房子不能抢
            rob = preNotRob + nums[i];
            // 不抢当前的房子,则有两种状态比较前一个房子抢和没抢
            notRob = Math.max(preNotRob, preRob);
        }
        return Math.max(rob, notRob);
    }
}
// @lc code=end
