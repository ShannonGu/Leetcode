/*
 * @lc app=leetcode id=140 lang=java
 *
 * [140] Word Break II
 */
class Solution {
    //http://zxi.mytechroad.com/blog/leetcode/leetcode-140-word-break-ii/
    //记忆化递归
    public List<String> wordBreak(String s, List<String> wordDict) {
        Set<String> dict = new HashSet<>();
        for (String word : wordDict)
            dict.add(word);
        return helper(s, dict);
    }
    
    private List<String> helper(String s, Set<String> dict) {
        //首先查看记忆数组里有没有该字符串的情况
        if (mem.containsKey(s))
            return mem.get(s);

        //用于存放所有情况
        List<String> ans = new ArrayList<>();

        //若dict本来就有该单词，则把该单词直接加入ans
        if (dict.contains(s))
            ans.add(s);

        //遍历左右两段的分割点
        for (int i = 1; i <= s.length(); ++i) {
            //右边部分的字符串
            String right = s.substring(i);
            //dict不存在right
            //这种划分不满足条件
            if (!dict.contains(right))
                continue;
            //取左边部分的字符串
            String left = s.substring(0, i);
            //对左边字符串继续递归划分
            List<String> left_part = helper(left, dict);
            //将左边字符串划分得到的每种情况与right拼接起来
            List<String> whole = append(left_part, right);

            //加入到的ans中
            for (String str : whole) {
                ans.add(str);
            }
        }

        //更新记忆数组
        mem.put(s, ans);
        return mem.get(s);
    }
    
    private List<String> append(List<String> prefixes, String word) {
        List<String> res = new ArrayList<>();
        //对左边字符串划分的每种情况拼接右边的字符串
        for (String str : prefixes) {
            res.add(new StringBuilder(str).append(" ").append(word).toString());
            // res.add(str + " " + word);
        }
        return res;
    }
    
    private Map<String, List<String>> mem = new HashMap<>();
}

