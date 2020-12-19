/*
 * @lc app=leetcode id=211 lang=java
 *
 * [211] Add and Search Word - Data structure design
 */

 //https://www.cnblogs.com/grandyang/p/4507286.html
class WordDictionary {

    class TrieNode {
        TrieNode[] child = new TrieNode[27];
        boolean isWord;

        TrieNode() {
            isWord = false;
            for (TrieNode node : child) {
                node = null;
            }
        }
    }

    TrieNode root;

    /** Initialize your data structure here. */
    public WordDictionary() {
        root = new TrieNode();
    }

    /** Adds a word into the data structure. */
    public void addWord(String word) {
        TrieNode p = root;
        for (char c : word.toCharArray()) {
            int i = c - 'a';
            if (p.child[i] == null)
                p.child[i] = new TrieNode();
            p = p.child[i];

        }
        p.isWord = true;
    }

    /**
     * Returns if the word is in the data structure. A word could contain the dot
     * character '.' to represent any one letter.
     */
    public boolean search(String word) {
        return searchWord(word, root, 0);
    }

    //DFS
    public boolean searchWord(String word, TrieNode p, int i) {
        //i到达了word的长度;
        //检查在最后一个节点是否有单词;
        if(i == word.length())
            return p.isWord;

        //当前匹配的字符是'.'
        if (word.charAt(i) == '.') {
            //对当前节点的所有子节点进行遍历;
            //找到一个匹配的;
            for (TrieNode node : p.child) {
                if (node != null && searchWord(word, node, i + 1))
                    return true;
            }
        }
        else {
            int num = word.charAt(i) - 'a';
            if (p.child[num] != null && searchWord(word, p.child[num], i + 1))
                return true;
        }
        return false;
    }
}

/**
 * Your WordDictionary object will be instantiated and called as such:
 * WordDictionary obj = new WordDictionary(); 
 * obj.addWord(word); 
 * boolean param_2 = obj.search(word);
 */
