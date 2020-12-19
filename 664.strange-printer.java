/*
 * @lc app=leetcode id=664 lang=java
 *
 * [664] Strange Printer
 */
class Solution {
    //http://zxi.mytechroad.com/blog/dynamic-programming/leetcode-664-strange-printer/
    public int strangePrinter(String s) {
        int n = s.length();
        //mem_[i][j]表示打印s[i,j]需要的最小turns
        mem_ = new int[n][n];
        for(int[] m : mem_)
            Arrays.fill(m, Integer.MAX_VALUE);
        return turn(s.toCharArray(), 0, n - 1);
    }

    private int turn(char[] s, int i, int j) {
        //空串
        if(i > j)
            return 0;
        if (i == j)
            return 1;
        //已经求解过了
        if(mem_[i][j] != Integer.MAX_VALUE)
            return mem_[i][j];
            
        //初始值, print s[i, j - 1] + print s[j]
        int ans = turn(s, i, j - 1) + 1;
        for (int k = i; k < j; ++k) {
            //在[i,j]中寻找分割点k
            //若s[k]==s[j],需要打印s[i,k],s[k+1,j-1];
            //因为打印[i,k]的同时可以顺便将s[k+1, j]打印成与s[k]相同的字符,这样就满足s[k]==s[j]
            if (s[k] == s[j])
                ans = Math.min(ans, turn(s, i, k) + turn(s, k + 1, j - 1));
        }
        mem_[i][j] = ans;
        return ans;
    }

    private int[][] mem_;
}
