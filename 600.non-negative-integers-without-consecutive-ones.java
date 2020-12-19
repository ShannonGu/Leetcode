/*
 * @lc app=leetcode id=600 lang=java
 *
 * [600] Non-negative Integers without Consecutive Ones
 */
class Solution {
    // https://www.cnblogs.com/grandyang/p/6959585.html
    // public int findIntegers(int num) {
    //     // 对于一个k位二进制数如k=5,其取值范围为00000-11111
    //     // 则不妨将其中满足要求的二进制数的个数定义为f(5);
    //     // 拆分发现，可以将这个范围分成两部分00000-01111, 和10000-10111;
    //     // 因为任何>=11000的二进制数都是不成立的，因为要避免连续的位是1
    //     // 而00000-01111中的情况即f(4), 10000-10111即f(3);
    //     // 故f(5) = f(4) + f(3), 对于f(4)和f(3)又可以继续拆分，即斐波那契数列
    //     // 于是需要预处理一个斐波那契数列;
    //     // 从num的二进制数最高位开始遍历, 如遇到某一位为1,其后有k位，则加上f(k)
    //     // 因为若将该位改为0,后面有f(k)情况,然后使用标记pre记录该位为1;
    //     // 若遇到该位为0，不做任何处理，因为要确保比num小, 但是将pre记录为0,
    //     // 若当前位1，标记pre(即前一位)也为1, 则直接返回结果，因为有了两位连续的1,后面的情况已经满足要求了
    //     int res = 0, k = 31, pre = 0;
    //     // f[i]表示i位二进制数满足要求的数的个数
    //     int[] f = new int[32];
    //     f[0] = 1;
    //     f[1] = 2; // 即0, 1;
    //     for (int i = 2; i < k; ++i) {
    //         f[i] = f[i - 1] + f[i - 2];
    //     }
    //     // 从高位向低位遍历， 找到第一个二进制位为1时开始处理
    //     while (k >= 0) {
    //         // num第k位为1
    //         if ((num & (1 << k)) == 1) {
    //             // 注意这里这有(k+1)位, 当前位为1，后面有k位
    //             res += f[k];
    //             // 若连续两位为1
    //             if (pre == 1)
    //                 return res;
    //             // 标记当前位为1，
    //             pre = 1;
    //         } else
    //             // 当前位为0;
    //             pre = 0;
    //         // 继续向低位遍历
    //         --k;
    //     }

    //     // 如果没有在循环中返回结果
    //     // 说明num本身也是满足要求的
    //     return res + 1;
    // }


    public int findIntegers(int num) {
        int cnt = 0, n = num;
        StringBuilder t = new StringBuilder("");

        //将num转为二进制数, cnt用于记录二进制数的长度
        while (n > 0) {
            ++cnt;
            t.append((n & 1) == 1 ? '1' : '0');
            n >>= 1;
        }
        
        //zeros[i]表示i位二进制数最低位为0的满足要求的数的个数
        //ones[i]表示i为二进制数最低位为1的满足要求的数的个数
        int[] zeros = new int[cnt], ones = new int[cnt];
        zeros[0] = 1;
        ones[0] = 1;
        for (int i = 1; i < cnt; ++i) {
            zeros[i] = zeros[i - 1] + ones[i - 1];
            //不能连续两位都为1;
            ones[i] = zeros[i - 1];
        }
        
        int res = zeros[cnt - 1] + ones[cnt - 1];
        for (int i = cnt - 2; i >= 0; --i) {
            //如果连续两位都为1, 直接break
            //因为这种情况不符合要求， 肯定没有被计算过
            if (t.charAt(i) == '1' && t.charAt(i + 1) == '1')
                break;
            //如果连续两位都为'0',需要减去被多算的情况
            //因为在之前的计算中, 会加上t[i+1]为'1'且满足要求的情况
            //而这样的情况得到的数是大于num的
            if (t.charAt(i) == '0' && t.charAt(i + 1) == '0')
                res -= ones[i];
        }
        return res;
    }
}
