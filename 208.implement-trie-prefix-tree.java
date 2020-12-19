/*
 * @lc app=leetcode id=208 lang=java
 *
 * [208] Implement Trie (Prefix Tree)
 */

//https://www.cnblogs.com/grandyang/p/4491665.html
 
class TrieNode {
    TrieNode[] child;
    boolean isWord;

    public TrieNode() {
        child = new TrieNode[26];
    }
}

class Trie {
    TrieNode root;
    /** Initialize your data structure here. */
    public Trie() {
        root = new TrieNode();
    }

    /** Inserts a word into the trie. */
    public void insert(String word) {
        TrieNode p = root;
        for (char c : word.toCharArray()) {
            int i = c - 'a';
            if (p.child[i] == null)
                p.child[i] = new TrieNode();
            p = p.child[i];
        }
        //在每个分支终点指示该分支路径所表示的字符串;
        p.isWord = true;
    }

    /** Returns if the word is in the trie. */
    public boolean search(String word) {
        TrieNode p = root;
        for (char c : word.toCharArray()) {
            int i = c - 'a';
            if (p.child[i] == null)
                return false;
            p = p.child[i];
        }
        //表示trie中是否有该word;
        return p.isWord;
    }

    /** Returns if there is any word in the trie that starts with the given prefix. */
    public boolean startsWith(String prefix) {
        TrieNode p = root;
        for (char c : prefix.toCharArray()) {
            int i = c - 'a';
            if (p.child[i] == null)
                return false;
            p = p.child[i];
        }
        return true;
    }
}
/**
 * Your Trie object will be instantiated and called as such: 
 * Trie obj = new Trie(); 
 * obj.insert(word); 
 * boolean param_2 = obj.search(word); 
 * boolean param_3 = obj.startsWith(prefix);
 */
