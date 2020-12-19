/*
 * @lc app=leetcode id=131 lang=java
 *
 * [131] Palindrome Partitioning
 */
class Solution {

    // List<List<String>> res = new ArrayList<>();
    // List<String> cur = new ArrayList<>();

    // public List<List<String>> partition(String s) {
    //     helper(s, 0);
    //     return res;
    // }

    // public void helper(String s, int start) {
    //     if (start == s.length()) {
    //         //注意这里要新创建一个对象
    //         res.add(new ArrayList<>(cugr));
    //         return;
    //     }

    //     for (int i = start; i < s.length(); ++i) {
    //         String tmp = s.substring(start, i + 1);
    //         if (isPalindrome(tmp)) {
    //             cur.add(tmp);
    //             helper(s, i + 1);
    //             cur.remove(cur.size() - 1);
    //         }
    //     }
    // }

    // private boolean isPalindrome(String str) {
    //     int i = 0, j = str.length() - 1;
    //     while (i < j) {
    //         if (str.charAt(i++) != str.charAt(j--)) {
    //             return false;
    //         }
    //     }
    //     return true;
    // }




    boolean[][] dp;
    List<List<String>> res;
    List<String> cur;

    //先用dp标记出哪一段是否为回文串，然后在递归求解
    public List<List<String>> partition(String s) {
        if (s.length() == 0)
            return new ArrayList<>();

        int len = s.length();
        res = new ArrayList<>();
        cur = new ArrayList<>();
        dp = new boolean[len][len];
        // for (int i = 0; i < len; ++i) {
        //     //j == i;
        //     dp[i][i] = true;
        //     for (int j = 0; j < i; ++j) {
        //         if (i == j + 1)
        //             dp[j][i] = s.charAt(j) == s.charAt(i);
        //         else if (i > j + 1)
        //             dp[j][i] = (s.charAt(j) == s.charAt(i) && dp[j + 1][i - 1]);
        //     }
        // }
        for(int l = 1; l <= len; ++l){
            for(int i = 0; i <= len - l; ++i){
                int j = i + l - 1;
                if(i == j){
                    dp[i][j] = true;
                    continue;
                }
                if(j == i + 1)
                    dp[i][j] = s.charAt(i) == s.charAt(j);
                else if(j > i + 1)
                    dp[i][j] = s.charAt(i) == s.charAt(j) && dp[i+1][j-1];
            }
        }
        helper(s, 0);
        return res;
    }
    

    public void helper(String s, int start) {
        if (start == s.length()) {
            res.add(new ArrayList<>(cur));
            return;
        }

        for (int i = start; i < s.length(); ++i) {
            if(!dp[start][i])
                continue;
            cur.add(s.substring(start, i + 1));
            helper(s, i + 1);
            cur.remove(cur.size() - 1);
        }
    }
}
