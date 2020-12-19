/*
 * @lc app=leetcode id=231 lang=java
 *
 * [231] Power of Two
 */
class Solution {
    //https://www.cnblogs.com/grandyang/p/4623394.html
    public boolean isPowerOfTwo(int n) {
        if(n <= 0)
            return false;
        
        //若一个数为2的n次方，则其二进制数的第n-1为必定是1,其余位为0;
        //n-1的二进制0~n-2位均为1
        if(((n - 1) & n) == 0)
            return true;
        return false;
    }
}

