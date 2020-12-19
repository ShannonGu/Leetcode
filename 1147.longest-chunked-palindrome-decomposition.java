/*
 * @lc app=leetcode id=1147 lang=java
 *
 * [1147] Longest Chunked Palindrome Decomposition
 */

// @lc code=start
class Solution {
    //https://leetcode.com/problems/longest-chunked-palindrome-decomposition/discuss/350560/JavaC%2B%2BPython-Easy-Greedy-with-Prove
    // public int longestDecomposition(String text) {
    //     int res = 0, n = text.length();
    //     StringBuilder l = new StringBuilder("");
    //     String r = "";
    //     for (int i = 0; i < n; ++i) {
    //         l.append(text.charAt(i));
    //         r = text.charAt(n - 1 - i) + r;
    //         if (l.toString().equals(r)) {
    //             ++res;
    //             l = new StringBuilder("");
    //             r = "";
    //         }
    //     }
    //     return res;
    // }

    //递归解法
    //https://leetcode.com/problems/longest-chunked-palindrome-decomposition/discuss/350762/Java-0ms-concise-beats-100-(both-time-and-memory)-with-algo
    // public int longestDecomposition(String text) {
    //     int n = text.length();
    //     for (int i = 0; i < n / 2; ++i) {
    //         if (text.substring(0, i + 1).equals(text.substring(n - 1 - i, n)))
    //             return 2 + longestDecomposition(text.substring(i + 1, n - 1 - i));
    //     }
    //     return (n == 0) ? 0 : 1;
    // }


    //Rolling hash
    //https://leetcode.com/problems/longest-chunked-palindrome-decomposition/discuss/355702/beat-100-!!-Java-Rolling-Hash
    public int longestDecomposition(String text) {
        int leftHash = 0;
        int rightHash = 0;
        int n = text.length();
        int cur = 1, res = 0;
        int l = 0, r = n - 1;

        while (l < r) {
            leftHash = 26 * leftHash + (text.charAt(l) - 'a');
            rightHash = rightHash + (text.charAt(r) - 'a') * cur;
            cur *= 26;

            if (leftHash == rightHash) {
                leftHash = 0;
                rightHash = 0;
                res += 2;
                cur = 1;
            }
            ++l;
            --r;
        }

        //偶数
        //将左右两边的剩下的字符串合并成一个
        if (l > r && leftHash != rightHash)
            res += 1;

        //奇数
        if(l == r)
            res += 1;

        return res;

    }
    
}
// @lc code=end

