import java.util.Map;
import java.util.TreeMap;

/*
 * @lc app=leetcode id=352 lang=java
 *
 * [352] Data Stream as Disjoint Intervals
 */
class SummaryRanges {
    //需要用到二叉搜索树
    //借助TreeMap
    TreeMap<Integer, Integer> left, right;

    /** Initialize your data structure here. */
    public SummaryRanges() {
        //left为区间左端点到右端点的映射，与区间左端点相对应的右端点
        left = new TreeMap<>();
        //right为区间右端点到左端点的映射,与区间右端点相对应的左端点
        right = new TreeMap<>();
    }
    
    public void addNum(int val) {
        //首先判断val是否属于某个区间
        //利用left.floorEntry()方法找到<=val的最大的区间左端点
        Map.Entry<Integer, Integer> me = left.floorEntry(val);
        //若该端点存在，并且所属区间的右端点>=val
        //说明val在该区间内,不需要更新区间
        if (me != null && val <= me.getValue()) {
            return;
        }
        
        //否则，val不属于任一区间
        //此时需要判断val与val-1,val+1所属区间的关系
        
        //查找val-1所属区间的左端点
        Integer l = right.get(val - 1);
        //查找val+1所属区间的右端点
        Integer r = left.get(val + 1);
        
        //若两者都不空,说明val-1和val+1所属区间都存在，且中间相隔val
        //于是将他们合并
        if (l != null && r != null) {
            left.remove(val + 1);
            right.remove(val - 1);
            left.put(l, r);
            right.put(r, l);
        } else if (l != null) {
            //若只有val-1区间存在
            //则val向左合并
            right.remove(val - 1);
            left.put(l, val);
            right.put(val, l);
        } else if (r != null) {
            //val向右合并
            left.remove(val + 1);
            left.put(val, r);
            right.put(r, val);
        } else {
            //val-1和val+1都不存在
            //val单独一个区间
            left.put(val, val);
            right.put(val, val);
        }
    }
    
    public int[][] getIntervals() {
        int[][] res = new int[left.size()][2];
        int idx = 0;
        //依次存储每个区间的端点
        for (Map.Entry<Integer, Integer> num : left.entrySet()) {
            res[idx][0] = num.getKey();
            res[idx++][1] = num.getValue();
        }
        return res;
    }
}

/**
 * Your SummaryRanges object will be instantiated and called as such:
 * SummaryRanges obj = new SummaryRanges();
 * obj.addNum(val);
 * int[][] param_2 = obj.getIntervals();
 */

