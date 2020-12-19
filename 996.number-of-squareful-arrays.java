import java.util.List;

/*
 * @lc app=leetcode id=996 lang=java
 *
 * [996] Number of Squareful Arrays
 */
class Solution {
    //https://zxi.mytechroad.com/blog/searching/leetcode-996-number-of-squareful-arrays/
    private int res = 0;

    public int numSquarefulPerms(int[] A) {
        //先从小到大排序
        Arrays.sort(A);
        List<Integer> cur = new ArrayList<>();
        boolean[] used = new boolean[A.length];
        dfs(A, cur, used);
        return res;
    }
    

    private boolean squareful(int x, int y) {
        int s = (int)Math.sqrt(x + y);
        return s * s == x + y;
    }

    private void dfs(int[] A, List<Integer> cur, boolean[] used) {
        if (cur.size() == A.length) {
            ++res;
            return;
        }

        for (int i = 0; i < A.length; ++i) {
            //已经使用过这个数了
            if(used[i])
                continue;
            //避免重复, [1,8,8,8]
            if(i > 0 && !used[i - 1] && A[i] == A[i - 1])
                continue;
            //不满足相邻两个数的和是平方数
            if(!cur.isEmpty() && !squareful(cur.get(cur.size() - 1), A[i]))
                continue;
            cur.add(A[i]);
            used[i] = true;
            dfs(A, cur, used);
            used[i] = false;
            cur.remove(cur.size() - 1);
        }
    }
}

