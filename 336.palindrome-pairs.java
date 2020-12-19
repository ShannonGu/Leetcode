import java.util.Arrays;
import java.util.Iterator;
import java.util.Map;

/*
 * @lc app=leetcode id=336 lang=java
 *
 * [336] Palindrome Pairs
 */
class Solution {
    //http://www.it610.com/article/5849999.htm
    //https://leetcode.com/problems/palindrome-pairs/discuss/79195/O(n-*-k2)-java-solution-with-Trie-structure
    
    // class TrieNode {
    //     TrieNode[] child;
    //     int idx;
    //     List<Integer> list;

    //     TrieNode() {
    //         child = new TrieNode[26];
    //         idx = -1;
    //         list = new ArrayList();
    //     }
    // }


    // //先将每个单词倒序插入Trie树中;
    // //对于每个单词A，能和其组成palindrome pairs的单词B无非两种情况:
    // //1.长度<=A,2长度>A;
    // public List<List<Integer>> palindromePairs(String[] words) {
    //     List<List<Integer>> res = new ArrayList();
    //     TrieNode root = new TrieNode();
    //     //将每个单词插入Trie树中;
    //     for (int i = 0; i < words.length; ++i) {
    //         insert(root, words[i], i);
    //     }

    //     //对每个单词查找能与配对的单词
    //     for (int i = 0; i < words.length; ++i) {
    //         search(words, i, root, res);
    //     }
    //     return res;
    // }

    // //将每个单词倒序插入Trie树中;
    // private void insert(TrieNode root, String word, int idx) {
    //     char[] ch = word.toCharArray();
    //     for (int i = ch.length - 1; i >= 0; --i) {
    //         int num = ch[i] - 'a';
    //         if (root.child[num] == null)
    //             root.child[num] = new TrieNode();

    //         //如果该单词word[0,i]是palindrome的，
    //         //则在Trie树中在word[i+1]字符节点上标注该word在单词数组中的下标idx
    //         //原因是当在最后的查找过程中,遇到一个比当前word长度短的单词B时,
    //         //由于word是倒序插入Trie树中的，所以当B全部匹配完成时,假设B的长度为len;
    //         //即B[0, len-1]与word[i+1, i+len-1]是对称的,
    //         //那么可以根据word[i+1]节点上list是否有下标idx来判断word[0,i]是否是palindrome
    //         //若是则可以组成B+word的palindrome pair
    //         //比如当前单词A是cbcba, 在Trie中的分支则是a-b-c-b-c, 若单词数组中还有个单词B是ab
    //         //则当B与A的后两个字符ab匹配完成时,由于A中cbc也是palindrome的，
    //         //所以会在Trie中在第一个b上的list中加上下标idx，所以可以直接判断BA可以组成palindrome pair,
    //         //即abcbcba;
    //         if (isPalindrome(word, 0, i))
    //             root.list.add(idx);
    //         root = root.child[num];
    //     }
    //     //在最后一个节点的list上也要添加idx;
    //     root.list.add(idx);

    //     //插入完成后，修改root.idx,表示该倒序分支的在单词数组的索引;
    //     root.idx = idx;
    // }

    // private void search(String[] words, int i, TrieNode root, List<List<Integer>> list) {
    //     //由于Trie中是倒序插入的;
    //     //所以可以正序遍历每个单词的字符时，如果不符合Trie的每个分支的节点时，可以直接排除;
    //     for (int j = 0; j < words[i].length(); ++j) {

    //         //表示当前查找的单词A与Trie中某个比A长度小的单词B进行匹配;
    //         //由于B已经匹配完成，所以要检查A中剩下字符是否是palindrome的
    //         //若是，则可以组成AB的palindrome pair;
    //         //如A为abcbc, B为ba,B在Trie中的分支为a-b, 当B全部匹配完成时,A中cbc也是palindrome的
    //         //即可以组成AB的palindrome pair;
    //         //将二者下标配对;
    //         if (root.idx >= 0 && root.idx != i && isPalindrome(words[i], j, words[i].length() - 1))
    //             list.add(Arrays.asList(i, root.idx));

    //         //继续匹配下一个字符;
    //         root = root.child[words[i].charAt(j) - 'a'];

    //         if (root == null)
    //             return;
    //     }

    //     //若此时root != null,说明当前查找的单词A比Trie中的单词B短;
    //     //例如A为abcbc, B为bbcbcba, 在Trie中的分支为a-b-c-b-c-b-b
    //     //此时root节点不为空，则可以与以B中匹配好的长度的分支为后缀的单词组成palindrome pair;
    //     //即此时为AB的palindrome;
    //     for (int j : root.list) {
    //         if (i != j)
    //             list.add(Arrays.asList(i, j));
    //     }
    // }

    // private boolean isPalindrome(String word, int i, int j) {
    //     while (i < j) {
    //         if (word.charAt(i++) != word.charAt(j--))
    //             return false;
    //     }
    //     return true;
    // }


    public List<List<Integer>> palindromePairs(String[] words) {
        List<List<Integer>> res = new ArrayList();
        //建立每个单词与其下标的映射;
        Map<String, Integer> m = new HashMap();
        //存储每个单词的长度;
        Set<Integer> s = new TreeSet();
        for (int i = 0; i < words.length; ++i) {
            m.put(words[i], i);
            s.add(words[i].length());
        }

        for (int i = 0; i < words.length; ++i) {
            //先将单词逆序;
            String str = new StringBuffer(words[i]).reverse().toString();
            int len = str.length();
            //先找到与该单词长度一样的另一个单词
            //检查是否可以与其配对成palindrome pair
            //如A:abc, 逆序cba, B:cba, 可以配成AB=abccba;
            if (m.containsKey(str) && m.get(str) != i)
                res.add(Arrays.asList(i, m.get(str)));

            Iterator it = s.iterator();
            //除了上面长度相等的情况,还有不等的情况
            //因为每个单词都遍历一遍，所以长度大小的关系是相对的
            //寻找比当前单词长度小的单词可以配对的情况;
            while (it.hasNext()) {
                int d = (Integer) it.next();
                if (d < len) {
                    //A:abcdd, 逆序:ddcba, dd为palindrome,  B:cba, 可以组成AB=abcddcba;
                    if (isPalindrome(str, 0, len - d - 1) && m.containsKey(str.substring(len - d)))
                        res.add(Arrays.asList(i, m.get(str.substring(len - d))));
                    //A:aabcd, 逆序:dcbaa, aa为palindrome,  B:dcb, 可以组成BA=dcbaabcd;
                    if (isPalindrome(str, d, len - 1) && m.containsKey(str.substring(0, d)))
                        res.add(Arrays.asList(m.get(str.substring(0, d)), i));
                }
            }
        }
        return res;
    }
    
    private boolean isPalindrome(String word, int i, int j) {
        while (i < j) {
            if (word.charAt(i++) != word.charAt(j--))
                return false;
        }
        return true;
    }
}
