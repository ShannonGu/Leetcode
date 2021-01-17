/*
 * @lc app=leetcode id=1497 lang=java
 *
 * [1497] Check If Array Pairs Are Divisible by k
 */

// @lc code=start
class Solution {
    // https://leetcode.com/problems/check-if-array-pairs-are-divisible-by-k/discuss/709691/Java-7ms-Simple-Solution
    // 如果a%k == x, b%k == k - x, 那么(a+b)可以被k整除
    // 证明：a%k = x ===> a = n*k+x
    // b%k = k - x ===> b = m*k+k-x
    // a+b = (m+n)*k+k=(m+n+1)*k ===> (a+b)*k = 0
    public boolean canArrange(int[] arr, int k) {
        // freq[i]表示数组arr中的数对于k的余数是i的个数
        int[] freq = new int[k];
        for (int num : arr) {
            // 先求余
            num %= k;
            // 若为负，转为正
            if (num < 0)
                num += k;
            // 对应余数++
            freq[num]++;
        }

        // freq[0]表示arr中所有能被k整除的个数，要保证为偶数个
        // 否则不能成对组合
        if (freq[0] % 2 != 0)
            return false;
        // 同理其他互补余数也应成对出现
        for (int i = 1; i <= k / 2; i++) {
            if (freq[i] != freq[k - i])
                return false;
        }
        return true;
    }
}
// @lc code=end
