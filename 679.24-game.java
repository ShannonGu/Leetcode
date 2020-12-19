import java.util.Arrays;

/*
 * @lc app=leetcode id=679 lang=java
 *
 * [679] 24 Game
 */
class Solution {
    private boolean res = false;
    private double eps = 0.001;
    private List<Double> arr;

    public boolean judgePoint24(int[] nums) {
        arr = new ArrayList<>();
        for (int num : nums) {
            arr.add((double)num);
        }
        helper();
        return res;
    }
    
    private void helper() {
        if(res)
            return;
        if (arr.size() == 1) {
            if (Math.abs(arr.get(0) - 24) < eps) {
                res = true;
            }
            return;
        }
        for (int i = 0; i < arr.size(); ++i) {
            for (int j = 0; j < i; ++j) {
                double p = arr.get(i), q = arr.get(j);
                List<Double> t = new ArrayList<>(Arrays.asList(p + q, p * q, p - q, q - p));
                
                if (p > eps)
                    t.add(q / p);
                if (q > eps)
                    t.add(p / q);

                arr.remove(i);
                arr.remove(j);
                for (double e : t) {
                    arr.add(e);
                    helper();
                    arr.remove(arr.size() - 1);
                }
                arr.add(j, q);
                arr.add(i, p);
            }
        }
    }
    
}

