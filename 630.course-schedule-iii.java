import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;

/*
 * @lc app=leetcode id=630 lang=java
 *
 * [630] Course Schedule III
 */
class Solution {
    // https://www.cnblogs.com/grandyang/p/7126289.html
    public int scheduleCourse(int[][] courses) {
        // 使用贪心策略
        // 先按课程结束时间从小到大排序
        // Arrays.sort(courses, (a, b) -> a[1] - b[1]);
        Arrays.sort(courses, (a, b) -> a[1] - b[1]);

        // 再利用大根堆对每个课程的持续时间进行排序
        Queue<Integer> q = new PriorityQueue<>((a, b) -> b - a);
        int curTime = 0;
        for (int[] course : courses) {
            curTime += course[0];
            // 将当前课程持续时间加入队列
            q.offer(course[0]);

            // 如果该课程结束时间大于该课程规定结束时间，说明该课程无法完成
            // 此时去掉用时最长的一门课
            // 因为是尽可能的多上课，若要去掉一门课，应该去掉用时最长的一门课
            // 或许省下的时间能多上几门课
            if (curTime > course[1]) {
                // 当前开始上课时间要前移
                curTime -= q.poll();
            }
        }
        return q.size();
    }
}
