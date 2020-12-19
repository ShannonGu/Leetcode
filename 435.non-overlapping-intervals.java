import java.util.Comparator;

/*
 * @lc app=leetcode id=435 lang=java
 *
 * [435] Non-overlapping Intervals
 */
class Solution {
    //https://www.cnblogs.com/grandyang/p/6017505.html
    public int eraseOverlapIntervals(int[][] intervals) {
        int res = 0, len = intervals.length, last = 0;

        Arrays.sort(intervals, new Comparator<int[]>() {

            public int compare(int[] x, int[] y) {
                if(x[0] == y[0])
                    return x[1] - y[1];

                return x[0] - y[0];
            }
        });
        // Arrays.sort(intervals, (x, y) -> x[0] == y[0] ? x[1] - y[1] : x[0] - y[0]);
        for (int i = 1; i < len; ++i) {
            if (intervals[i][0] < intervals[last][1]) {
                ++res;
                if (intervals[i][1] < intervals[last][1])
                    last = i;
            } else {
                last = i;
            }
        }
        return res;
    }
}

