/*
 * @lc app=leetcode id=292 lang=java
 *
 * [292] Nim Game
 */
class Solution {
    //https://www.cnblogs.com/grandyang/p/4873248.html
    public boolean canWinNim(int n) {
        return n % 4 != 0;
    }
}
