import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.ArrayList;
import java.util.Iterator;

/*
 * @lc app=leetcode id=300 lang=java
 *
 * [300] Longest Increasing Subsequence
 */
class Solution {
    // Approach1，两重循环 O(n^2)

    // Approach2
    public int lengthOfLIS(int[] nums) {
        if (nums.length == 0)
            return 0;
        // 定义一个列表a， 其中a[k]表示长度为k+1的最长递增子序列的最后一个数字
        // 遍历每个位置i，如果其对应的数字大于a中所有的数字，则将其添加到列表的尾部
        // 表示最长递增子序列长度加1
        // 如果该数字在列表中比数字num1大，比数字num2小，则将num2更新为此数字，
        // 使得构成递增序列的可能性增大
        // 通过这种方式维护的列表a永远是递增的，因此可以用二分查找加速搜索。
        List<Integer> a = new ArrayList<>();
        for (int i = 0; i < nums.length; ++i) {
            if (a.size() == 0 || nums[i] > a.get(a.size() - 1))
                a.add(nums[i]);
            else {
                int len = a.size();
                int l = 0, r = len - 1;
                while (l < r) {
                    int mid = l + r >> 1;
                    if (a.get(mid) < nums[i])
                        l = mid + 1;
                    else
                        r = mid;
                }
                a.set(l, nums[i]);
            }
        }
        return a.size();
    }

    // 利用BIT实现
    // https://www.geeksforgeeks.org/longest-increasing-subsequence-using-bit/
    private int lowBit(int x) {
        return x & (-x);
    }

    // 首先对原数组进行离散化
    private void compression(int[] nums, int n) {
        // 利用TreeSet对原数组进行去重和排序
        Set<Integer> s = new TreeSet<>();
        for (int num : nums) {
            s.add(num);
        }

        // 利用HashMap存储数组中每个数的相对大小
        Map<Integer, Integer> m = new HashMap<>();
        int index = 0;
        for (int num : s) {
            index++;
            m.put(num, index);
        }

        // 在原数组上存储该数在数组中的相对大小
        for (int i = 0; i < n; ++i) {
            nums[i] = m.get(nums[i]);
        }
    }

    // 因为经过离散化处理后,nums中的每个位置存放的已经是该位置上原数的相对次序
    // 所以当从左到右遍历每个数时,就可以得到该数的相对次序,
    // 此时通过BIT可知以该数为结尾的LIS的长度,对BIT进行更新
    // BIT的每个下标表示的是原数组中的数的相对次序
    // BIT每个位置上的数表示为该次序的数为结尾的LIS的长度
    // query得到的是以当前已经出现的前index个为结尾的数的LIS的最长长度
    private int query(int[] BIT, int index, int n) {
        int ans = 0;
        while (index > 0) {
            ans = Math.max(ans, BIT[index]);
            index -= lowBit(index);
        }
        return ans;
    }

    private void update(int[] BIT, int index, int n) {
        // 先得到以比当前数小的数为结尾的LIS中最长的长度
        // 因为遍历时是从左到右的,所以当前遍历的数,
        // 肯定可以加在前面已经得到的以比当前数小的数为结尾的LIS的后面
        int x = query(BIT, index - 1, n);
        // 故更新LIS的长度
        int value = x + 1;
        // 更新BIT
        while (index <= n) {
            // 与之前得到的LIS的长度比较,进行更新
            BIT[index] = Math.max(BIT[index], value);
            index += lowBit(index);
        }
    }

    public int lengthOfLIS(int[] nums) {
        int n = nums.length;
        // 离散化
        // 此时nums中每个位置存储的是该位置上原来的数在所有数中的相对大小
        compression(nums, n);

        // 初始化BIT
        int[] BIT = new int[n + 1];
        Arrays.fill(BIT, 0);

        // 从左到右依次更新
        for (int i = 0; i < n; ++i) {
            update(BIT, nums[i], n);
        }

        int res = query(BIT, n, n);
        return res;
    }
}
