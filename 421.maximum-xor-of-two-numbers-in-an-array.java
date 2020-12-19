/*
 * @lc app=leetcode id=421 lang=java
 *
 * [421] Maximum XOR of Two Numbers in an Array
 */

// https://leetcode.com/problems/maximum-xor-of-two-numbers-in-an-array/discuss/130427/()-92
// https://leetcode.com/problems/maximum-xor-of-two-numbers-in-an-array/discuss/184434/JavaTrie99

class TrieNode {
    TrieNode left;
    TrieNode right;
    int val;

    public TrieNode(int x) {
        left = null;
        right = null;
        val = x;
    }
}

class Trie {
    TrieNode root;

    public Trie() {
        root = new TrieNode(0);
    }

    public void insert(int num) {
        TrieNode cur = root;
        for (int i = 31; i >= 0; --i) {
            int tmp = num & (1 << i);
            if (tmp == 0) {
                if (cur.right == null)
                    cur.right = new TrieNode(0);
                cur = cur.right;
            } else {
                if (cur.left == null)
                    cur.left = new TrieNode(1);
                cur = cur.left;
            }
        }
    }
}

class Solution {

    public int findMaximumXOR(int[] nums) {
        if (nums.length <= 1)
            return 0;

        Trie T = new Trie();
        for (int num : nums)
            T.insert(num);

        TrieNode realRoot = T.root;
        int cnt = 31;
        //令所有数中最高位为1的那一位的上一位为根节点
        while (realRoot.left == null || realRoot.right == null) {
            --cnt;
            realRoot = realRoot.left == null ? realRoot.right : realRoot.left;
        }

        TrieNode cur = realRoot;
        int maxVal = 0;
        //依次遍历每一个数
        for (int i = 0; i < nums.length; ++i) {
            //记录每一个数与最大数进行异或所得到的数
            int val = 0;
            for (int j = cnt; j >= 0; --j) {
                //得到nums[i]的第j高位的二进制位
                int tmp = nums[i] & (1 << j);
                //若是两个孩子都存在
                if (cur.left != null && cur.right != null) {
                    //因为是xor，所以要使当前数与最大数的每一位尽可能不同;
                    if (tmp == 0)
                        cur = cur.left;
                    else
                        cur = cur.right;
                } else {
                    cur = cur.left == null ? cur.right : cur.left;
                }
                //对每一位进行按位或，加入到val中
                val += tmp ^ (cur.val << j);
            }
            //重置cur,对下一个数进行异或
            cur = realRoot;
            //全局异或最大值
            maxVal = Math.max(maxVal, val);
        }
        return maxVal;
    }
}
