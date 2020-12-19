import java.util.Iterator;
import java.util.Set;

/*
 * @lc app=leetcode id=327 lang=java
 *
 * [327] Count of Range Sum
 */

//https://www.hrwhisper.me/leetcode-count-of-range-sum/
class Solution {

    class FenwickTree {
        int[] sums;

        public FenwickTree(int x) {
            sums = new int[x + 1];
        }

        private int lowBit(int x) {
            return x & (-x);
        }

        public void add(int i, int delta) {
            while (i < sums.length) {
                sums[i] += delta;
                i += lowBit(i);
            }
        }

        public int query(int i) {
            int sum = 0;
            while (i > 0) {
                sum += sums[i];
                i -= lowBit(i);
            }
            return sum;
        }
    }

    // 目的是求nums数组中满足条件的区间的个数;
    // 即求lower <= sums[i, j] <= upper, 满足条件的sums[i, j]的个数
    // sums[i, j] = sums[j] - sums[i - 1];
    // 即求lower <= sums[j] - sums[i - 1] <= upper
    // 也即lower + sums[i - 1] <= sums[j] <= upper + sums[i - 1];
    public int countRangeSum(int[] nums, int lower, int upper) {
        if (nums.length == 0)
            return 0;
        int n = nums.length;
        long[] sums_ = new long[n * 3 + 2];
        long sum = 0;

        // 求出某段区间的和，首先求累加和
        for (int i = 0; i < n; ++i) {
            sum += nums[i];
            sums_[i * 3] = sum;
            sums_[i * 3 + 1] = sum + lower - 1;
            // sums[i * 3 + 1] = sum + lower;
            sums_[i * 3 + 2] = sum + upper;
        }

        // sums[n * 3] = lower;
        sums_[n * 3] = lower - 1;
        sums_[n * 3 + 1] = upper;

        // 将sums_中的累加和存入Set中去重
        // 利用TreeSet的性质自动排序;
        Set<Long> s = new TreeSet<>();
        for (long sum_ : sums_) {
            s.add(sum_);
        }

        // 离散化，将Set中的数与它们在数组的相对大小进行映射
        Map<Long, Integer> ranks = new HashMap<>();
        Iterator it = s.iterator();
        int rank = 0;
        while (it.hasNext()) {
            ranks.put((Long) it.next(), ++rank);
        }

        // 使用ranks的大小参数初始化一个FenwickTree
        // 模拟出一个freq数组, 大小为ranks.size() + 1, 表示对应的数出现的次数;
        // FenwickTree中实际创建了一个sums数组,大小和Freq相同
        // sums[i]表示求Freq[0, i]的元素之和,注意freq和sums都是从下标1开始存放;
        FenwickTree T = new FenwickTree(ranks.size());
        int ans = 0;
        for (int j = nums.length - 1; j >= 0; --j) {
            // 首先插入一个sum, 会在当前sum对应的freq的位置上加1
            // 表示[0, j]的总和的情况
            T.add(ranks.get(sum), 1);

            // 用_sums[i]表示为nums[0, i]的累加和
            // 则_sums[i, j] = _sums[j] - _sums[i - 1];求某个区间的和;
            // 此时sum = _sums[i-1];
            sum -= nums[j];

            // 因为这里要求的是_sums[i, j]是否符合题目条件
            // 转化为求lower + _sums[i - 1] <= _sums[j] <= upper + _sums[i - 1];
            // 随着循环中j递减,先进行sum-=nums[j]操作,此时sum相当于上式中的_sums[i-1],
            // 而下式T.query(ranks.get(upper + sum)) - T.query(ranks.get(lower - 1 + sum))
            // 则是求的所有以_sums[i, j]中以i为起始的满足条件的区间的个数;
            // 比如有nums[0, 5]6个数, 当执行sum -= nums[3]操作时, 此时sum == _sums[2];
            // 此时lower + _sums[2] <= _sums[j] <= upper + _sums[2];
            // 通过FenwickTree, 就可以求出[3, 5], [3, 4], [3, 3]区间中所有满足条件的个数;

            // ans += T.query(ranks.get(upper + sum)) - T.query(ranks.get(lower + sum) - 1);
            ans += T.query(ranks.get(upper + sum)) - T.query(ranks.get(lower - 1 + sum));
        }
        return ans;
    }
}
