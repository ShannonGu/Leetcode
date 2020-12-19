/*
 * @lc app=leetcode id=468 lang=java
 *
 * [468] Validate IP Address
 */
class Solution {
    //https://www.cnblogs.com/grandyang/p/6185339.html
    public String validIPAddress(String IP) {
        int flag = 0;
        //先判断IP中是否有'.'
        for (char c : IP.toCharArray()) {
            if (c == '.') {
                flag = 1;
                break;
            }
        }
        if (flag == 1) {
            //IPV4
            return isValid4(IP) ? "IPv4" : "Neither";
        } else {
            return isValid6(IP) ? "IPv6" : "Neither";
        }
    }
    
    //判断IPv4
    private boolean isValid4(String ip) {
        String[] str = ip.split("\\.");
        //以"."开头或者结尾
        if (ip.startsWith(".") || ip.endsWith("."))
            // return "Neither";
            return false;
        //不是4段
        if (str.length != 4)
            // return "Neither";
            return false;
        for (String s : str) {
            //为""，或者首字符为0, 或者长度大于3
            if (s.isEmpty() || (s.length() > 1 && s.charAt(0) == '0') || s.length() > 3)
                // return "Neither";
                return false;
            for (char c : s.toCharArray()) {
                //出现非数字字符
                if (c < '0' || c > '9')
                    // return "Neither";
                    return false;
            }
            int num = Integer.parseInt(s);
            if (num < 0 || num > 255)
                // return "Neither";
                return false;
        }
        // return "IPv4";
        return true;
    }
    
    //判断IPv6
    private boolean isValid6(String ip) {
        String[] str = ip.split(":");
        //以":"开头或者结尾
        if(ip.startsWith(":") || ip.endsWith(":"))
            // return "Neither";
            return false;
        //不是8段
        if (str.length != 8)
            // return "Neither";
            return false;
        //对每段进行检查
        for (String s : str) {
            //为空或者长度>4
            if (s.isEmpty() || s.length() > 4)
                // return "Neither";
                return false;
            //每一段字符的组成情况
            for (char c : s.toCharArray()) {
                if (!(c >= '0' && c <= '9') && !(c >= 'a' && c <= 'f') && !(c >= 'A' && c <= 'F'))
                    // return "Neither";
                    return false;
            }
        }
        // return "IPv6";
        return true;
    }
}

