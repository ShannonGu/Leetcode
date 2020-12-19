import java.util.Map;

/*
 * @lc app=leetcode id=525 lang=java
 *
 * [525] Contiguous Array
 */
class Solution {
    //此题和523,560类似，都是将累加和与数的下标建立映射
    //用来求解子数组是否满足某种条件
    //要注意hash table的应用
    public int findMaxLength(int[] nums) {
        //根据题意如果存在相同数量的0和1的子数组
        //那么该子数组的和等于1的个数
        //可以将0看做-1，这样该子数组的和就为0了
        //将改造后的累加和与每个数的位置建立映射
        Map<Integer, Integer> map = new HashMap<>();
        //注意初始化
        map.put(0, -1);
        int sum = 0, res = 0;
        for (int i = 0; i < nums.length; ++i) {
            sum += nums[i] == 1 ? 1 : -1;

            //若map此前存在sum的映射
            //说明子数组[map.get(sum),i]的和是0;
            //说明-1和1相同，也即0和1相同
            if (map.containsKey(sum)) {
                res = Math.max(res, i - map.get(sum));
            } else {
                //否则将该累加和放入map中
                map.put(sum, i);
            }
        }
        return res;
    }

    //一道与此题类似的付费题目
    // http://206.81.6.248:12306/leetcode/maximum-size-subarray-sum-equals-k/description

    // public int findMaxLength(int[] nums, int k) {
    //     Map<Integer, Integer> m = new HashMap<>();
    //     m.put(0, -1);
    //     int sum = 0, res = 0;
    //     for (int i = 0; i < nums.length; ++i) {
    //         sum += nums[i];
    //         if (m.containsKey(sum - k)) {
    //             res = Math.max(res, i - m.get(sum - k));
    //         } else
    //             m.put(sum, i);
    //     }
    //     return res;
    // }

}

