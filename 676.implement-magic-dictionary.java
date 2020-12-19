import java.util.Set;

/*
 * @lc app=leetcode id=676 lang=java
 *
 * [676] Implement Magic Dictionary
 */
class MagicDictionary {
    //https://www.cnblogs.com/grandyang/p/7612918.html

    //先将单词集中的每个单词加入到集合中;
    //然后对要查询的候选单词依次改变其每个字符，在集合中搜索是否存在改变后的单词;
    // private Set<String> s;
    // /** Initialize your data structure here. */
    // public MagicDictionary() {
    //     s = new HashSet();
    // }
    
    // /** Build a dictionary through a list of words */
    // public void buildDict(String[] dict) {
    //     for(String word : dict)
    //         s.add(word);
    // }
    
    // /** Returns if there is any word in the trie that equals to the given word after modifying exactly one character */
    // public boolean search(String word) {
    //     char[] ch = word.toCharArray();
    //     for (int i = 0; i < word.length(); ++i) {
    //         char t = ch[i];
    //         //依次改变每一个字符;
    //         for (char c = 'a'; c <= 'z'; ++c) {
    //             if (t == c)
    //                 continue;
    //             ch[i] = c;
                
    //             if (s.contains(String.valueOf(ch))) {
    //                 return true;
    //             }
    //         }
    //         ch[i] = t;
    //     }
    //     return false;
    // }







    //首先建立单词字符个数与相应单词数组之间的映射
    //然后在与候选单词个数相同的数组查找是否有相应单词满足条件;
    Map<Integer, List<String>> m;

    public MagicDictionary() {
        m = new HashMap();
    }
    
    //建立单词字符个数与相应单词数组之间的映射
    public void buildDict(String[] dict) {
        for (String word : dict) {
            if (!m.containsKey(word.length())) {
                m.put(word.length(), new ArrayList());
            }
            m.get(word.length()).add(word);
        }
    }
    
    public boolean search(String word) {
        //若不存在与候选单词字符个数相对应的数组;
        if (!m.containsKey(word.length()))
            return false;

        //在与个数相对应的数组中查找单词;
        for (String str : m.get(word.length())) {
            //cnt表示修改次数;
            int cnt = 0, i = 0;
            for (; i < word.length(); ++i) {
                if (str.charAt(i) == word.charAt(i))
                    continue;
                //说明与当前单词不匹配
                if (str.charAt(i) != word.charAt(i) && cnt == 1)
                    break;
                ++cnt;
            }
            //只修改一次和数组中某个单词相同;
            if (i == word.length() && cnt == 1)
                return true;
        }
        return false;
    }
}

/**
 * Your MagicDictionary object will be instantiated and called as such:
 * MagicDictionary obj = new MagicDictionary();
 * obj.buildDict(dict);
 * boolean param_2 = obj.search(word);
 */

