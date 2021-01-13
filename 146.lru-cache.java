/*
 * @lc app=leetcode id=146 lang=java
 *
 * [146] LRU Cache
 */
class LRUCache {

    // solution1 利用java内置的LinkedHashMap
    // 在HashMap的每个位置用LinkedList处理冲突元素
    // 再通过双向链表将所有元素有顺序地串联起来
    // Map<Integer, Integer> map = new LinkedHashMap<>();
    // int limit;

    // public LRUCache(int capacity) {
    // limit = capacity;
    // }

    // public int get(int key) {
    // if (!map.containsKey(key))
    // return -1;
    // int val = map.get(key);
    // //取哈希表中key对应位置的链表头部节点，其存放的是最久访问的元素或者最先插入元素
    // map.remove(key);
    // //尾部是最近访问的或最近插入的元素
    // //调整位置，放置在该链表的尾部
    // map.put(key, val);
    // return val;
    // }

    // public void put(int key, int value) {
    // if (map.containsKey(key))
    // map.remove(key);
    // //达到容量上限，移除最久未访问的节点，双向链表的头部节点
    // if (map.size() == limit)
    // map.remove(map.keySet().iterator().next());
    // map.put(key, value);
    // }

    // Solution2
    // 核心思想:
    // 构造一个双向链表Node类，再利用HashMap将key与每个节点映射
    // 标记head和tail两个节点，head.next表示最近访问的节点
    // tail.prev表示最近最久未访问节点
    private class Node {
        int key;
        int val;
        Node prev;
        Node next;

        Node(int key, int val) {
            this.key = key;
            this.val = val;
            this.prev = null;
            this.next = null;
        }
    }

    private Node head, tail;
    private int limit;
    private Map<Integer, Node> map;

    public LRUCache(int capacity) {
        limit = capacity;
        map = new HashMap<>();
        // 创建head和tail节点
        head = new Node(0, 0);
        tail = new Node(-1, -1);
        // 构建双向链表
        head.next = tail;
        tail.prev = head;
    }

    public int get(int key) {
        if (!map.containsKey(key)) {
            return -1;
        }
        Node node = map.get(key);
        // 将key对应的节点从双向链表中删除
        removeNode(node);
        // 再将之放到head.next
        setHead(node);
        return node.val;
    }

    public void put(int key, int value) {
        // 判断是否已存在该key
        if (map.containsKey(key)) {
            Node old = map.get(key);
            removeNode(old);
            // 更新值
            old.val = value;
            // 再放到head.next;
            setHead(old);
            return;
        }
        // 未存在，再判断容量
        if (map.size() >= limit) {
            // 取最近最久未访问元素
            int tmp = tail.prev.key;
            // 先从hashmap中去除该元素
            map.remove(tail.prev.key);
            // 删除该元素对应的节点
            removeNode(tail.prev);
        }
        // 构建新节点
        Node node = new Node(key, value);
        // 放在head.next，最近访问
        setHead(node);
        // 更新hashmap
        map.put(key, node);
    }

    private void removeNode(Node node) {
        // 双向链表中只有一个元素，不需要移动到head.next
        if (node.prev == head && node.next == tail)
            return;

        node.prev.next = node.next;
        node.next.prev = node.prev;
        node.prev = null;
        node.next = null;
    }

    private void setHead(Node node) {
        node.next = head.next;
        node.prev = head;
        head.next = node;
        node.next.prev = node;
    }

}
/**
 * Your LRUCache object will be instantiated and called as such: LRUCache obj =
 * new LRUCache(capacity); int param_1 = obj.get(key); obj.put(key,value);
 */
