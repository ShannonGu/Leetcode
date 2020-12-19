import java.util.Arrays;

/*
 * @lc app=leetcode id=436 lang=java
 *
 * [436] Find Right Interval
 */
class Solution {
    //先按照起始点排序，然后二分查找
    public int[] findRightInterval(int[][] intervals) {
        if (intervals.length == 0 || intervals[0].length == 0)
            return new int[] {};
        int n = intervals.length;
        int[] res = new int[n];
        int[] newIntervals = new int[n];
        Map<Integer, Integer> m = new HashMap<>();
        for (int i = 0; i < n; ++i) {
            m.put(intervals[i][0], i);
            newIntervals[i] = intervals[i][0];
        }

        Arrays.sort(newIntervals);
        for (int i = 0; i < n; ++i) {
            int l = 0, r = n;
            while (l < r) {
                int mid = l + r >> 1;
                if (newIntervals[mid] >= intervals[i][1])
                    r = mid;
                else
                    l = mid + 1;
            }
            if (l == n)
                res[i] = -1;
            else
                res[i] = m.get(newIntervals[l]);
        }
        return res;
    }
}
