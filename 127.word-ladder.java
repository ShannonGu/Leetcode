import java.util.Map;
import java.util.Queue;

/*
 * @lc app=leetcode id=127 lang=java
 *
 * [127] Word Ladder
 */

// @lc code=start
class Solution {
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        Set<String> wordSet = new HashSet<>();
        for (String word : wordList)
            wordSet.add(word);
        if (!wordSet.contains(endWord))
            return 0;
        // 建立某一路径的最后一个单词与该路径长度的映射
        Map<String, Integer> pathCnt = new HashMap<>();
        pathCnt.put(beginWord, 1);
        Queue<String> q = new LinkedList<>();
        q.offer(beginWord);
        while (!q.isEmpty()) {
            String word = q.poll();
            for (int j = 0; j < word.length(); j++) {
                char[] newWord = word.toCharArray();
                // 每次替换一个字符
                for (char c = 'a'; c <= 'z'; c++) {
                    newWord[j] = c;
                    String tmp = String.valueOf(newWord);
                    if (tmp.equals(endWord))
                        return pathCnt.get(word) + 1;
                    // wordSet中存在替换后的字符串且当前路径中不存在该字符串
                    if (wordSet.contains(tmp) && !pathCnt.containsKey(tmp)) {
                        q.offer(tmp);
                        pathCnt.put(tmp, pathCnt.get(word) + 1);
                    }
                }
            }
        }
        return 0;
    }
}
// @lc code=end
