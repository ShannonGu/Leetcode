import java.util.List;
import java.util.Map;
import java.util.Random;

/*
 * @lc app=leetcode id=381 lang=java
 *
 * [381] Insert Delete GetRandom O(1) - Duplicates allowed
 */

// @lc code=start



//https://www.jiuzhang.com/solution/insert-delete-getrandom-o1-duplicates-allowed
class numAndIdx {
    //将每个数和其在nums中的索引组成的索引列表的位置封装成一个数据结构
    int num;
    int idx;

    numAndIdx(int num, int idx) {
        this.num = num;
        this.idx = idx;
    }
}

class RandomizedCollection {

    private List<numAndIdx> nums;
    //利用一个hashmap将每个数和其在nums中的索引组成的索引列表映射起来
    private Map<Integer, List<Integer>> m;
    private Random rand;
    /** Initialize your data structure here. */
    public RandomizedCollection() {
        nums = new ArrayList<>();
        m = new HashMap<>();
        rand = new Random();
    }
    
    /** Inserts a value to the collection. Returns true if the collection did not already contain the specified element. */
    public boolean insert(int val) {
        boolean alreadyExists = m.containsKey(val);

        //如果当前val不存在
        if (!alreadyExists) {
            //在hashmap中创建对应的列表存放该val的索引
            m.put(val, new ArrayList<>());
        }

        //取出val对应的索引列表
        List<Integer> indices = m.get(val);
        //添加一个索引,val添加到nums最后
        indices.add(nums.size());
        //新建一个节点，存入nums中，起初的索引为索引列表中最后一个位置
        nums.add(new numAndIdx(val, indices.size() - 1));
        return !alreadyExists;
    }
    
    /** Removes a value from the collection. Returns true if the collection contained the specified element. */
    public boolean remove(int val) {
        if(!m.containsKey(val))
            return false;

        //取出当前val对应的索引列表
        List<Integer> indices = m.get(val);
        //remove最后一个，将列表最后一个val对应的在nums中的索引取出
        int idxToReplace = indices.get(indices.size() - 1);

        //将nums列表中的最后一个节点取出，
        numAndIdx last = nums.get(nums.size() - 1);
        //放置到要被删除的val的位置
        nums.set(idxToReplace, last);
        //然后删除最后一个节点
        nums.remove(nums.size() - 1);

        //更新last节点中数在nums中的索引
        //last节点对应的数
        int valToReplace = last.num;
        m.get(valToReplace).set(last.idx, idxToReplace);

        //在val对应的索引列表remove最后一个索引
        indices.remove(indices.size() - 1);
        //若indices此时为空，说明nums中已经没有val这个数了
        if (indices.isEmpty()) {
            m.remove(val);
        }
        return true;
    }
    
    /** Get a random element from the collection. */
    public int getRandom() {
        int t = rand.nextInt(nums.size());
        return nums.get(t).num;
    }
}

/**
 * Your RandomizedCollection object will be instantiated and called as such:
 * RandomizedCollection obj = new RandomizedCollection();
 * boolean param_1 = obj.insert(val);
 * boolean param_2 = obj.remove(val);
 * int param_3 = obj.getRandom();
 */
// @lc code=end

