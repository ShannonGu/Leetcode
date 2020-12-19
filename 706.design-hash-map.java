import java.util.ArrayList;

/*
 * @lc app=leetcode id=706 lang=java
 *
 * [706] Design HashMap
 */
class MyHashMap {
    //拉链法解决冲突，先找到该key所在的桶
    //然后在所在的桶的链表中寻找对应的值
    class ListNode {
        int key, val;
        ListNode next;

        ListNode(int key, int val) {
            this.key = key;
            this.val = val;
        }
    }

    private ListNode[] nodes;

    /** Initialize your data structure here. */
    public MyHashMap() {
        nodes = new ListNode[10001];
    }

    private int idx(int key) {
        return Integer.hashCode(key) % nodes.length;
    }

    ListNode find(ListNode bucket, int key) {
        ListNode node = bucket, prev = null;
        while (node != null && node.key != key) {
            prev = node;
            node = node.next;
        }
        return prev;
    }

    /** value will always be non-negative. */
    public void put(int key, int value) {
        int i = idx(key);
        //该key对应的桶不存在
        //则创建一个桶存放具有相同哈希值对应的key
        if(nodes[i] == null)
            nodes[i] = new ListNode(-1, -1);
        
        //找到在对应的桶中，在链表中插入该值
        ListNode prev = find(nodes[i], key);

        if (prev.next == null)
            //如果是最后一个元素
            //直接插入
            prev.next = new ListNode(key, value);
        else
            //否则之前和当前元素有相同的key,直接更新value
            prev.next.val = value;
    }

    /** Returns the value to which the specified key is mapped, or -1 if this map contains no mapping for the key */
    public int get(int key) {
        int i = idx(key);
        if(nodes[i] == null)
            return -1;
        ListNode prev = find(nodes[i], key);
        return prev.next == null ? -1 : prev.next.val;
    }

    /** Removes the mapping of the specified value key if this map contains a mapping for the key */
    public void remove(int key) {
        int i = idx(key);
        if(nodes[i] == null)
            return;
        ListNode prev = find(nodes[i], key);
        if(prev.next == null)
            return;
        prev.next = prev.next.next;
    }
}

/**
 * Your MyHashMap object will be instantiated and called as such:
 * MyHashMap obj = new MyHashMap();
 * obj.put(key,value);
 * int param_2 = obj.get(key);
 * obj.remove(key);
 */

