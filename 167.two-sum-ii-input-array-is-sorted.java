/*
 * @lc app=leetcode id=167 lang=java
 *
 * [167] Two Sum II - Input array is sorted
 */
class Solution {
    public int[] twoSum(int[] numbers, int target) {
        if (numbers.length == 0)
            return new int[] {};
        int[] res = new int[2];
        int n = numbers.length;
        int i = 0, j = n - 1;
        while (i < j) {
            if (numbers[i] + numbers[j] == target) {
                res[0] = i + 1;
                res[1] = j + 1;
                break;
            } else if (numbers[i] + numbers[j] > target)
                --j;
            else
                ++i;
        }
        return res;
    }
}
