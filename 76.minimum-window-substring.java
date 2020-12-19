import java.util.Map;

/*
 * @lc app=leetcode id=76 lang=java
 *
 * [76] Minimum Window Substring
 */
class Solution {
    // https://www.cnblogs.com/grandyang/p/4340948.html
    // public String minWindow(String s, String t) {
    //     String res = "";
    //     Map<Character, Integer> letterCnt = new HashMap<>();
    //     //先将t串中字符与出现次数映射
    //     for (char c : t.toCharArray()) {
    //         if (!letterCnt.containsKey(c)) {
    //             letterCnt.put(c, 1);
    //             continue;
    //         }
    //         letterCnt.put(c, letterCnt.get(c) + 1);
    //     }
    //     int left = 0, cnt = 0, minLen = Integer.MAX_VALUE;
    //     //遍历s串
    //     for (int i = 0; i < s.length(); ++i) {
    //         //先扩大右边界
    //         //若s[i]在t串中
    //         if (letterCnt.containsKey(s.charAt(i))) {
    //             int tmp = letterCnt.get(s.charAt(i));
    //             //判断是不是多余的s[i]
    //             if (tmp - 1 >= 0)
    //                 //不是则更新已经出现的t串中的字符个数
    //                 ++cnt;
    //             letterCnt.put(s.charAt(i), tmp - 1);
    //         }

    //         //若此时t串中字符已经全部出现
    //         while (cnt == t.length()) {
    //             //则更新最短子串长度
    //             if (minLen > i - left + 1) {
    //                 minLen = i - left + 1;
    //                 res = s.substring(left, left + minLen);
    //             }
    //             //再向右扩大左边界，缩小子串范围
    //             if (letterCnt.containsKey(s.charAt(left))) {
    //                 int tmp = letterCnt.get(s.charAt(left));
    //                 //检查s[left]是不是属于t串的但是多余的字符
    //                 if (tmp + 1 > 0)
    //                     //不是则减小已出现的t串的字符个数
    //                     --cnt;
    //                 letterCnt.put(s.charAt(left), tmp + 1);
    //             }
    //             //向右扩大左边界
    //             ++left;
    //         }
    //     }
    //     return res;
    // }



    //用数组代替HashMap
    public String minWindow(String s, String t) {
        String res = "";
        int[] letterCnt = new int[128];
        //先将t串中字符与出现次数映射
        for (char c : t.toCharArray()) {
            ++letterCnt[c];
        }
        int left = 0, cnt = 0, minLen = Integer.MAX_VALUE;
        //遍历s串
        for (int i = 0; i < s.length(); ++i) {
            //先扩大右边界
            //若s[i]在t串中
            if (--letterCnt[s.charAt(i)] >= 0)
                ++cnt;
            
            //若此时t串中字符已经全部出现
            while (cnt == t.length()) {
                //则更新最短子串长度
                if (minLen > i - left + 1) {
                    minLen = i - left + 1;
                    res = s.substring(left, left + minLen);
                }
                //再向右扩大左边界，缩小子串范围
                //s[left]是t串中的字符，且不是多余的
                if (++letterCnt[s.charAt(left)] > 0)
                    --cnt;
                //向右扩大左边界
                ++left;
            }
        }
        return res;
    }
}

