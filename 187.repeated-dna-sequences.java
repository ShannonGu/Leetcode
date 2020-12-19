import java.util.ArrayList;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/*
 * @lc app=leetcode id=187 lang=java
 *
 * [187] Repeated DNA Sequences
 */
class Solution {
    //https://www.cnblogs.com/grandyang/p/4284205.html
    public List<String> findRepeatedDnaSequences(String s) {
        //由于构成字符串的字符只有4种,二进制表示分别为
        //A:0100 0001  C:0100 0011  G:0100 0111  T:0101 0100
        //发现后三位都不相同，所以可以用后三位来区分他们
        //题目要求是10个字符长度的字符串，每个字符用三位来区分，10个字符需要三十位
        //一个32位整形数就可以表示,
        //为了取出后30位，还需要用个mask，取值为0x7ffffff,用此mask可以取出后27位，再向左平移三位即可
        List<String> res = new ArrayList<>();
        if(s.length() <= 10)
            return res;
        int mask = 0x7ffffff, cur = 0;
        Map<Integer, Integer> m = new HashMap<>();

        //首先取出前9个字符
        for (int i = 0; i < 9; ++i)
            //s.charAt(i)&7 表示用该字符对应的3位二进制数
            //cur向左移动三位来存放s[i]对应的二进制数
            cur = (cur << 3) | (s.charAt(i) & 7);

        //从第10个字符开始遍历
        for (int i = 9; i < s.length(); ++i) {
            //首先取出上一次10个字符的后9个字符
            //左移三位来存放s[i]
            cur = ((cur & mask) << 3) | (s.charAt(i) & 7);
            //如果哈希表中已经出现过了这个字符串
            if (m.containsKey(cur)) {
                //并且此前只出现过一次
                //注意只有第二次出现，才存入答案,避免重复
                if (m.get(cur) == 1)
                    //那么将该字符串存入答案
                    res.add(s.substring(i - 9, i + 1));
                //并更新出现次数
                m.put(cur, m.get(cur) + 1);
            } else {
                //否则将其存入哈希表
                m.put(cur, 1);
            }
        }
        return res;
    }




    // public List<String> findRepeatedDnaSequences(String s) {
    //     Set<String> st = new HashSet<>();
    //     Set<String> res = new HashSet<>();
    //     for (int i = 0; i + 9 < s.length(); ++i) {
    //         String t = s.substring(i, i + 10);
    //         if (st.contains(t))
    //             res.add(t);
    //         else
    //             st.add(t);
    //     }
    //     return new ArrayList<>(res);
    // }
}

