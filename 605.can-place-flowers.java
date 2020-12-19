/*
 * @lc app=leetcode id=605 lang=java
 *
 * [605] Can Place Flowers
 */
class Solution {
    public boolean canPlaceFlowers(int[] flowerbed, int n) {
        //每遇到三个连续的0才能放置一个花
        int len = flowerbed.length;
        int[] newBed = new int[len + 2];
        for(int i = 0; i < len; ++i)
            newBed[i + 1] = flowerbed[i];
        newBed[0] = newBed[len + 1] = 0;
        int i = 1;
        while (i < len + 1) {
            if (n == 0)
                return true;
            if (newBed[i - 1] + newBed[i] + newBed[i + 1] == 0) {
                --n;
                i += 2;
            } else
                ++i;
        }
        return n <= 0;
    }
}

