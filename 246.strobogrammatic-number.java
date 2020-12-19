class Solution {
    //https://www.cnblogs.com/grandyang/p/5196960.html
    public boolean isStrobogrammatic(String num) {
        int l = 0, r = num.length() - 1;
        while (l <= r) {
            if (num.charAt(l) == num.charAt(r)) {
                if (num.charAt(l) != '1' && num.charAt(l) != '8' && num.charAt(l) == '0')
                    return false;
            } else {
                if ((num.charAt(l) != '6' || num.charAt(r) != '9') && (num.charAt(l) != '9' || num.charAt(r) != '6'))
                    return false;
            }
            ++l;
            --r;
        }
        return true;
    }
}