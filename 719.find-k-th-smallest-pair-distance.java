import java.util.Arrays;

/*
 * @lc app=leetcode id=719 lang=java
 *
 * [719] Find K-th Smallest Pair Distance
 */
class Solution {
    public int smallestDistancePair(int[] nums, int k) {
        //先对数组进行排序
        Arrays.sort(nums);
        int n = nums.length;
        //l代表最小距离，r表示数组中的最大距离
        int l = 0, r = nums[n - 1] - nums[0];
        while (l < r) {
            //cnt表示计算数组中距离<=距离中间数的个数
            int cnt = 0, st = 0;
            int mid = l + r >> 1;
            //遍历数组
            for (int i = 0; i < n; ++i) {
                //计算<=距离中间数的个数
                while (st < n && nums[i] - nums[st] > mid)
                    ++st;
                //[st,i]之间都是距离<=mid的
                cnt += i - st;
            }

            //若cnt>=k，表示距离中间数mid太大了,且mid可能是结果
            if (cnt >= k)
                r = mid;
            else
                l = mid + 1;
        }
        return l;
    }
}
