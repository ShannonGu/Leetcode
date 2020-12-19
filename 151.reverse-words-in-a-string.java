/*
 * @lc app=leetcode id=151 lang=java
 *
 * [151] Reverse Words in a String
 */
class Solution {
    //https://www.cnblogs.com/grandyang/p/4606676.html
    // public String reverseWords(String s) {
    //     if(s.length() == 0)
    //         return s;
    //     int i = 0, j = i;
    //     StringBuilder res = new StringBuilder();
    //     while (j <= s.length()) {
    //         while (i < s.length() && s.charAt(i) == ' ')
    //             ++i;
    //         j = i;
    //         while (j < s.length() && s.charAt(j) != ' ')
    //             ++j;
    //         if (i == s.length())
    //             break;
    //         res.append(" ").append(reverse(s.substring(i, j)));
    //         i = j;
    //     }
    //     return reverse(res.toString()).trim();
    // }

    // private String reverse(String str) {
    //     str = new StringBuilder(str).reverse().toString();
    //     return str;
    // }

    // public String reverseWords(String s) {
    //     //split(" +")表示以多个" "为分隔符
    //     String[] words = s.trim().split(" +");
    //     Collections.reverse(Arrays.asList(words));
    //     return String.join(" ", words);
    // }


    public String reverseWords(String s) {
        StringBuilder res = new StringBuilder();
        //"\\s+"为一个正则表达式，\\s表示空格，+表示可以有一个或多个空格字符
        String[] words = s.split("\\s+");
        for(int i = words.length - 1; i > 0; --i){
            res.append(words[i]).append(" ");
        }
        return res.append(words[0]).toString();
    }
}

