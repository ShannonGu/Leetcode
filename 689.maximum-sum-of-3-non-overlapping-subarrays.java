import java.util.Arrays;
import java.util.List;

/*
 * @lc app=leetcode id=689 lang=java
 *
 * [689] Maximum Sum of 3 Non-Overlapping Subarrays
 */
class Solution {
    public int[] maxSumOfThreeSubarrays(int[] nums, int k) {
        //mx表示全局最大值
        int n = nums.length, mx = Integer.MIN_VALUE;

        // left[i]表示[0,i]范围内长度为k且和为最大的子数组的起始位置,显然，前k个元素肯定是0;
        // right[i]表示[i,n-1]范围内长度为k且和为最大的子数组的起始位置,显然,后k个元素肯定是n-k;
        int[] sums = new int[n + 1], left = new int[n], right = new int[n];
        for (int i = 0; i < n; ++i)
            sums[i + 1] = sums[i] + nums[i];
        Arrays.fill(right, n - k);
        int[] res = {};

        //total初始为sums[1,k] = sums[k] - sums[0];
        //要注意这里i从k开始，不是从k+1开始
        //否则下面的更新比较的是sums[i] - sums[i - k] > total
        //这样就不能遍历到sums[n] - sums[n - k]了
        for (int i = k, total = sums[k] - sums[0]; i < n; ++i) {
            //如果当前窗口的和大于左边的窗口的和
            //更新起始位置,要注意lexicograpically order
            if (sums[i + 1] - sums[i - k + 1] > total) {
                //更新左边窗口的最大和
                total = sums[i + 1] - sums[i - k + 1];
                left[i] = i - k + 1;
            } else {
                //否则与之前窗口的起始位置
                left[i] = left[i - 1];
            }
        }

        //total初始为sums[n - (k-1), n] = sums[n - k + 1, n]= sums[n] - sums[n - k];
        for (int i = n - k - 1, total = sums[n] - sums[n - k]; i >= 0; --i) {
            //更新起始位置时，注意lexicographically order
            if (sums[i + k] - sums[i] >= total) {
                total = sums[i + k] - sums[i];
                right[i] = i;
            } else {
                right[i] = right[i + 1];
            }
        }

        //选择中间窗口的起始位置
        for (int i = k; i <= n - 2 * k; ++i) {
            //l表示左边窗口的起始位置,r表示右边窗口的起始位置
            int l = left[i - 1], r = right[i + k];
            //求三个窗口的和
            int tmp = (sums[l + k] - sums[l]) + (sums[i + k] - sums[i]) + (sums[r + k] - sums[r]);
            //更新最大值
            if (tmp > mx) {
                mx = tmp;
                res = new int[] { l, i, r };
            }
        }
        return res;
    }

}
