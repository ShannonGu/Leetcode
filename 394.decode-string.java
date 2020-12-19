import java.util.Stack;

/*
 * @lc app=leetcode id=394 lang=java
 *
 * [394] Decode String
 */
class Solution {
    public String decodeString(String s) {
        //利用两个栈
        if(s.isEmpty())
            return "";
        //一个栈存放数字
        Stack<Integer> sNum = new Stack<>();
        //另一个栈存放每对[]中的字符
        Stack<StringBuilder> sStr = new Stack<>();

        int sum = 0;
        StringBuilder res = new StringBuilder("");

        for (char c : s.toCharArray()) {

            if (c >= '0' && c <= '9')
                //遇到数字就计算整串数字的大小
                sum = sum * 10 + (c - '0');
            else if (c == '[') {
                //遇到'['就将之前遍历到的数字和字符串分别放入栈中
                sNum.push(sum);
                sStr.push(res);

                //然后重新归0
                //下一层 '[]' 中的数字和字符串
                sum = 0;
                res = new StringBuilder("");
            } else if (c == ']') {
                //遇到 ']',就开始处理字符串
                //弹出 离当前'[]'最近的一个数字
                int num = sNum.pop();

                //top表示当前 '[]'中的外面一层的 '[]'中的字符串
                //要把他们拼接起来,
                StringBuilder top = sStr.peek();
                for (int i = 0; i < num; ++i) {
                    top.append(res);
                }

                //为拼接下一层 '[]'做准备
                res = sStr.pop();
            } else
                res.append(c);
        }
        return res.toString();
    }


    // private int i = 0;

    // public String decodeString(String s) {
    //     return decode(s.toCharArray());
    // }
    
    // private String decode(char[] s) {
    //     StringBuilder res = new StringBuilder("");
    //     //']'是每一层递归结束的标志
    //     while (i < s.length && s[i] != ']') {
    //         // 每一次递归,字符串总是从数字开始;
    //         // 如果不是数字的话，肯定是字符,则将该字符加到这一次递归的临时字符串;
    //         if (s[i] >= '0' && s[i] <= '9') {
    //             // 第一个字符是数字
    //             // 记录这一次递归需要重复的次数;
    //             int sum = 0;

    //             //不需要添加'i < s.length()'因为字符串不会出现数字结尾的情况;
    //             while (s[i] >= '0' && s[i] <= '9') {
    //                 sum = sum * 10 + (s[i] - '0');
    //                 ++i;
    //             }

    //             //跳过'['
    //             ++i;
    //             //记录与当前sum对应的字符串;
    //             String tmp = decode(s);
    //             //跳过']'
    //             ++i;
    //             for (int j = 0; j < sum; ++j) {
    //                 res.append(tmp);
    //             }

    //         } else {
    //             res.append(s[i]);
    //             ++i;
    //         }
    //     }
    //     return res.toString();
    // }
}
