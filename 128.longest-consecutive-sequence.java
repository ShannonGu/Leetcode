import java.util.Iterator;
import java.util.Map;

/*
 * @lc app=leetcode id=128 lang=java
 *
 * [128] Longest Consecutive Sequence
 */
class Solution {
    //Union-find思想
    public int longestConsecutive(int[] nums) {
        if(nums.length == 0)
            return 0;
        //首先利用hashmap将每个数与其下一个坐标映射起来
        Map<Integer, Integer> m = new HashMap<>();
        for (int i = 0; i < nums.length; ++i) {
            m.putIfAbsent(nums[i], i + 1);
        }

        //cnt用于记录每段连续seq的长度
        //res为全局最长长度
        int cnt = 0, res = 1;
        Iterator it = m.entrySet().iterator();
        //对哈希表每个数进行遍历
        while (it.hasNext()) {

            Map.Entry e = (Map.Entry) it.next();
            int val = (Integer) e.getValue();
            //如果对应value为0,说明包含key的这一段连续seq已经处理完毕
            //寻找下一段
            if (val == 0)
                continue;
            //否则更新当前这一段的长度
            ++cnt;
            //当前数
            int key = (Integer) e.getKey();
            //将其映射值置为0，表示处理过了
            m.put(key, 0);

            //向-1方向寻找与其连续的数
            while (m.containsKey(--key)) {
                //更新长度
                ++cnt;
                //更新处理标记
                m.put(key, 0);
            }

            //向+1方向寻找与其连续的数
            key = (Integer) e.getKey();
            while (m.containsKey(++key)) {
                ++cnt;
                m.put(key, 0);
            }

            //更新全局最长长度
            res = Math.max(res, cnt);
            
            //寻找下一段连续seq
            cnt = 0;
        }
        return res;
    }
}

