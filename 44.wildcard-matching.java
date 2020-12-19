/*
 * @lc app=leetcode id=44 lang=java
 *
 * [44] Wildcard Matching
 */
class Solution {
    //https://www.cnblogs.com/grandyang/p/4401196.html
    //https://leetcode.com/problems/wildcard-matching/discuss/17839/C%2B%2B-recursive-solution-16-ms
    // public boolean isMatch(String s, String p) {
    //     int m = s.length(), n = p.length();
    //     //dp[i][j]表示s串前i个字符[0, i-1]与p串前j个字符[0, j - 1]是否能匹配
    //     boolean[][] dp = new boolean[m + 1][n + 1];
    //     //空串总能匹配
    //     dp[0][0] = true;

    //     //考虑s为空串的情况
    //     for (int i = 1; i <= n; ++i) {
    //         //若p[i - 1] == '*'
    //         //可以任意匹配
    //         if (p.charAt(i - 1) == '*')
    //             dp[0][i] = dp[0][i - 1];
    //     }

    //     for (int i = 1; i <= m; ++i) {
    //         for (int j = 1; j <= n; ++j) {
    //             if (p.charAt(j - 1) == '*') {
    //                 //若p[j - 1]=='*'
    //                 //可以匹配空字符,即用s[0, i-1]与p[0,j-2]匹配，也即dp[i][j-1];
    //                 //或者'*'可以匹配1个字符,即dp[i - 1][j - 1];
    //                 //或者匹配多个字符,比如
    //                 //   0 1 2 3 4 5 6 7
    //                 //s: a b b c c a b c
    //                 //p: a b b *
    //                 //当i==5, j==4时, '*'可以匹配连续匹配s[0,4]=="abbcc", p[0,3]="abb*"
    //                 //p[0,3]=="abb*"可以与s[0,3]=="abbc"匹配也即dp[i-1][j]，然后'*'还可以多匹配一个字符即s[4]
    //                 //此时dp[5][4]==dp[4][4]
    
    //                 //p串前j个字符能与s串的前i-1个字符匹配成功(dp[i-1][j]),那么dp[i][j]也能为true
    //                 //因为'*'可以匹配任意字符串，再多加一个任意字符也没问题
    //                 //其中dp[i-1][j-1]||dp[i-1][j]可以并在一起写为dp[i-1][j]，因为p[j-1]=='*',所以s[i-1]之后的字符已经不用考虑了
    //                 //只需考虑s[0, i-1)与dp[0,j)之间是否能匹配
    //                 //dp[i][j] = dp[i][j - 1] || dp[i - 1][j - 1] || dp[i - 1][j];
    //                 dp[i][j] = dp[i][j - 1] || dp[i-1][j];
    //             } else {
    //                 //p[j-1]可能是字母或者'?'
    //                 //'?'可以匹配任何一个字符(不包括空)
    //                 dp[i][j] = (s.charAt(i - 1) == p.charAt(j - 1) || p.charAt(j - 1) == '?') && dp[i - 1][j - 1];
    //             }
    //         }
    //     }
    //     return dp[m][n];
    // }

    
    public boolean isMatch(String s, String p) {
        return helper(s, p, 0, 0) > 1;
    }

    //有三种状态
    //0表示s串匹配到了末尾,但是未匹配成功
    //1表示未匹配到s串的末尾就失败了
    //2表示完全匹配成功
    private int helper(String s, String p, int i, int j) {
        //如果两者都匹配到了末尾
        //则成功
        if(i == s.length() && j == p.length())
            return 2;
        //若s串到了末尾，而p串未匹配到末尾, 且p串最后所处的位置的字符不是'*', 则肯定失败
        //因为'?'只能匹配一个字符且不能为空
        if(i == s.length() && p.charAt(j) != '*')
            return 0;
        //若s串未到末尾，p串到了末尾，则当前匹配状态失败
        if(j == p.length())
            return 1;
        
        //若两者均未到末尾
        if(i < s.length() && j < p.length() && (s.charAt(i) == p.charAt(j) || p.charAt(j) == '?'))
            return helper(s, p, i + 1, j + 1);
        
        //若两者当前字符不同，且p串当前为'*'
        if (p.charAt(j) == '*') {
            //考虑连续'*', 只需考虑连续'*'中的最后一个'*'
            if (j + 1 < p.length() && p.charAt(j + 1) == '*')
                return helper(s, p, i, j + 1);

            //s串中前i个字符已经匹配完成
            //考虑后面s.length() - i个
            //由于 '*'可以匹配任意数量的字符
            //k表示 '*'匹配的字符的数量
            for (int k = 0; k <= (int)s.length() - i; ++k) {
                //依次跨过s[i]之后的k个字符，即从s[i+k]与p[j+1]开始匹配
                int res = helper(s, p, i + k, j + 1);

                //剪枝条件
                //若res==0, res==2是注定失败或者成功
                //则不需要再进行了
                //而==1的情况，还需要再进行匹配
                //比如
                //   0 1 2 3 4 5 6 7
                //s: a b c c c e e e
                //p: a b * * e e 
                //当最后一个 '*'匹配三个 'c'时， s[5]与p[4]匹配，p串匹配完毕后，
                //s串匹配到s[7], 不成功,此时返回1;
                //而当 '*'匹配"ccce"时, s[6]与p[4]开始匹配, 此时均可匹配到末尾,成功
                if (res == 0 || res == 2)
                    return res;
            }
        }
        //所有条件均不满足,返回1
        return 1;
    }
}

