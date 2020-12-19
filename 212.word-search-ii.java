
import java.util.ArrayList;

/*
 * @lc app=leetcode id=212 lang=java
 *
 * [212] Word Search II
 */
class Solution {

    class TrieNode {
        TrieNode[] child = new TrieNode[26];
        //表示每个分支路径所对应的字符串;
        String str;

        TrieNode() {
            str = "";
            for (TrieNode node : child)
                node = null;
        }
    }

    class Trie {
        TrieNode root;

        Trie() {
            root = new TrieNode();
        }

        void insert(String word) {
            TrieNode p = root;
            for (char c : word.toCharArray()) {
                int i = c - 'a';
                if (p.child[i] == null)
                    p.child[i] = new TrieNode();
                p = p.child[i];
            }
            // 在每个分支终点存入该分支路径所对应的字符串;
            p.str = word;
        }
    }
    // https://www.cnblogs.com/grandyang/p/4516013.html#commentform
    public List<String> findWords(char[][] board, String[] words) {
        List<String> res = new ArrayList();
        if (board.length == 0 || board[0].length == 0 || words.length == 0)
            return res;
        int row = board.length, col = board[0].length;
        int[][] visited = new int[row][col];
        Trie trie = new Trie();
        for (String word : words) {
            trie.insert(word);
        }
        for (int i = 0; i < row; ++i) {
            for (int j = 0; j < col; ++j) {
                //首先判断board[i][j]处的char在不在Trie中
                int num = board[i][j] - 'a';
                if (trie.root.child[num] != null)
                    helper(board, i, j, trie.root.child[num], visited, res);
            }
        }
        return res;
    }

    public void helper(char[][] board, int i, int j, TrieNode p, int[][] visited, List<String> res) {

        //判断Trie中当前节点p处是否有单词
        if (!p.str.isEmpty()) {
            res.add(p.str);
            // 防止重复出现某个单词
            // 如board中含有多个'a', 而words中有"a"这个单词，
            // 若在存入第一个单词"a"时不清空trie中a对应的str,则会重复存入"a";
            p.str = "";

            //这里不需要return
            //return;
        }
        int[][] dires = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };
        visited[i][j] = 1;
        int row = board.length, col = board[0].length;
        for (int[] dire : dires) {
            int x = i + dire[0], y = j + dire[1];
            if (x >= 0 && x < row && y >= 0 && y < col && visited[x][y] == 0) {
                int num = board[x][y] - 'a';
                if (p.child[num] != null) {
                    helper(board, x, y, p.child[num], visited, res);
                }
            }
        }
        visited[i][j] = 0;
    }
}
