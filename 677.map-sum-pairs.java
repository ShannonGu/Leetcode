/*
 * @lc app=leetcode id=677 lang=java
 *
 * [677] Map Sum Pairs
 */
class MapSum {


    //创建TrieNode
    class TrieNode {
        //将单词的每个字符与一个TrieNode映射起来;
        Map<Character, TrieNode> child = new HashMap();
        //表示以单词结尾处字符所代表的分数总和;
        int score = 0;
    }
    //https://leetcode.com/problems/map-sum-pairs/solution/
    //Approach 3

    //将所有出现过的单词与其分数映射起来;
    Map<String, Integer> map;
    TrieNode root;
    /** Initialize your data structure here. */
    public MapSum() {
        map = new HashMap();
        root = new TrieNode();
    }
    
    public void insert(String key, int val) {
        //首先查看是否出现过该单词;
        //若出现得到其分数差;
        int delta = val - map.getOrDefault(key, 0);
        //再更新该单词所代表的分数;
        map.put(key, val);

        TrieNode cur = root;
        //根节点也要加上分数
        //因为""是所有单词的前缀;
        cur.score += delta;

        //插入每一个字符，构建Trie;
        for (char c : key.toCharArray()) {
            cur.child.putIfAbsent(c, new TrieNode());
            cur = cur.child.get(c);
            //标记到该节点的字符串为前缀所代表的分数;
            cur.score += delta;
        }
    }
    
    public int sum(String prefix) {
        TrieNode cur = root;
        //遍历前缀的每一个字符;
        for (char c : prefix.toCharArray()) {
            cur = cur.child.get(c);
            //不存在该前缀
            if (cur == null)
                return 0;
        }
        return cur.score;
    }




    //Approach 2

    // //建立每个单词与分数的映射;
    // Map<String, Integer> map;

    // //更新每个前缀与分数和的映射;
    // Map<String, Integer> score;
    // /** Initialize your data structure here. */
    // public MapSum() {
    //     map = new HashMap();
    //     score = new HashMap();
    // }
    
    // public void insert(String key, int val) {
    //     int delta = val - map.getOrDefault(key, 0);
    //     //更新出现过的单词所对应的分数;
    //     map.put(key, val);
    //     String prefix = "";
    //     for(char c : key.toCharArray()){
    //         prefix += c;
    //         //更新前缀所对应的分数和;
    //         score.put(prefix, score.getOrDefault(prefix, 0) + delta);
    //     }
    // }
    
    // public int sum(String prefix) {
    //     return score.getOrDefault(prefix, 0);
    // }
}

/**
 * Your MapSum object will be instantiated and called as such:
 * MapSum obj = new MapSum();
 * obj.insert(key,val);
 * int param_2 = obj.sum(prefix);
 */

