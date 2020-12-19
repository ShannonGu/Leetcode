/*
 * @lc app=leetcode id=834 lang=java
 *
 * [834] Sum of Distances in Tree
 */

// @lc code=start
class Solution {
    private List<Integer>[] lists;
    private int res;

    private void dfs(int last, int u, int cnt){
        for(int e : lists[u]){
            if(e == last)
                continue;
            res += cnt;
            dfs(u, e, cnt + 1);
        }
    }

    public int[] sumOfDistancesInTree(int N, int[][] edges) {
        lists = new ArrayList[N];
        Arrays.fill(lists, new ArrayList<>());
        for(int[] edge : edges){
            int a = edge[0], b = edge[1];
            lists[a].add(b);
            lists[b].add(a);
        }
        int[] ans = new int[N];
        for(int i = 0; i < N; i++){
            res = 0;
            dfs(-1, i, 1);
            ans[i] = res;
        }
        return ans;

    }
    
}
// @lc code=end

