import java.util.List;

/*
 * @lc app=leetcode id=842 lang=java
 *
 * [842] Split Array into Fibonacci Sequence
 */
class Solution {
    //https://www.cnblogs.com/grandyang/p/10434771.html
    private List<Integer> res;

    public List<Integer> splitIntoFibonacci(String S) {
        if (S.length() < 3)
            return new ArrayList<>();
        res = new ArrayList<>();
        List<Integer> out = new ArrayList<>();
        helper(S, 0, out);
        return res;
    }

    private void helper(String S, int st, List<Integer> out) {
        if(!res.isEmpty())
            return;
        if (st >= S.length() && out.size() >= 3) {
            res.addAll(out);
            return;
        }

        for (int i = st + 1; i <= S.length(); ++i) {
            String cur = S.substring(st, i);
            if((cur.length() > 1 && cur.charAt(0) == '0') || cur.length() > 10)
                break;
            long num = Long.valueOf(cur);
            int len = out.size();
            if(num > Integer.MAX_VALUE)
                break;
            if(len >= 2 && num != out.get(len - 1) + out.get(len - 2))
                continue;
            out.add((int)num);
            helper(S, i, out);
            out.remove(out.size() - 1);
        }
    }

    // private String add(String num1, String num2) {
    //     StringBuilder res = new StringBuilder("");
    //     int carry = 0, i = num1.length() - 1, j = num2.length() - 1;
    //     while (i >= 0 || j >= 0 || carry != 0) {
    //         int sum = (i >= 0 ? num1.charAt(i--) - '0' : 0) + (j >= 0 ? num2.charAt(j--) - '0' : 0) + carry;
    //         carry = sum / 10;
    //         sum = sum % 10;
    //         res.append(sum);
    //     }
    //     return res.reverse().toString();
    // }
}
