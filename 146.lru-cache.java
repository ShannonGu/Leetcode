/*
 * @lc app=leetcode id=146 lang=java
 *
 * [146] LRU Cache
 */
class LRUCache {

    // 兼具LinkedList和HashMap特性
    Map<Integer, Integer> map = new LinkedHashMap<>();
    int limit;

    public LRUCache(int capacity) {
        limit = capacity;
    }

    public int get(int key) {
        if (!map.containsKey(key))
            return -1;
        int val = map.get(key);
        //链表头部存放的是最久访问的节点或者最先插入的节点
        map.remove(key);
        //尾部是最近访问的或最近插入的节点
        //调整位置，放置在尾部
        map.put(key, val);
        return val;
    }

    public void put(int key, int value) {
        if (map.containsKey(key))
            map.remove(key);
        //达到容量上限，移除最久未访问的节点，链表的头部节点
        if (map.size() == limit)
            map.remove(map.keySet().iterator().next());
        map.put(key, value);
    }

    // //构建一个双向链表类;
    // class Node {
    //     int key;
    //     int val;
    //     //前驱
    //     Node prev;
    //     //后继
    //     Node next;

    //     //Node类构造函数
    //     public Node(int key, int val) {
    //         this.key = key;
    //         this.val = val;
    //         this.prev = null;
    //         this.next = null;
    //     }
    // }

    // //分别指向头尾两个结点;
    // Node head;
    // Node tail;

    // Map<Integer, Node> map;
    // int cap;

    // //LRUCache类构造函数;
    // public LRUCache(int capacity) {
    //     this.cap = capacity;
    //     this.map = new HashMap<>();
    // }

    // public int get(int key) {
    //     //cache中不存在当前元素;
    //     if (map.get(key) == null) {
    //         return -1;
    //     }

    //     //存在则将其取出放置到cache头部;
    //     Node tmp = map.get(key);
    //     //现在cache中去除该元素;
    //     removeNode(tmp);
    //     //然后放置到头部;
    //     setHead(tmp);
    //     return tmp.val;
    // }

    // public void put(int key, int value) {
    //     //若cache中存在该元素;
    //     //则更新其值,并放置到头部;
    //     if (map.containsKey(key)) {
    //         Node old = map.get(key);
    //         //更新值
    //         old.val = value;
    //         //先去除该元素;
    //         removeNode(old);
    //         //然后放置到头部;
    //         setHead(old);
    //     } else {
    //         //若不存在
    //         //则将其存入cache,并放置到头部;
    //         Node created = new Node(key, value);
    //         //若超过了cache的容量
    //         //去除LRU的元素,也即双向链表中的尾元素;
    //         if (map.size() >= cap) {
    //             //在HashMap中先取出该元素;
    //             map.remove(tail.key);
    //             removeNode(tail);
    //             setHead(created);
    //         } else {
    //             //没有超过cache的容量
    //             //则直接放置到头部;
    //             setHead(created);
    //         }
    //         map.put(key, created);
    //     }
    // }

    // //从双向链表中去除此结点;
    // private void removeNode(Node n) {
    //     //注意去除该节点后的前后结点的连接;
    //     if (n.prev != null) {
    //         n.prev.next = n.next;
    //     } else {
    //         //表示此时去除的n为头结点;
    //         //所以要更新头结点;
    //         head = n.next;
    //     }

    //     if (n.next != null) {
    //         n.next.prev = n.prev;
    //     } else {
    //         tail = n.prev;
    //     }
    // }

    // //将结点放置在链表的头部;
    // private void setHead(Node n) {
    //     n.next = head;
    //     n.prev = null;
    //     if (head != null) {
    //         head.prev = n;
    //     }
    //     head = n;
    //     //当链表中只有一个结点时,此时head,tail指向一个结点;
    //     if (tail == null) {
    //         tail = head;
    //     }
    // }


}
/**
 * Your LRUCache object will be instantiated and called as such: 
 * LRUCache obj = new LRUCache(capacity); 
 * int param_1 = obj.get(key); 
 * obj.put(key,value);
 */
