import java.util.ArrayList;

import com.sun.org.apache.bcel.internal.generic.INEG;

/*
 * @lc app=leetcode id=745 lang=java
 *
 * [745] Prefix and Suffix Search
 */
class WordFilter {
    // //https://leetcode.com/problems/prefix-and-suffix-search/solution/
    // //Approach 3

    // class TrieNode {
    //     TrieNode[] child;
    //     int weight;

    //     public TrieNode() {
    //         child = new TrieNode[27];
    //         weight = 0;
    //     }
    // }

    // //将每个单词末尾加上'{'后，然后循环将单词的每个字母作为首字母开始挂到Trie树上,
    // //查询时，将suffix + '{' + prefix，在Trie树中查找是否有相应的分支;
    // TrieNode T;
    // public WordFilter(String[] words) {
    //     T = new TrieNode();
    //     for (int weight = 0; weight < words.length; ++weight) {
    //         //'{' - 'a' == 26;
    //         String word = words[weight] + "{";
    //         //依次将word中的每个字符作为头插入到Trie中
    //         for (int i = 0; i < word.length(); ++i) {
    //             TrieNode cur = T;
    //             cur.weight = weight;
    //             //这里2 * word.length() - 1的原因是
    //             //prefix和suffix均可以是单词本身;
    //             //比如单词是apple，若查询组合是['apple', 'apple']，也可以查询成功
    //             for (int j = i; j < 2 * word.length() - 1; ++j) {
    //                 int k = word.charAt(j % word.length()) - 'a';
    //                 if(cur.child[k] == null)
    //                     cur.child[k] = new TrieNode();
    //                 cur = cur.child[k];
    //                 cur.weight = weight;
    //             }
    //         }
    //     }
    // }

    // public int f(String prefix, String suffix) {
    //     TrieNode cur = T;
    //     for (char c : (suffix + "{" + prefix).toCharArray()) {
    //         int num = c - 'a';
    //         if (cur.child[num] == null)
    //             return -1;
    //         cur = cur.child[num];
    //     }
    //     return cur.weight;
    // }

    //https://www.cnblogs.com/grandyang/p/8331660.html
    //解法二

    //使用两个hash table
    //一个建立所有前缀和权重数组之间的映射
    //一个建立所有后缀和权重数组之间的映射
    private Map<String, List<Integer>> mPre = new HashMap();
    private Map<String, List<Integer>> mSuf = new HashMap();

    public WordFilter(String[] words) {
        for (int weight = 0; weight < words.length; ++weight) {
            String word = words[weight];

            //每个单词所有前缀和权重数组之间的映射;
            for (int i = 0; i <= word.length(); ++i) {
                String str = word.substring(0, i);
                if (!mPre.containsKey(str))
                    mPre.put(str, new ArrayList());
                mPre.get(str).add(weight);
            }

            //每个单词所有后缀和权重数组之间的映射;
            for (int j = 0; j <= word.length(); ++j) {
                String str = word.substring(word.length() - j);
                if (!mSuf.containsKey(str))
                    mSuf.put(str, new ArrayList());
                mSuf.get(str).add(weight);
            }
        }
    }

    public int f(String prefix, String suffix) {
        //没有该prefix或suffix
        if (mPre.get(prefix) == null || mSuf.get(suffix) == null)
            return -1;
        List<Integer> pre = mPre.get(prefix), suf = mSuf.get(suffix);

        int i = pre.size() - 1, j = suf.size() - 1;
        //寻找对应权重相同的前缀和后缀
        //说明是同一个单词的前缀和后缀;
        while (i >= 0 && j >= 0) {
            if (pre.get(i) > suf.get(j))
                i--;
            else if (pre.get(i) < suf.get(j))
                j--;
            else
                return pre.get(i);
        }
        return -1;
    }

}

/**
 * Your WordFilter object will be instantiated and called as such:
 * WordFilter obj = new WordFilter(words);
 * int param_1 = obj.f(prefix,suffix);
 */

