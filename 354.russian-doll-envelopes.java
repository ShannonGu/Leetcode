import java.util.Arrays;
import java.util.List;

/*
 * @lc app=leetcode id=354 lang=java
 *
 * [354] Russian Doll Envelopes
 */
class Solution {
    //https://www.cnblogs.com/grandyang/p/5568818.html
    //Approach 1与求longest increasing subsequence approach 1相同

    //Approach2
    // public int maxEnvelopes(int[][] envelopes) {
    //     //将envelopes排序，宽度从小到大排序
    //     //宽度相同的，高度从大到小
    //     Arrays.sort(envelopes, (w1, w2) -> {
    //         if(w1[0] != w2[0])
    //             return Integer.compare(w1[0], w2[0]);
    //         return Integer.compare(w2[1], w1[1]);
    //     });

    //     //与LIS approach 2相同
    //     //目的在于保留具有在List中,
    //     //因为该方法总是在List寻找在宽度相同的情况下，>=当前高度的第一个值，然后替代它,
    //     //而宽度相同时，又是递减排序的，所以List中保留了相同宽度的envelopes的最小高度
    //     //这样当有一个envelop的高度大于List中最大高度时，说明这个envelope也具有新的更大的宽度
    //     List<Integer> res = new ArrayList<>();
    //     for (int i = 0; i < envelopes.length; ++i) {
    //         int tmp = envelopes[i][1];
    //         if (res.isEmpty() || tmp > res.get(res.size() - 1))
    //             res.add(tmp);
    //         else {
    //             int l = 0, r = res.size() - 1;
    //             while (l < r) {
    //                 int mid = l + r >> 1;
    //                 if (res.get(mid) >= tmp)
    //                     r = mid;
    //                 else
    //                     l = mid + 1;
    //             }
    //             res.set(l, tmp);
    //         }
    //     }
    //     return res.size();
    // }



    private int lowBit(int x) {
        return x & (-x);
    }

    private void compression(int[][] nums, int n) {
        Set<Integer> s = new TreeSet<>();
        for (int[] num : nums) {
            s.add(num[1]);
        }

        Map<Integer, Integer> m = new HashMap<>();
        int index = 0;
        for (int num : s) {
            ++index;
            m.put(num, index);
        }
        for (int[] num : nums) {
            num[1] = m.get(num[1]);
        }
    }

    private int query(int[] BIT, int index, int n) {
        int ans = 0;
        while (index > 0) {
            ans = Math.max(ans, BIT[index]);
            index -= lowBit(index);
        }
        return ans;
    }

    private void update(int[] BIT, int index, int n) {
        int x = query(BIT, index - 1, n);
        int value = x + 1;
        while (index <= n) {
            BIT[index] = Math.max(BIT[index], value);
            index += lowBit(index);
        }
    }

    public int maxEnvelopes(int[][] envelopes) {
        Arrays.sort(envelopes, (w1, w2) -> {
            if(w1[0] != w2[0])
                return w1[0] - w2[0];
            else
                return w2[1] - w1[1];
        });

        int n = envelopes.length;
        compression(envelopes, n);
        int[] BIT = new int[n + 1];
        Arrays.fill(BIT, 0);
        for (int[] envelope: envelopes) {
            update(BIT, envelope[1], n);
        }

        int res = query(BIT, n, n);
        return res;
    }
}

