/*
 * @lc app=leetcode id=929 lang=java
 *
 * [929] Unique Email Addresses
 */
class Solution {
    public int numUniqueEmails(String[] emails) {
        if(emails.length == 0)
            return 0;
        Set<String> addrs = new HashSet<>();
        for (String email : emails) {
            StringBuilder str = new StringBuilder();
            String[] tmp = email.split("\\@");
            for (int i = 0; i < tmp[0].length(); ++i) {
                if (tmp[0].charAt(i) == '.')
                    continue;
                else if (tmp[0].charAt(i) == '+')
                    break;
                else
                    str.append(tmp[0].charAt(i));
            }
            str.append('@').append(tmp[1]);
            addrs.add(str.toString());
        }
        return addrs.size();
    }
}

