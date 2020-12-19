import java.util.Arrays;

/*
 * @lc app=leetcode id=43 lang=java
 *
 * [43] Multiply Strings
 */
class Solution {
    // public String multiply(String num1, String num2) {
    //     if (num1.isEmpty() || num2.isEmpty())
    //         return "";
    //     if(num1.equals("0") || num2.equals("0"))
    //         return "0";
    //     int len1 = num1.length(), len2 = num2.length();
    //     String[] res = new String[len1];
    //     Arrays.fill(res, "");
    //     for (int i = len1 - 1; i >= 0; --i) {
    //         StringBuilder tmp = new StringBuilder("");
    //         if (num1.charAt(i) == '0') {
    //             tmp.append(0);
    //             continue;
    //         }

    //         for (int k = len1 - 1 - i; k > 0; --k)
    //             tmp.append(0);
    //         int j = len2 - 1, carry = 0;
    //         while (j >= 0 || carry != 0) {
    //             int num = (num1.charAt(i) - '0') * (j >= 0 ? num2.charAt(j) - '0' : 0) + carry;
    //             carry = num / 10;
    //             num = num % 10;
    //             tmp.append(num);
    //             --j;
    //         }
    //         res[i] = tmp.toString();
    //     }

    //     StringBuilder sum = addN(res, 0, len1 - 1);
    //     return sum.reverse().toString();
    // }

    // private StringBuilder addN(String[] nums, int l, int r) {
    //     if(l > r)
    //         return new StringBuilder();
    //     if(l == r)
    //         return new StringBuilder(nums[l]);
    //     if(r == l + 1)
    //         return addTwo(nums[l], nums[r]);
    //     int mid = l + ((r - l) >> 1);
    //     StringBuilder left = addN(nums, l, mid);
    //     StringBuilder right = addN(nums, mid + 1, r);
    //     return addTwo(left.toString(), right.toString());
    // }

    // private StringBuilder addTwo(String s1, String s2){
    //     StringBuilder res = new StringBuilder();
    //     int len1 = s1.length(), len2 = s2.length();
    //     int carry = 0, i = 0, j = 0;
    //     while (i < len1 || j < len2 || carry != 0) {
    //         int sum = (i < len1 ? s1.charAt(i) - '0' : 0) + (j < len2 ? s2.charAt(j) - '0' : 0) + carry;
    //         carry = sum / 10;
    //         sum = sum % 10;
    //         res.append(sum);
    //         ++i;
    //         ++j;
    //     }
    //     return res;
    // }


    //https://www.cnblogs.com/grandyang/p/4395356.html
    public String multiply(String num1, String num2) {
        //两数相乘得到的积的长度<=两数长度之和
        StringBuilder res = new StringBuilder("");
        int m = num1.length(), n = num2.length();
        //vals存放各位相乘的积
        int[] vals = new int[m + n];
        //num1中位置为i的数字乘以num2中位置为j的数字，
        //得到的两位数字的位置为i+j,i+j+1
        for (int i = m - 1; i >= 0; --i) {
            for (int j = n - 1; j >= 0; --j) {
                int mul = (num1.charAt(i) - '0') * (num2.charAt(j) - '0');
                //p1为高位，p2为低位
                int p1 = i + j, p2 = i + j + 1;
                int sum = mul + vals[p2];
                vals[p1] += sum / 10;
                vals[p2] = sum % 10;
            }
        }

        for (int val : vals) {
            //跳过vals中的leading zeros
            if (!(res.length() == 0 && val == 0))
                res.append(val);
        }
        return res.length() == 0 ? "0" : res.toString();
    }

}
