import java.util.Queue;

/*
 * @lc app=leetcode id=301 lang=java
 *
 * [301] Remove Invalid Parentheses
 */
class Solution {
    //https://www.cnblogs.com/grandyang/p/4944875.html
    // public List<String> removeInvalidParentheses(String s) {
    //     List<String> res = new ArrayList<>();
    //     Set<String> visit = new HashSet<>();
    //     Queue<String> q = new LinkedList<>();
    //     q.offer(s);
    //     boolean found = false;
    //     while (!q.isEmpty()) {
    //         String t = q.poll();
    //         if (isValid(t)) {
    //             res.add(t);
    //             found = true;
    //         }
    //         if (found)
    //             continue;
    //         for (int i = 0; i < t.length(); ++i) {
    //             if (t.charAt(i) != '(' && t.charAt(i) != ')')
    //                 continue;
    //             StringBuilder tmp = new StringBuilder("");
    //             tmp.append(t.substring(0, i)).append(t.substring(i + 1));

    //             if (!visit.contains(tmp.toString())) {
    //                 q.offer(tmp.toString());
    //                 visit.add(tmp.toString());
    //             }
    //         }
    //     }
    //     return res;
    // }

    // private boolean isValid(String s) {
    //     int cnt = 0;
    //     for (char c : s.toCharArray()) {
    //         if (c == '(')
    //             ++cnt;
    //         else if (c == ')')
    //             if (cnt == 0)
    //                 return false;
    //             else
    //                 --cnt;
    //     }
    //     return cnt == 0;
    // }


    public List<String> removeInvalidParentheses(String s) {
        int cnt1 = 0, cnt2 = 0;
        //cnt1,cnt2表示多余的'(',')'个数
        //'(',')'要么一样多，要么'('多，要么')',要么两者都多,比如")(";
        //所以cnt1, cnt2要么都为0，要么都大于0， 要么一个为0，一个大于0
        for (char c : s.toCharArray()) {
            if (c == '(')
                ++cnt1;
            else if (c == ')') {
                //说明前面没有'('可供匹配，')'多余了,cnt2++;
                if (cnt1 == 0)
                    ++cnt2;
                else
                    //说明前面还有剩余的'('可供匹配
                    --cnt1;
            }
        }
        List<String> res = new ArrayList<>();
        helper(s, 0, cnt1, cnt2, res);
        return res;
    }

    private void helper(String s, int start, int cnt1, int cnt2, List<String> res) {
        //如果两个剩余数都为0
        if (cnt1 == 0 && cnt2 == 0) {
            //需判断是否是合法的
            //例如"()())()", cnt1 == 0, cnt2 == 1
            //当遍历到最后一个')'时, cnt1 == 0, cnt2 == 0
            //但此时是"()())(",不是合法的
            if (isValid(s))
                res.add(s);
            return;
        }
        
        for (int i = start; i < s.length(); ++i) {
            //需注意重复的情况
            //例如"()())()",第四，五个均是')',去除其中之一都是相同的情况
            //所以这里只取前面一种情况
            if(i != start && s.charAt(i) == s.charAt(i - 1))
                continue;
            //如果此时'('还有多余，且此时当前字符恰是'(',则将次字符删去，继续判断
            if(cnt1 > 0 && s.charAt(i) == '(')
                helper(s.substring(0, i) + s.substring(i + 1), i, cnt1 - 1, cnt2, res);
            //同理
            if(cnt2 > 0 && s.charAt(i) == ')')
                helper(s.substring(0, i) + s.substring(i + 1), i, cnt1, cnt2 - 1, res);
        }
    }
    
    private boolean isValid(String s) {
        int cnt = 0;
        for (char c : s.toCharArray()) {
            if (c == '(')
                ++cnt;
            else if (c == ')') {
                if (cnt == 0)
                    return false;
                else
                    --cnt;
            }
        }
        return true;
    }
}

