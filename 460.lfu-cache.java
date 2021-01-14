/*
 * @lc app=leetcode id=460 lang=java
 *
 * [460] LFU Cache
 */

//https://medium.com/algorithm-and-datastructure/lfu-cache-in-o-1-in-java-4bac0892bdb3
class LFUCache {

    // 最低频率和cache容量
    int minFreq, cap;

    // key->val
    Map<Integer, Integer> vals;

    // key->freq
    Map<Integer, Integer> freqs;

    // freq->linkedHashSet
    // LinkedHashSet可以按照插入顺序进行迭代;
    // 所以最先迭代到的即对应频率下映射的HashSet中最久未使用的
    // 用以在cache容量不够的情况下，按照LFU置换相应元素;
    Map<Integer, LinkedHashSet<Integer>> lists;

    // 构造函数
    public LFUCache(int capacity) {
        this.cap = capacity;
        this.minFreq = 0;
        this.vals = new HashMap<>();
        this.freqs = new HashMap<>();
        this.lists = new HashMap<>();
    }

    public int get(int key) {
        // 不存在
        if (!vals.containsKey(key)) {
            return -1;
        }
        // 存在该元素,
        // 则更新该元素的频率和相应频率下的位置
        update(key);

        // 返回该key对应的value;
        return vals.get(key);
    }

    public void put(int key, int value) {
        // 注意容量为0，不能存放;
        if (cap == 0)
            return;

        // 若cache中已有该key
        if (vals.containsKey(key)) {
            // 更新该key对应的value;
            vals.put(key, value);
            // 更新频率和相应频率下的位置;
            update(key);
            return;
        }

        // 若不存在该key
        // 且此时cache中存放的元素已经达到容量上限;
        // 需要置换
        if (vals.size() == cap) {
            // 取出最少使用的相应的HashSet,在HashSet中取出第一个元素
            // 即频率相同的情况下最久未使用的元素
            int evict = lists.get(minFreq).iterator().next();
            // 去除该元素;
            lists.get(minFreq).remove(evict);
            vals.remove(evict);
            freqs.remove(evict);
        }

        // 将新元素放入;
        vals.put(key, value);
        // 更新对应频率
        freqs.put(key, 1);
        // 最小频率置为1,即最新加入的元素仅使用一次
        minFreq = 1;
        if (!lists.containsKey(minFreq))
            lists.put(minFreq, new LinkedHashSet<>());
        lists.get(minFreq).add(key);
    }

    // 更新方法
    private void update(int key) {
        int freq = freqs.get(key);
        // 将该元素的频率加1;
        freqs.put(key, freq + 1);
        // 在对应频率的HashSet中删除此元素
        lists.get(freq).remove(key);

        // 若此元素之前是最少使用的，且是唯一的最久未使用的元素;
        // 则更新最小使用的频率;
        if (freq == minFreq && lists.get(freq).size() == 0)
            ++minFreq;

        // 若当前key更新频率没有对应的LinkedHashSet为空;
        // 则新建当前频率的LinkedHashSet
        if (!lists.containsKey(freq + 1)) {
            lists.put(freq + 1, new LinkedHashSet<>());
        }
        // 将当前key加入当前频率的LinkedHashSet中;
        lists.get(freq + 1).add(key);
    }
}

/**
 * Your LFUCache object will be instantiated and called as such: LFUCache obj =
 * new LFUCache(capacity); int param_1 = obj.get(key); obj.put(key,value);
 */
