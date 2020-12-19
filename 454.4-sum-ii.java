import java.util.Iterator;
import java.util.Map;

import com.sun.corba.se.impl.encoding.OSFCodeSetRegistry.Entry;

/*
 * @lc app=leetcode id=454 lang=java
 *
 * [454] 4Sum II
 */
class Solution {
    public int fourSumCount(int[] A, int[] B, int[] C, int[] D) {
        int n = A.length;
        Map<Integer, Integer> m = new HashMap<>();
        for(int i = 0; i < n; ++i){
            for(int j = 0; j < n; ++j){
                int t = A[i] + B[j];
                m.put(t, m.getOrDefault(t, 0) + 1);
            }
        }
        
        int res = 0;
        for(int i = 0; i < n; ++i){
            for(int j = 0; j < n; ++j){
                int target = -1 * (C[i] + D[j]);
                res += m.getOrDefault(target, 0);
            }
        }
        return res;
    }
}

