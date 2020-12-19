/*
 * @lc app=leetcode id=273 lang=java
 *
 * [273] Integer to English Words
 */
class Solution {
    //https://leetcode.com/problems/integer-to-english-words/discuss/70625/My-clean-Java-solution-very-easy-to-understand
    private final String[] LESS_THAN_20 = { "", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine",
            "Ten", "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen", "Eighteen",
            "Nineteen" };
    private final String[] TENS = { "", "Ten", "Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy", "Eighty",
            "Ninety" };
    private final String[] OVERHUNDRED = {"","Thousand","Million","Billion"};

    public String numberToWords(int num) {
        if (num == 0)
            return "Zero";
        String words = "";
        int cnt = 0;
        while (num > 0) {
            //三位不全是0
            if (num % 1000 != 0) {
                //计算每三位代表的数
                //cnt表示第几个三位数，指向OVERHUNDERD中的量级
                words = helper(num % 1000) + OVERHUNDRED[cnt] + " " + words;
            }
            ++cnt;
            num /= 1000;
        }
        return words.trim();
    }
    
    private String helper(int num) {
        if(num == 0)
            return "";
        else if(num < 20)
            return LESS_THAN_20[num] + " ";
        else if(num < 100)
            return TENS[num / 10] + " " + helper(num % 10);
        else
            return LESS_THAN_20[num / 100] + " Hundred " + helper(num % 100);
    }
}

