import java.util.List;

/*
 * @lc app=leetcode id=784 lang=java
 *
 * [784] Letter Case Permutation
 */

// @lc code=start
class Solution {
    //https://www.cnblogs.com/grandyang/p/9065702.html
    private List<String> res;
    public List<String> letterCasePermutation(String S) {
        res = new ArrayList<>();
        helper(S.toCharArray(), 0);
        return res;
    }

    private void helper(char[] s, int pos) {
        if (pos == s.length) {
            res.add(String.valueOf(s));
            return;
        }

        helper(s, pos + 1);
        if (s[pos] > '9') {
            s[pos] ^= 32;
            helper(s, pos + 1);
        }
    }
    
}
// @lc code=end

