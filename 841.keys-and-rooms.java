import java.util.Queue;

/*
 * @lc app=leetcode id=841 lang=java
 *
 * [841] Keys and Rooms
 */
class Solution {
    public boolean canVisitAllRooms(List<List<Integer>> rooms) {
        if(rooms.size() == 0)
            return true;
        int cnt = 0, len = rooms.size();
        Queue<Integer> q = new LinkedList<>();
        q.offer(0);
        //判断是否遍历过当前房间
        boolean[] visited = new boolean[len];
        visited[0] = true;
        while (!q.isEmpty()) {
            int tmp = q.poll();
            int n = rooms.get(tmp).size();
            ++cnt;
            for (int num : rooms.get(tmp)) {
                if (visited[num])
                    continue;
                visited[num] = true;
                q.offer(num);
            }
        }
        return cnt == len;
    }
}

