import java.util.HashSet;
import java.util.List;

/*
 * @lc app=leetcode id=139 lang=java
 *
 * [139] Word Break
 */
class Solution {
    // public boolean wordBreak(String s, List<String> wordDict) {
    //     if(wordDict.isEmpty() && s.length() != 0)
    //         return false;
    //     Set<String> dict = new HashSet<>();
    //     for(String str : wordDict)
    //         dict.add(str);
    //     int len = s.length();
    //     //dp[i]表示s[0, i)是否至少可由wordDict中的一个单词构成
    //     boolean[] dp = new boolean[len + 1];
    //     //注意""的情况，
    //     dp[0] = true;
    //     for (int i = 0; i <= len; ++i) {
    //         for (j = 0; j < i; ++j) {
    //             //s[0, j)满足条件，再检查s[j, i)的情况
    //             if (dp[j] == true && dict.contains(s.substring(j, i))) {
    //                 dp[i] = true;
    //                 break;
    //             }
    //         }
    //         if(dp[len])
    //             return true;
    //     }
    //     return false;
    // }

    //https://www.youtube.com/watch?v=ptlwluzeC1I
    //记忆化+递归
    public boolean wordBreak(String s, List<String> wordDict) {
        Set<String> dict = new HashSet<>();
        for (String word : wordDict) {
            dict.add(word);
        }

        return helper(s, dict);
    }

    public boolean helper(String s, Set<String> dict) {
        if (mem.containsKey(s))
            return mem.get(s);
        if (dict.contains(s)) {
            mem.put(s, true);
            return true;
        }

        for (int i = 1; i <= s.length(); ++i) {
            String left = s.substring(0, i);
            String right = s.substring(i);
            if (dict.contains(left) && helper(right, dict)) {
                mem.put(s, true);
                return true;
            }
        }
        mem.put(s, false);
        return false;
    }

    private Map<String, Boolean> mem = new HashMap<>();
}

