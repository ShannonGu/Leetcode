import java.util.Arrays;

/*
 * @lc app=leetcode id=670 lang=java
 *
 * [670] Maximum Swap
 */
class Solution {
    public int maximumSwap(int num) {
        //核心思路是尽量将高位中的小数与低位中的大数进行交换
        char[] res = String.valueOf(num).toCharArray();
        //back[i]表示第i位及其后面的最大值
        char[] back = Arrays.copyOf(res, res.length);

        //记录每一位后面的最大值
        for (int i = res.length - 2; i >= 0; --i) {
            back[i] = back[i] > back[i + 1] ? back[i] : back[i + 1];
        }


        for (int i = 0; i < res.length; ++i) {
            //说明此时res[i]就是其后面的最大值
            if (res[i] == back[i])
                continue;
            //若一直不进入此循环，表示原数的每一位都是非递增的，不需要进行交换，就是最大值
            for (int j = res.length - 1; j > i; --j) {
                //从i位后面挑选出最大值进行交换
                if (res[j] == back[i]) {
                    char c = res[j];
                    res[j] = res[i];
                    res[i] = c;
                    return Integer.valueOf(String.valueOf(res));
                }
            }
        }
        return Integer.valueOf(String.valueOf(res));
    }
}
