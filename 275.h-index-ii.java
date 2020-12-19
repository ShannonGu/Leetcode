/*
 * @lc app=leetcode id=275 lang=java
 *
 * [275] H-Index II
 */
class Solution {
    //找一个最大的数h，使得后h个数均>=h;
    //若h满足，则小于h的数都满足
    //若h不满足，则大于h的数都不满足
    public int hIndex(int[] citations) {
        if(citations.length == 0)
            return 0;
        
        int n = citations.length;
        //最大index为0
        //H-index肯定==0
        if(citations[n - 1] == 0)
            return 0;

        int l = 0, r = n - 1;
        
        while (l < r) {
            int mid = l + r >> 1;
            //找到第一个citations[mid] == citations.length - mid;
            //如果没有找到,第一个citations[mid] > citations.length - mid;
            if (citations[mid] >= n - mid)
                r = mid;
            else
                l = mid + 1;
        }
        
        return citations.length - l;
    }
}

