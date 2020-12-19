/*
 * @lc app=leetcode id=705 lang=java
 *
 * [705] Design HashSet
 */

// @lc code=start
class MyHashSet {
    class ListNode {
        int key;
        ListNode next;

        ListNode(int key) {
            this.key = key;
        }
    }
    private ListNode[] nodes;

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
    
    /** Initialize your data structure here. */
    public MyHashSet() {
        nodes = new ListNode[10001];
    }
    
    public void add(int key) {
        int i = idx(key);
        //该key对应的桶不存在
        //则创建一个桶存放具有相同哈希值对应的key
        if(nodes[i] == null)
            nodes[i] = new ListNode(-1);
        
        //找到在对应的桶中，在链表中插入该值
        ListNode prev = find(nodes[i], key);

        if (prev.next == null)
            //如果是最后一个元素
            //直接插入
            prev.next = new ListNode(key);
    }
    
    public void remove(int key) {
        int i = idx(key);
        if(nodes[i] == null)
            return;
        ListNode prev = find(nodes[i], key);
        if(prev.next == null)
            return;
        prev.next = prev.next.next;
    }
    
    /** Returns true if this set contains the specified element */
    public boolean contains(int key) {
        int i = idx(key);
        if(nodes[i] == null)
            return false;
        ListNode prev = find(nodes[i], key);
        if(prev.next == null)
            return false;
        return true;
    }
}

/**
 * Your MyHashSet object will be instantiated and called as such:
 * MyHashSet obj = new MyHashSet();
 * obj.add(key);
 * obj.remove(key);
 * boolean param_3 = obj.contains(key);
 */
// @lc code=end

