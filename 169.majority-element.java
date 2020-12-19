/*
 * @lc app=leetcode id=169 lang=java
 *
 * [169] Majority Element
 */
class Solution {
    // public int majorityElement(int[] nums) {
    //     int res = 0, cnt = 0;
    //     for (int num : nums) {
    //         if (cnt == 0) {
    //             res = num;
    //             cnt = 1;
    //         } else {
    //             if (num == res)
    //                 ++cnt;
    //             else
    //                 --cnt;
    //         }
    //     }
    //     return res;
    // }


    public int majorityElement(int[] nums) {
        int res = 0, n = nums.length;
        for (int i = 0; i < 32; ++i) {
            int ones = 0, zeros = 0;
            //在每一位上对每个数进行遍历
            for (int num : nums) {
                //该位上是1,ones加1
                if ((num & (1 << i)) != 0)
                    ++ones;
                else
                    //否则zeros加1
                    ++zeros;
                //如果ones或者zeros出现了超过一半
                //说明某个数超过一半
                if (ones > n / 2 || zeros > n / 2)
                    break;
            }
            //如果ones>zeros，说明该位上这个超过一半的数是1
            //按位同或上1
            //0则不需要
            if (ones > zeros)
                res |= (1 << i);
        }
        return res;
    }
}

