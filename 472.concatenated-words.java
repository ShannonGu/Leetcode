import java.util.Set;

/*
 * @lc app=leetcode id=472 lang=java
 *
 * [472] Concatenated Words
 */

// https://www.cnblogs.com/grandyang/p/4507286.html


// https://leetcode.com/problems/concatenated-words/discuss/95720/Java-Trie
// class Solution {
//     class TrieNode {
//         TrieNode[] child = new TrieNode[26];
//         boolean isWord;

//         TrieNode() {
//             isWord = false;
//             for (TrieNode node : child) {
//                 node = null;
//             }
//         }
//     }
//     class Trie {
//         TrieNode root;

//         Trie() {
//             root = new TrieNode();
//         }

//         public void insert(String word) {
//             TrieNode p = root;
//             for (char c : word.toCharArray()) {
//                 int i = c - 'a';
//                 if (p.child[i] == null)
//                     p.child[i] = new TrieNode();
//                 p = p.child[i];
//             }
//             p.isWord = true;
//         }

//         public boolean query(String word) {
//             TrieNode p = root;
//             for (char c : word.toCharArray()) {
//                 int i = c - 'a';
//                 if (p.child[i] == null)
//                     return false;
//                 p = p.child[i];
//             }
//             return p.isWord;
//         }
//     }

//     public List<String> findAllConcatenatedWordsInADict(String[] words) {
//         List<String> res = new ArrayList();
//         if (words.length == 0)
//             return res;
//         Trie T = new Trie();
//         for (String word : words) {
//             //需要判断空字符串
//             if (word.equals(""))
//                 continue;
//             T.insert(word);
//         }

//         for (String word : words) {
//             if (word.equals(""))
//                 continue;
//             if (helper(T, word, false))
//                 res.add(word);
//         }
//         return res;
//     }

//     public boolean helper(Trie T, String s, boolean cut) {
//         //cut表示前面的分割是满足要求的
//         if (s.equals("") && cut)
//             return true;

//         for (int i = 1; i <= s.length(); ++i) {
//             String pre = s.substring(0, i);
//             //当前单词分割完毕，但之前没有已经分割好的单词
//             //所以不能由至少两个短单词组成
//             if (i == s.length() && !cut)
//                 return false;
//             //单词集中有pre这个单词
//             if (T.query(pre)) {
//                 //并且后面的单词也能分割更短的单词
//                 if (helper(T, s.substring(i), true))
//                     return true;
//             }
//         }
//         return false;
//     }
// }



// class Solution {
//     public List<String> findAllConcatenatedWordsInADict(String[] words) {
//         List<String> res = new ArrayList();
//         if (words.length == 0)
//             return res;
//         Set<String> s = new HashSet();
//         for (String word : words) {
//             s.add(word);
//         }
//         for (String word : words) {
//             if (helper(word, 0, s, 0))
//                 res.add(word);
//         }
//         return res;

//     }

//     public boolean helper(String word, int pos, Set<String> s, int cnt) {
//         if (pos == word.length() && cnt >= 2) {
//             return true;
//         }

//         //i表示每次分割字符串的长度;
//         for (int i = 1; i <= word.length() - pos; i++) {
//             //当前分割出的字符串
//             String tmp = word.substring(pos, pos + i);
//             //下一次分割以pos+i为起点
//             if (s.contains(tmp) && helper(word, pos + i, s, cnt + 1))
//                 return true;
//         }
//         return false;
//     }
// }




class Solution {
    public List<String> findAllConcatenatedWordsInADict(String[] words) {
        List<String> res = new ArrayList();
        if (words.length == 0)
            return res;
        Set<String> s = new HashSet();
        for (String word : words) {
            s.add(word);
        }

        for (String word : words) {
            int len = word.length();
            //dp[i]表示s[0, i)是否可以按照题意被拆分
            boolean[] dp = new boolean[len + 1];
            //注意""的情况，总是符合要求,所以dp[0]=true;
            dp[0] = true;
            for (int i = 0; i < len; ++i) {
                //表示s[0,i)不存在或者不能划分
                if (dp[i] == false)
                    continue;
                
                //dp[i] = true;
                //至少要有两段word.substring(0, len-1)和word.substring(len-1, len);
                for (int j = i + 1; j <= len; ++j) {
                    //s[i, j)存在
                    //则s[0, j)可以划分成至少两个更短的单词
                    //j-i<len,否则该单词不能分割
                    if (j - i < len && s.contains(word.substring(i, j))) {
                        dp[j] = true;
                    }
                }

                //s[0, len)可以划分成至少两个更短的单词
                //该单词符合要求，不必继续尝试分割，退出当前循环
                if (dp[len]) {
                    res.add(word);
                    break;
                }
            }
        }
        return res;
    }
}
