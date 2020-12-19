/*
 * @lc app=leetcode id=6 lang=java
 *
 * [6] ZigZag Conversion
 */
class Solution {
    //https://www.cnblogs.com/grandyang/p/4128268.html
    public String convert(String s, int numRows) {
        if(numRows <= 1)
            return s;
        int gap = (numRows - 1) * 2;
        int len = s.length();
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < numRows; ++i) {
            for (int j = i; j < len; j += gap) {
                //每一列的字符
                res.append(s.charAt(j));

                //每一列字符左边的一个
                //第一行和最后一行分开考虑
                int tmp = j + gap - 2 * i;
                if(i != 0 && i != numRows - 1 && tmp < len)
                    res.append(s.charAt(tmp));
            }
        }
        return res.toString();
    }
}

