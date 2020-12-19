/*
 * @lc app=leetcode id=306 lang=java
 *
 * [306] Additive Number
 */
class Solution {
    //https://www.cnblogs.com/grandyang/p/4974115.html
    // boolean res = false;

    // public boolean isAdditiveNumber(String num) {
    //     List<Long> out = new ArrayList<>();
    //     helper(num, 0, out);
    //     return res;
    // }

    // private void helper(String num, int st, List<Long> out) {
    //     if (res)
    //         return;
    //     if (st >= num.length()) {
    //         if (out.size() >= 3)
    //             res = true;
    //         return;
    //     }

    //     for (int i = 1; i <= num.length() - st; ++i) {
    //         String str = num.substring(st, st + i);
    //         if ((str.length() > 1 && str.charAt(0) == '0') || str.length() > 19)
    //             break;
    //         long curNum = Long.valueOf(str);
    //         int n = out.size();
    //         if (n >= 2 && curNum != out.get(n - 1) + out.get(n - 2))
    //             continue;
    //         out.add(curNum);
    //         helper(num, st + i, out);
    //         out.remove(out.size() - 1);
    //     }
    // }


    public boolean isAdditiveNumber(String num) {
        if(num.length() < 3)
            return false;
        //依次取两个数
        for (int i = 1; i <= num.length(); ++i) {
            String num1 = num.substring(0, i);
            if (num1.length() > 1 && num1.charAt(0) == '0')
                break;
            for (int j = i + 1; j <= num.length(); ++j) {
                String num2 = num.substring(i, j);
                if (num2.length() > 1 && num2.charAt(0) == '0')
                    break;
                if (helper(num.substring(j), num1, num2))
                    return true;
            }
        }
        return false;
    }
    
    private boolean helper(String num, String num1, String num2) {
        //leading zero
        if ((num1.length() > 1 && num1.charAt(0) == '0') || (num2.length() > 1 && num2.charAt(0) == '0'))
            return false;
        String sum = add(num1, num2);
        //剩下的直接相等
        if (num.equals(sum))
            return true;
        //前两项的和大于剩下的,或者不成Fibonacci
        if (sum.length() >= num.length() || !num.substring(0, sum.length()).equals(sum))
            return false;

        //sum == num1 + num2;
        return helper(num.substring(sum.length()), num2, sum);
    }
    
    //防止溢出,字符串方法大数相加
    private String add(String num1, String num2) {
        StringBuilder res = new StringBuilder("");
        int carry = 0;
        int i = num1.length() - 1, j = num2.length() - 1;
        while (i >= 0 || j >= 0 || carry != 0) {
            int sum = (i >= 0 ? num1.charAt(i--) - '0' : 0) + (j >= 0 ? num2.charAt(j--) - '0' : 0) + carry;
            carry = sum / 10;
            sum = sum % 10;
            res.append(sum);
        }
        return res.reverse().toString();
    }
}
