import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

/*
 * @lc app=leetcode id=380 lang=java
 *
 * [380] Insert Delete GetRandom O(1)
 */

// @lc code=start
class RandomizedSet {

    //https://www.jiuzhang.com/solution/insert-delete-getrandom-o1
    private Map<Integer, Integer> m;
    private List<Integer> nums;
    private Random rand;
    /** Initialize your data structure here. */
    public RandomizedSet() {
        m = new HashMap<>();
        nums = new ArrayList<>();
        rand = new Random();
    }
    
    /** Inserts a value to the set. Returns true if the set did not already contain the specified element. */
    public boolean insert(int val) {
        if(m.containsKey(val))
            return false;
        int pos = nums.size();
        nums.add(val);
        m.put(val, pos);
        return true;
    }
    
    /** Removes a value from the set. Returns true if the set contained the specified element. */
    public boolean remove(int val) {
        if(!m.containsKey(val))
            return false;
        //将当前要remove的元素与最后一个元素置换
        //再将最后一个位置的元素remove掉
        //最后更新哈希表
        int len = nums.size();
        int pos = m.get(val);
        //val不是最后一个元素
        if (pos < len - 1) {
            int last = nums.get(len - 1);
            nums.set(pos, last);
            m.put(last, pos);
        }
        nums.remove(len - 1);
        m.remove(val);
        return true;
    }
    
    /** Get a random element from the set. */
    public int getRandom() {
        int t = rand.nextInt(nums.size());
        return nums.get(t);
    }
}

/**
 * Your RandomizedSet object will be instantiated and called as such:
 * RandomizedSet obj = new RandomizedSet();
 * boolean param_1 = obj.insert(val);
 * boolean param_2 = obj.remove(val);
 * int param_3 = obj.getRandom();
 */
// @lc code=end

