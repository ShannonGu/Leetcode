import java.util.List;

/*
 * @lc app=leetcode id=241 lang=java
 *
 * [241] Different Ways to Add Parentheses
 */
class Solution {
    //https://www.cnblogs.com/grandyang/p/4682458.html
    // public List<Integer> diffWaysToCompute(String input) {
    //     List<Integer> res = new ArrayList<>();
    //     for (int i = 0; i < input.length(); ++i) {
    //         if (input.charAt(i) == '+' || input.charAt(i) == '-' || input.charAt(i) == '*') {
    //             List<Integer> l = diffWaysToCompute(input.substring(0, i));
    //             List<Integer> r = diffWaysToCompute(input.substring(i + 1));
    //             for (int j : l) {
    //                 for (int k : r) {
    //                     if (input.charAt(i) == '+')
    //                         res.add(j + k);
    //                     else if (input.charAt(i) == '-')
    //                         res.add(j - k);
    //                     else
    //                         res.add(j * k);
    //                 }
    //             }
    //         }
    //     }
    //     if(res.isEmpty())
    //         res.add(Integer.valueOf(input));
    //     return res;
    // }


    

    private Map<String, List<Integer>> memo = new HashMap<>();

    public List<Integer> diffWaysToCompute(String input) {
        if(memo.containsKey(input))
            return memo.get(input);
        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < input.length(); ++i) {
            if (input.charAt(i) == '+' || input.charAt(i) == '-' || input.charAt(i) == '*') {
                List<Integer> l = diffWaysToCompute(input.substring(0, i));
                List<Integer> r = diffWaysToCompute(input.substring(i + 1));
                for (int j : l) {
                    for (int k : r) {
                        if (input.charAt(i) == '+')
                            res.add(j + k);
                        else if (input.charAt(i) == '-')
                            res.add(j - k);
                        else
                            res.add(j * k);
                    }
                }
            }
        }

        //说明input是一个数字
        if(res.isEmpty())
            res.add(Integer.valueOf(input));

        memo.put(input, res);
        return res;
    }
}
