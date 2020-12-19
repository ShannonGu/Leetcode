/*
 * @lc app=leetcode id=165 lang=java
 *
 * [165] Compare Version Numbers
 */
class Solution {
    public int compareVersion(String version1, String version2) {
        if(version1 == null && version2 == null)
            return 0;
        else if(version1 == null)
            return -1;
        else if(version2 == null)
            return 1;

        //"."表示任意字符，"\\."表示".";
        String[] str1 = version1.split("\\.");
        String[] str2 = version2.split("\\.");


        int len = Math.max(str1.length, str2.length);
        //为了防止数字过大溢出,采用直接比较字符串的每个字符
        for (int i = 0; i < len; ++i) {
            String s1 = i < str1.length ? str1[i] : "0";
            String s2 = i < str2.length ? str2[i] : "0";

            //寻找第一个非0的数字
            int j = 0;
            while(j < s1.length() - 1 && s1.charAt(j) == '0')
                ++j;
                
            //j最大s1.length() - 1,所以s1至少有一个字符
            if(j > 0)
                s1 = s1.substring(j);
            j = 0;
            while(j < s2.length() - 1 && s2.charAt(j) == '0')
                ++j;
            if(j > 0)
                s2 = s2.substring(j);
            //长度相同，遍历每一个字符
            if (s1.length() == s2.length()) {
                for (int k = 0; k < s1.length(); ++k) {
                    if(s1.charAt(k) == s2.charAt(k))
                        continue;
                    return s1.charAt(k) > s2.charAt(k) ? 1 : -1;
                }
            } else {
                return s1.length() > s2.length() ? 1 : -1;
            }
        }
        return 0;
    }
}

