/*
 * @lc app=leetcode id=17 lang=java
 *
 * [17] Letter Combinations of a Phone Number
 */
class Solution {
    //https://leetcode.com/problems/letter-combinations-of-a-phone-number/solution/
    Map<String, String> phone = new HashMap<String, String>(){{
        put("2","abc");
        put("3","def");
        put("4","ghi");
        put("5","jkl");
        put("6","mno");
        put("7","pqrs");
        put("8","tuv");
        put("9","wxyz");
        }
    };
    List<String> res = new ArrayList<>();
    
    public List<String> letterCombinations(String digits) {
        if(digits.length() != 0)
            helper(digits, "");
        return res;
    }

    private void helper(String nextDigits, String tmp) {
        if (nextDigits.length() == 0) {
            res.add(tmp);
            return;
        }

        String digit = nextDigits.substring(0, 1);
        String letters = phone.get(digit);
        for (int i = 0; i < letters.length(); ++i) {
            String letter = phone.get(digit).substring(i, i + 1);
            helper(nextDigits.substring(1), tmp + letter);
        }
    }
}

