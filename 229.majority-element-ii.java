/*
 * @lc app=leetcode id=229 lang=java
 *
 * [229] Majority Element II
 */
class Solution {
    //https://www.cnblogs.com/grandyang/p/4606822.html
    public List<Integer> majorityElement(int[] nums) {
        //至多有两个数出现次数>n/3;
        int num1 = 0, num2 = 0, cnt1 = 0, cnt2 = 0;
        List<Integer> res = new ArrayList<>();
        int n = nums.length;
        for (int num : nums) {
            if (num == num1)
                ++cnt1;
            else if (num == num2)
                ++cnt2;
            else if (cnt1 == 0) {
                num1 = num;
                cnt1 = 1;
            } else if (cnt2 == 0) {
                num2 = num;
                cnt2 = 1;
            } else {
                --cnt1;
                --cnt2;
            }
        }

        cnt1 = cnt2 = 0;
        for (int num : nums) {
            if (num == num1)
                ++cnt1;
            else if (num == num2)
                ++cnt2;
        }
        
        if(cnt1 > n / 3)
            res.add(num1);
        if(cnt2 > n/3)
            res.add(num2);
        return res;
    }
}
