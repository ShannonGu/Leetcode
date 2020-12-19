class Solution{
    // https://www.cnblogs.com/grandyang/p/5203228.html
    private int res = 0;
    public int strobogrammaticInRange(String low, String high){
        for(int i = low.length(); i <= high.length(); i++){
            find(low, high, "", i);
            find(low, high, "0", i);
            find(low, high, "1", i);
            find(low, high, "8", i);
        }
        return res;
    }

    private void find(String low, String high, String str, int len){
        if(str.length() >= len){
            if(str.length() != len || (len != 1 && str.charAt(0) == '0'))
                return;
            if((len == low.length() && str.compareTo(low) < 0) || (len == high.length() && str.compareTo(high) > 0)){
                return;
            }
            ++res;
        }

        find(low, high, "0" + str + "0", len);
        find(low, high, "1" + str + "1", len);
        find(low, high, "6" + str + "9", len);
        find(low, high, "8" + str + "8", len);
        find(low, high, "9" + str + "6", len);
    }
}