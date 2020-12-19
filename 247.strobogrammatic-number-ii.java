import java.util.Arrays;

class Solution {
    //https://www.cnblogs.com/grandyang/p/5200919.html2
    public List<String> findStrobogrammatic(int n) {
        return find(n, n);
    }
    
    private List<String> find(int m, int n) {
        if(m == 0)
            return new ArrayList<>(Arrays.asList(""));
        if(m == 1)
            return new ArrayList<>(Arrays.asList("0", "1", "2"));

        List<String> tmp = find(m - 2, n);
        List<String> res = new ArrayList<>();
        for (String str : tmp) {
            if (m != n)
                res.add("0" + str + "0");
            res.add("1" + str + "1");
            res.add("6" + str + "9");
            res.add("8" + str + "8");
            res.add("9" + str + "6");
        }
        return res;
    }
}