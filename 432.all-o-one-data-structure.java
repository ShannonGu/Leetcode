import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/*
 * @lc app=leetcode id=432 lang=java
 *
 * [432] All O`one Data Structure
 */

//https://leetcode.com/problems/all-oone-data-structure/discuss/91383/An-accepted-JAVA-solution-detailed-explanation.(HashMap-%2B-double-linked-list)
class AllOne {
    // 使用双向链表加hashmap
    // 双向链表上的每个节点上有hashset用于存放相同频率的所有key
    // 队头频率最小， 队尾频率最大
    static class Node {// 双向链表
        int freq;
        Set<String> keys;// 用于存放相同频率的key
        Node prev;
        Node next;

        Node(int freq) {
            this.freq = freq;
            this.keys = new HashSet<>();
        }

        void addKey(String key) {
            this.keys.add(key);
        }

        void removeKey(String key) {
            this.keys.remove(key);
        }
    }

    // 队头队尾两个dummy节点
    // 其中head.next为频率最小的key所在节点
    // tail.prev为频率最大的key所在节点
    private Node head, tail;
    // key与freq进行映射
    private Map<String, Integer> keyFreqs;
    // freq与该freq代表的节点进行映射,节点上hashset存放key
    private Map<Integer, Node> lists;

    /** Initialize your data structure here. */
    public AllOne() {
        // 两个dummy节点
        head = new Node(-1);
        tail = new Node(0);
        // 双向链表
        head.next = tail;
        tail.prev = head;
        keyFreqs = new HashMap<>();
        lists = new HashMap<>();
    }

    /** Inserts a new key <Key> with value 1. Or increments an existing key by 1. */
    public void inc(String key) {
        int oldF = keyFreqs.getOrDefault(key, 0);
        int freq = oldF + 1;
        // 更新该key的频率
        keyFreqs.put(key, freq);
        // 该频率没有对应的节点就新建一个
        lists.putIfAbsent(freq, new Node(freq));
        Node node = lists.get(freq);
        // 将该key加入
        node.addKey(key);

        // 记录oldFreq对应的节点
        Node oldNode = head;
        // key对应oldF>0说明key此前是存在的， 并且不是head
        if (oldF > 0) {
            oldNode = lists.get(oldF);
            // 对应的oldNode删除此key
            oldNode.removeKey(key);
        }

        // 如果freq对应的节点中只有一个key,说明是一个新节点,则将之插入到链表中
        if (node.keys.size() == 1) {
            node.next = oldNode.next;
            node.prev = oldNode;
            oldNode.next.prev = node;
            oldNode.next = node;
        }

        // 如果oldNode不是head(辅助节点),并且去除一个当前key后为空
        // 则删去oldNode
        if (oldNode != head && oldNode.keys.isEmpty()) {
            oldNode.prev.next = node;
            node.prev = oldNode.prev;
            oldNode.prev = null;
            oldNode.next = null;
        }
    }

    /**
     * Decrements an existing key by 1. If Key's value is 1, remove it from the data
     * structure.
     */
    public void dec(String key) {
        // 不含有此key
        if (!keyFreqs.containsKey(key))
            return;

        int oldF = keyFreqs.get(key);
        // 对应频率为1,移除此key
        if (oldF == 1) {
            keyFreqs.remove(key);
        }

        int f = oldF - 1;
        Node node = head;
        if (f > 0) {
            keyFreqs.put(key, f);
            // 如果f没有对应的节点则新建一个
            lists.putIfAbsent(f, new Node(f));
            node = lists.get(f);
            // 加入key
            node.addKey(key);
        }

        Node oldNode = lists.get(oldF);
        // 原节点删除该key
        oldNode.removeKey(key);
        // node为新节点,则加入到链表中
        if (node.keys.size() == 1) {
            node.next = oldNode;
            node.prev = oldNode.prev;
            oldNode.prev.next = node;
            oldNode.prev = node;
        }

        // oldNode不是辅助节点tail, 并且删去一个key后为空
        // 则将oldNode从链表中删除
        if (oldNode != tail && oldNode.keys.isEmpty()) {
            oldNode.next.prev = node;
            node.next = oldNode.next;
            oldNode.next = null;
            oldNode.prev = null;
        }
    }

    /** Returns one of the keys with maximal value. */
    public String getMaxKey() {
        // 双向链表除了head, tail没有其他节点
        if (tail.prev.keys.isEmpty())
            return "";
        // tail前一个节点代表频率最大的key所在的节点
        return tail.prev.keys.iterator().next();
    }

    /** Returns one of the keys with Minimal value. */
    public String getMinKey() {
        if (head.next.keys.isEmpty())
            return "";
        // head后一个节点代表频率最小的key所在的节点
        return head.next.keys.iterator().next();
    }
}

/**
 * Your AllOne object will be instantiated and called as such: AllOne obj = new
 * AllOne(); obj.inc(key); obj.dec(key); String param_3 = obj.getMaxKey();
 * String param_4 = obj.getMinKey();
 */
