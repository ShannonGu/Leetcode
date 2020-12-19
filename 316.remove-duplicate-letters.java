/*
 * @lc app=leetcode id=316 lang=java
 *
 * [316] Remove Duplicate Letters
 */

// @lc code=start
class Solution {
    // https://www.cnblogs.com/grandyang/p/5085379.html
    public String removeDuplicateLetters(String s) {
        int[] m = new int[26];
        StringBuilder res = new StringBuilder("");
        //vis[i]表示当前情况，i代表的字符是否已经被确定好了位置
        boolean[] vis = new boolean[26];
        //首先记录s串中每个字符的个数
        for (char c : s.toCharArray())
            ++m[c - 'a'];

        for (int i = 0; i < s.length(); ++i) {
            char ch = s.charAt(i);
            //出现次数减1
            --m[ch - 'a'];

            //当前字符已经确定好了位置
            if (vis[ch - 'a'])
                continue;

            //用于记录已经形成的字符串的最后一个字符
            char lastCh = ' ';
            int idx = res.length() - 1;
            //已经形成的字符串不为空
            if (idx >= 0) {
                //如果字符串最后一个字符小于当前遍历到的字符并且最后一个字符在之后还会再次出现
                //说明字典序还可以更小
                while (idx >= 0 && (lastCh = res.charAt(idx)) > ch && m[lastCh - 'a'] > 0) {
                    //最后一个字符的位置还不确定
                    vis[lastCh - 'a'] = false;
                    //继续寻找更小的字典序
                    idx--;
                }
            }
            
            //修改字符串长度
            res.setLength(idx + 1);
            //暂时标记当前字符已确定
            vis[ch - 'a'] = true;
            //将当前遍历到的字符加入到字符串
            res.append(ch);
        }
        return res.toString();
    }


    // https://www.jiuzhang.com/solution/remove-duplicate-letters
    // public String removeDuplicateLetters(String s) {
    //     Stack<Integer> st = new Stack<>();
    //     boolean[] vis = new boolean[26];
    //     int[] m = new int[26];
    //     for (char c : s.toCharArray()) {
    //         ++m[c - 'a'];
    //     }

    //     for (int i = 0; i < s.length(); ++i) {
    //         int num = s.charAt(i) - 'a';
    //         m[num]--;
    //         if (vis[num])
    //             continue;
    //         while (!st.isEmpty() && st.peek() > num && m[st.peek()] > 0) {
    //             vis[st.peek()] = false;
    //             st.pop();
    //         }
    //         st.push(num);
    //         vis[num] = true;
    //     }
    //     StringBuilder res = new StringBuilder("");
    //     for(int x : st)
    //         res.append((char)(x + 'a'));
    //     return res.toString();
    // }
}
// @lc code=end
