import java.util.HashSet;

/*
 * @lc app=leetcode id=97 lang=java
 *
 * [97] Interleaving String
 */
class Solution {
    // https://www.cnblogs.com/grandyang/p/4298664.html
    // public boolean isInterleave(String s1, String s2, String s3) {
    //     int l1 = s1.length(), l2 = s2.length(), l3 = s3.length();
    //     if (l1 + l2 != l3)
    //         return false;
    //     // dp[i][j]表示s1[0,i)和s2[0,j)是否匹配s3[0,i+j);
    //     boolean dp[][] = new boolean[l1 + 1][l2 + 1];
    //     dp[0][0] = true;

    //     // 初始化
    //     // 当s2取0个字符时,如果dp[i-1][0]为true,再对比s1[i-1]和s3[i-1]是否相同;
    //     for (int i = 1; i <= l1; ++i) {
    //         dp[i][0] = dp[i - 1][0] && (s1.charAt(i - 1) == s3.charAt(i - 1));
    //     }
    //     // 同理初始化dp[0][i];
    //     for (int i = 1; i <= l2; ++i) {
    //         dp[0][i] = dp[0][i - 1] && (s2.charAt(i - 1) == s3.charAt(i - 1));
    //     }

    //     for (int i = 1; i <= l1; ++i) {
    //         // int s1Idx = i - 1;
    //         for (int j = 1; j <= l2; ++j) {
    //             // int s2Idx = j - 1;
    //             // 考虑dp[i-1][j]和dp[i][j-1]为true的两种情况;
    //             // 若dp[i-1][j]为true,说明s1[0,i-1)和s2[0,j)可以匹配s3[0, i-1+j)
    //             // 此时比较s1[i-1]和s3[i-1+j]是否相同，相同则s1[0,i)和s2[0,j)可以匹配s3[0,i+j);
    //             // 若dp[i][j-1]为true,同理

    //             // int s3Idx = s1Idx + s2Idx + 1;
    //             // if(s1.charAt(s1Idx) == s3.charAt(s3Idx) && s2.charAt(s2Idx) == s3.charAt(s3Idx))
    //             //     dp[i][j] = dp[i - 1][j] || dp[i][j - 1];
    //             // else if(s1.charAt(s1Idx) == s3.charAt(s3Idx))
    //             //     dp[i][j] = dp[i - 1][j];
    //             // else if(s2.charAt(s2Idx) == s3.charAt(s3Idx))
    //             //     dp[i][j] = dp[i][j - 1];
    
    //             dp[i][j] = (dp[i - 1][j] && (s1.charAt(i - 1) == s3.charAt(i - 1 + j)))
    //                     || (dp[i][j - 1] && (s2.charAt(j - 1) == s3.charAt(j - 1 + i)));
    //         }
    //     }
    //     return dp[l1][l2];
    // }



    private int[][] mem;
    public boolean isInterleave(String s1, String s2, String s3) {
        if(s1.length() + s2.length() != s3.length())
            return false;
        int l1 = s1.length(), l2 = s2.length();
        mem = new int[l1][l2];
        for(int[] m : mem)
            Arrays.fill(m, -1);
        return helper(s1, 0, s2, 0, s3, 0);
    }
    
    public boolean helper(String s1, int i, String s2, int j, String s3, int k) {
        if(i == s1.length())
            return s2.substring(j).equals(s3.substring(k));
        if(j == s2.length())
            return s1.substring(i).equals(s3.substring(k));
        if(mem[i][j] >= 0)
            return mem[i][j] == 1 ? true : false;
        
        boolean ans = false;
        if((s1.charAt(i) == s3.charAt(k) && helper(s1, i + 1, s2, j, s3, k + 1))
            || (s2.charAt(j) == s3.charAt(k) && helper(s1, i, s2, j + 1, s3, k + 1)))
            ans = true;
        mem[i][j] = ans ? 1 : 0;
        return ans;
    }
}
