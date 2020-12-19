/*
 * @lc app=leetcode id=209 lang=java
 *
 * [209] Minimum Size Subarray Sum
 */
class Solution {
    //https://www.cnblogs.com/grandyang/p/4501934.html
    // public int minSubArrayLen(int s, int[] nums) {
    //     if(nums.length == 0)
    //         return 0;
    //     int l = 0, r = 0;
    //     int sum = 0, res = nums.length + 1;
    //     while (r < nums.length) {
    //         while (r < nums.length && sum < s) {
    //             sum += nums[r++];
    //         }
    //         while (l <= r && sum >= s) {
    //             res = Math.min(res, r - l);
    //             sum -= nums[l++];
    //         }
    //     }

    //     return res == nums.length + 1 ? 0 : res;
    // }

    public int minSubArrayLen(int s, int[] nums) {
        int n = nums.length, res = n + 1;
        int[] sums = new int[n + 1];
        for (int i = 1; i < n + 1; ++i) {
            sums[i] = sums[i - 1] + nums[i - 1];
        }

        for (int i = 0; i < n + 1; ++i) {
            int pos = searchPos(i + 1, n, sums[i] + s, sums);
            if (pos == n) {
                //出现这种情况，后面的和已经不可能了
                //所以直接break;
                if (sums[pos] < sums[i] + s) {
                    break;
                }
            }
            if (res > pos - i)
                res = pos - i;
        }
        return res == n + 1 ? 0 : res;
    }

    private int searchPos(int l, int r, int key, int[] sums) {
        while (l < r) {
            int mid = l + r >> 1;
            if (sums[mid] >= key)
                r = mid;
            else
                l = mid + 1;
        }
        return l;
    }
}

