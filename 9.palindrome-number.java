/*
 * @lc app=leetcode id=9 lang=java
 *
 * [9] Palindrome Number
 */
class Solution {
    //https://www.cnblogs.com/grandyang/p/4125510.html
    public boolean isPalindrome(int x) {
        if(x < 0 || (x % 10 == 0 && x != 0))
            return false;
        if(x < 10)
            return true;
        int num = 0;
        while (num < x) {
            num = num * 10 + x % 10;
            x /= 10;
        }
        return num == x || num / 10 == x;
    }
}

