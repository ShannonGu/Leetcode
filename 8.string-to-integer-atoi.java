/*
 * @lc app=leetcode id=8 lang=java
 *
 * [8] String to Integer (atoi)
 */
class Solution {
    public int myAtoi(String str) {
        if (str.isEmpty())
            return 0;
        int idx = 0;
        // while (idx < str.length() && str.charAt(idx) == ' ')
        //     ++idx;
        str = str.trim();
        
        int isPos = 1, res = 0;
        if (idx < str.length()) {
            if (str.charAt(idx) == '-' || str.charAt(idx) == '+') {
                if (str.charAt(idx) == '-')
                    isPos = -1;
                idx++;
            } else if (!Character.isDigit(str.charAt(idx)))
                return 0;
            res = strToNum(str.toCharArray(), idx, isPos);
        }
        return res;
    }

    private int strToNum(char[] str, int pos, int isPos) {
        long num = 0;
        while (pos < str.length) {
            if (Character.isDigit(str[pos])) {
                num = num * 10 + isPos * (str[pos] - '0');
                if (isPos == 1 && num > 0x7fffffff)
                    return 0x7fffffff;
                else if (isPos == -1 && num < 0x80000000)
                    return 0x80000000;
                ++pos;
            } else
                break;
        }
        return (int) num;
    }
}
