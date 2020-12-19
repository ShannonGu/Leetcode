/*
 * @lc app=leetcode id=214 lang=java
 *
 * [214] Shortest Palindrome
 */
class Solution {
    //https://www.cnblogs.com/grandyang/p/4523624.html
    public String shortestPalindrome(String s) {
        if (s.length() < 2)
            return s;
        int n = s.length();
        int i = n - 1;
        for (; i > 0; --i) {
            if (isPal(s.substring(0, i + 1)))
                break;
        }
        i += 1;
        StringBuilder tmp = new StringBuilder(s.substring(i));
        tmp.reverse().append(s);
        return tmp.toString();
    }
    
    private boolean isPal(String str) {
        int i = 0, j = str.length() - 1;
        while (i < j) {
            if (str.charAt(i++) != str.charAt(j--))
                return false;
        }
        return true;
    }
}

